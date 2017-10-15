package com.jemshit.challenge.data.datasource.local;

import com.jemshit.challenge.data.test_helper.DataGenerator;
import com.jemshit.challenge.data.datasource.cache.Cache;
import com.jemshit.challenge.data.entity.web_responses.LoginResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.entity.web_responses.UserEntity;

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

import rx.observers.TestSubscriber;

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
    public void login_shouldAlwaysReturnNull() throws Exception {
        // Assign
        TestSubscriber<LoginResponseEntity> testSubscriber = new TestSubscriber<>();
        String USERNAME = "username";
        String PASSWORD = "password";

        // Act
        localDataSource.login(USERNAME, PASSWORD)
                .subscribe(testSubscriber);

        // Assert
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(null);
        testSubscriber.assertCompleted();
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
        TestSubscriber<List<UserEntity>> testSubscriber = new TestSubscriber<>();

        // Act
        localDataSource.getProfileList(TOKEN)
                .subscribe(testSubscriber);

        // Assert
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(userEntityList);
        testSubscriber.assertCompleted();
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
        TestSubscriber<ProfileEntity> testSubscriber = new TestSubscriber<>();

        // Act
        localDataSource.getProfile(PROFILE_ID)
                .subscribe(testSubscriber);

        // Assert
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValue(profileEntity);
        testSubscriber.assertCompleted();
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