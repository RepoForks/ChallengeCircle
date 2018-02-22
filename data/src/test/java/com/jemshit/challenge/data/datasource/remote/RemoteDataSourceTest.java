package com.jemshit.challenge.data.datasource.remote;

import com.jemshit.challenge.data.datasource.cache.Cache;
import com.jemshit.challenge.data.entity.web_responses.GetProfileListResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.LoginResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;
import com.jemshit.challenge.data.entity.web_responses.UserEntity;
import com.jemshit.challenge.data.exception.FetchDataException;
import com.jemshit.challenge.data.test_helper.DataGenerator;
import com.jemshit.challenge.data.datasource.remote.web_service.ApiService;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;


public class RemoteDataSourceTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private RemoteDataSource remoteDataSource;
    @Mock private ApiService apiService;
    @Mock private Cache cache;
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String TOKEN = "secret_token";
    private final String PROFILE_ID = "profileId";

    @Before
    public void setUp() throws Exception {
        remoteDataSource = new RemoteDataSource(apiService, cache);
    }

    //region Login
    @Test
    public void login_successShouldInvokeApiServiceAndCache() throws Exception {
        // Assign
        List<LoginResponseEntity> responseEntityList = new ArrayList<>(1);
        responseEntityList.add(new LoginResponseEntity(USERNAME, PASSWORD, TOKEN));
        Mockito.when(apiService.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(responseEntityList));

        // Act
        remoteDataSource.login(USERNAME, PASSWORD)
                .subscribe();

        // Assert
        Mockito.verify(apiService).login(USERNAME, PASSWORD);
        Mockito.verify(cache).putInfinite(ArgumentMatchers.anyString(), ArgumentMatchers.eq(TOKEN), ArgumentMatchers.any());

        Mockito.verifyNoMoreInteractions(apiService);
        Mockito.verifyNoMoreInteractions(cache);
    }

    @Test
    public void login_successShouldReturnValidData() throws Exception {
        // Assign
        List<LoginResponseEntity> responseEntityList = new ArrayList<>(1);
        LoginResponseEntity loginResponseEntity = new LoginResponseEntity(USERNAME, PASSWORD, TOKEN);
        responseEntityList.add(loginResponseEntity);
        Mockito.when(apiService.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(responseEntityList));
        TestObserver<LoginResponseEntity> testObserver = new TestObserver<>();

        // Act
        remoteDataSource.login(USERNAME, PASSWORD)
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(loginResponseEntity);
        testObserver.assertComplete();
    }

    @Test
    public void login_errorShouldNotInvokeCache() throws Exception {
        // Assign
        List<LoginResponseEntity> responseEntityList = new ArrayList<>(0);
        Mockito.when(apiService.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(responseEntityList));

        // Act
        remoteDataSource.login(USERNAME, PASSWORD)
                .subscribe();

        // Assert
        Mockito.verifyZeroInteractions(cache);
    }

    @Test
    public void login_errorShouldReturnEmpty() throws Exception {
        // Assign
        List<LoginResponseEntity> responseEntityList = new ArrayList<>(0);
        Mockito.when(apiService.login(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Single.just(responseEntityList));
        TestObserver<LoginResponseEntity> testObserver = new TestObserver<>();

        // Act
        remoteDataSource.login(USERNAME, PASSWORD)
                .subscribe(testObserver);

        // Assert
        testObserver.assertValueCount(1);
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }
    //endregion


    //region Get Profile List
    @Test
    public void getProfileList_successShouldInvokeApiServiceAndCache() throws Exception {
        // Assign
        List<GetProfileListResponseEntity> responseEntityList = new ArrayList<>(1);
        List<UserEntity> userEntityList = DataGenerator.createUserEntityList(true);
        responseEntityList.add(new GetProfileListResponseEntity(userEntityList));
        Mockito.when(apiService.getProfileList(TOKEN))
                .thenReturn(Single.just(responseEntityList));

        // Act
        remoteDataSource.getProfileList(TOKEN)
                .subscribe();

        // Assert
        Mockito.verify(apiService).getProfileList(TOKEN);
        Mockito.verify(cache).putList(ArgumentMatchers.anyString(), ArgumentMatchers.eq(userEntityList), ArgumentMatchers.any(), ArgumentMatchers.anyLong());

        Mockito.verifyNoMoreInteractions(apiService);
        Mockito.verifyNoMoreInteractions(cache);
    }

    @Test
    public void getProfileList_successShouldReturnValidData() throws Exception {
        // Assign
        List<GetProfileListResponseEntity> responseEntityList = new ArrayList<>(1);
        List<UserEntity> userEntityList = DataGenerator.createUserEntityList(true);
        responseEntityList.add(new GetProfileListResponseEntity(userEntityList));
        Mockito.when(apiService.getProfileList(TOKEN))
                .thenReturn(Single.just(responseEntityList));
        TestObserver<List<UserEntity>> testObserver = new TestObserver<>();

        // Act
        remoteDataSource.getProfileList(TOKEN)
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(userEntityList);
        testObserver.assertComplete();
    }

    @Test
    public void getProfileList_errorShouldNotInvokeCache() throws Exception {
        // Assign
        List<GetProfileListResponseEntity> responseEntityList = new ArrayList<>(0);
        Mockito.when(apiService.getProfileList(TOKEN))
                .thenReturn(Single.just(responseEntityList));
        TestObserver<List<UserEntity>> testObserver = new TestObserver<>();

        // Act
        remoteDataSource.getProfileList(TOKEN)
                .subscribe(testObserver);

        // Assert
        Mockito.verifyZeroInteractions(cache);
    }

    @Test
    public void getProfileList_errorShouldThrowFetchDataException() throws Exception {
        // Assign
        List<GetProfileListResponseEntity> responseEntityList = new ArrayList<>(0);
        Mockito.when(apiService.getProfileList(TOKEN))
                .thenReturn(Single.just(responseEntityList));
        TestObserver<List<UserEntity>> testObserver = new TestObserver<>();

        // Act
        remoteDataSource.getProfileList(TOKEN)
                .subscribe(testObserver);

        // Assert
        testObserver.assertNotComplete();
        testObserver.assertNoValues();
        testObserver.assertError(FetchDataException.class);
    }
    //endregion


    //region Get Profile
    @Test
    public void getProfile_successShouldInvokeApiServiceAndCache() throws Exception {
        // Assign
        List<ProfileEntity> responseEntityList = DataGenerator.createProfileEntityList(true);
        Mockito.when(apiService.getProfile(PROFILE_ID))
                .thenReturn(Single.just(responseEntityList));

        // Act
        remoteDataSource.getProfile(PROFILE_ID)
                .subscribe();

        // Assert
        Mockito.verify(apiService).getProfile(PROFILE_ID);
        Mockito.verify(cache).put(ArgumentMatchers.anyString(), ArgumentMatchers.eq(responseEntityList.get(0)),
                ArgumentMatchers.any(), ArgumentMatchers.anyLong());

        Mockito.verifyNoMoreInteractions(apiService);
        Mockito.verifyNoMoreInteractions(cache);
    }

    @Test
    public void getProfile_successShouldReturnValidData() throws Exception {
        // Assign
        List<ProfileEntity> responseEntityList = DataGenerator.createProfileEntityList(true);
        Mockito.when(apiService.getProfile(PROFILE_ID))
                .thenReturn(Single.just(responseEntityList));
        TestObserver<ProfileEntity> testObserver = new TestObserver<>();

        // Act
        remoteDataSource.getProfile(PROFILE_ID)
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(responseEntityList.get(0));
        testObserver.assertComplete();
    }

    @Test
    public void getProfile_errorShouldNotInvokeCache() throws Exception {
        // Assign
        List<ProfileEntity> responseEntityList = DataGenerator.createProfileEntityList(false);
        Mockito.when(apiService.getProfile(PROFILE_ID))
                .thenReturn(Single.just(responseEntityList));
        TestObserver<ProfileEntity> testObserver = new TestObserver<>();

        // Act
        remoteDataSource.getProfile(PROFILE_ID)
                .subscribe(testObserver);

        // Assert
        Mockito.verifyZeroInteractions(cache);
    }

    @Test
    public void getProfile_errorShouldThrowFetchDataException() throws Exception {
        // Assign
        List<ProfileEntity> responseEntityList = DataGenerator.createProfileEntityList(false);
        Mockito.when(apiService.getProfile(PROFILE_ID))
                .thenReturn(Single.just(responseEntityList));
        TestObserver<ProfileEntity> testObserver = new TestObserver<>();

        // Act
        remoteDataSource.getProfile(PROFILE_ID)
                .subscribe(testObserver);

        // Assert
        testObserver.assertNotComplete();
        testObserver.assertNoValues();
        testObserver.assertError(FetchDataException.class);
    }
    //endregion

    //region Get Token
    @Test
    public void getToken_shouldAlwaysReturnEmptyString() throws Exception {
        // Assign
        String token;

        // Act
        token = remoteDataSource.getToken();

        // Assert
        Assert.assertEquals("", token);
    }
    //endregion

}