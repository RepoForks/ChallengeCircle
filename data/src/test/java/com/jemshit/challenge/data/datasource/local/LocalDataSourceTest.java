package com.jemshit.challenge.data.datasource.local;

import com.jemshit.challenge.data.datasource.cache.Cache;
import com.jemshit.challenge.data.entity.web_responses.LoginResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.entity.web_responses.UserEntity;
import com.jemshit.challenge.data.test_helper.DataGenerator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import io.reactivex.observers.TestObserver;


public class LocalDataSourceTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private LocalDataSource localDataSource;
    @Mock private Cache cache;
    private final String TOKEN = "secret_token";
    private final String PROFILE_ID = "profileId";

    @Before
    public void setUp() throws Exception {
        localDataSource = new LocalDataSource(cache);
    }

    //region Get Token
    @Test
    public void login_shouldAlwaysReturnEmpty() throws Exception {
        // Assign
        TestObserver<LoginResponseEntity> testObserver = new TestObserver<>();
        String USERNAME = "username";
        String PASSWORD = "password";

        // Act
        localDataSource.login(USERNAME, PASSWORD)
                .subscribe(testObserver);

        // Assert
        testObserver.assertValueCount(1);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }
    //endregion

    //region Get Profile List
    @Test
    public void getProfileList_shouldAlwaysInvokeCache() throws Exception {
        // Act
        localDataSource.getProfileList(TOKEN)
                .subscribe();

        // Assert
        Mockito.verify(cache).getList(ArgumentMatchers.anyString(), ArgumentMatchers.any());
    }

    @Test
    public void getProfileList_successShouldReturnValidData() throws Exception {
        // Assign
        List<UserEntity> userEntityList = DataGenerator.createUserEntityList(true);
        Mockito.doReturn(userEntityList)
                .when(cache)
                .getList(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        TestObserver<List<UserEntity>> testObserver = new TestObserver<>();

        // Act
        localDataSource.getProfileList(TOKEN)
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(userEntityList);
        testObserver.assertComplete();
    }
    //endregion

    //region Get Profile
    @Test
    public void getProfile_shouldAlwaysInvokeCache() throws Exception {
        // Act
        localDataSource.getProfile(PROFILE_ID)
                .subscribe();

        // Assert
        Mockito.verify(cache).get(ArgumentMatchers.anyString(), ArgumentMatchers.any());
    }

    @Test
    public void getProfile_successShouldReturnValidData() throws Exception {
        // Assign
        ProfileEntity profileEntity = DataGenerator.createProfileEntity(true);
        Mockito.doReturn(profileEntity)
                .when(cache)
                .get(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        TestObserver<ProfileEntity> testObserver = new TestObserver<>();

        // Act
        localDataSource.getProfile(PROFILE_ID)
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(profileEntity);
        testObserver.assertComplete();
    }
    //endregion

    //region Get Token
    @Test
    public void getToken_shouldAlwaysInvokeCache() throws Exception {
        // Act
        localDataSource.getToken();

        // Assert
        Mockito.verify(cache).getInfinite(ArgumentMatchers.anyString(), ArgumentMatchers.any());
    }

    @Test
    public void getToken_successShouldReturnValidData() throws Exception {
        // Assign
        Mockito.doReturn(TOKEN)
                .when(cache)
                .getInfinite(ArgumentMatchers.anyString(), ArgumentMatchers.any());
        String token;

        // Act
        token = localDataSource.getToken();

        // Assert
        Assert.assertEquals(TOKEN, token);
    }
    //endregion
}