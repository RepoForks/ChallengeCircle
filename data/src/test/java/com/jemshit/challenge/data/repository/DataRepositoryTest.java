package com.jemshit.challenge.data.repository;

import com.jemshit.challenge.data.datasource.DataSourceFactory;
import com.jemshit.challenge.data.datasource.local.LocalDataSource;
import com.jemshit.challenge.data.datasource.remote.RemoteDataSource;
import com.jemshit.challenge.data.entity.mapper.ProfileEntityMapper;
import com.jemshit.challenge.data.entity.mapper.UserEntityMapper;
import com.jemshit.challenge.data.entity.web_responses.LoginResponseEntity;
import com.jemshit.challenge.data.entity.web_responses.ProfileEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;


public class DataRepositoryTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private DataRepository dataRepository;
    @Mock private DataSourceFactory dataSourceFactory;
    @Mock private UserEntityMapper userEntityMapper;
    @Mock private ProfileEntityMapper profileEntityMapper;
    @Mock private RemoteDataSource remoteDataSource;
    @Mock private LocalDataSource localDataSource;
    private final String USERNAME = "username";
    private final String PASSWORD = "password";

    @Before
    public void setUp() throws Exception {
        dataRepository = new DataRepository(dataSourceFactory, userEntityMapper, profileEntityMapper);
    }

    //region login
    @Test
    public void login_shouldInvokeDataSourceFactory() {
        // Assign
        Mockito.when(dataSourceFactory.getRemoteDataSource())
                .thenReturn(remoteDataSource);
        Mockito.when(remoteDataSource.login(USERNAME, PASSWORD))
                .thenReturn(Single.just(new LoginResponseEntity()));

        // Act
        dataRepository.login(USERNAME, PASSWORD).subscribe();

        // Assert
        Mockito.verify(dataSourceFactory).getRemoteDataSource();
        Mockito.verify(remoteDataSource).login(USERNAME, PASSWORD);
    }

    @Test
    public void login_success() {
        // Assign
        Mockito.when(dataSourceFactory.getRemoteDataSource())
                .thenReturn(remoteDataSource);
        Mockito.when(remoteDataSource.login(USERNAME, PASSWORD))
                .thenReturn(Single.just(new LoginResponseEntity("", "", "")));
        TestObserver<Boolean> testObserver = new TestObserver<>();

        // Act
        dataRepository.login(USERNAME, PASSWORD)
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoErrors();
        testObserver.assertValue(true);
        testObserver.assertComplete();
    }

    @Test
    public void login_fail() {
        // Assign
        Mockito.when(dataSourceFactory.getRemoteDataSource())
                .thenReturn(remoteDataSource);
        Mockito.when(remoteDataSource.login(USERNAME, PASSWORD))
                .thenReturn(Single.just(new LoginResponseEntity()));
        TestObserver<Boolean> testObserver = new TestObserver<>();

        // Act
        dataRepository.login(USERNAME, PASSWORD)
                .subscribe(testObserver);

        // Assert
        testObserver.assertNoErrors();
        testObserver.assertValue(false);
        testObserver.assertComplete();
    }
    //endregion


    //region getProfileList
    @Test
    public void getProfileList_shouldInvokeDataSourceFactoryAndMapper() {
        // Assign
        Mockito.when(dataSourceFactory.getDataSourceForProfileList())
                .thenReturn(remoteDataSource);
        String TOKEN = "secret_token";
        Mockito.when(remoteDataSource.getProfileList(TOKEN))
                .thenReturn(Single.just(Collections.emptyList()));

        // Act
        dataRepository.getProfileList(TOKEN).subscribe();

        // Assert
        Mockito.verify(dataSourceFactory).getDataSourceForProfileList();
        Mockito.verify(remoteDataSource).getProfileList(TOKEN);
        Mockito.verify(userEntityMapper).transform(ArgumentMatchers.anyCollection());
    }

    //endregion


    //region getProfile
    @Test
    public void getProfile_shouldInvokeDataSourceFactoryAndMapper() {
        // Assign
        String PROFILE_ID = "profileId";
        Mockito.when(dataSourceFactory.getDataSourceForProfile(PROFILE_ID))
                .thenReturn(remoteDataSource);
        Mockito.when(remoteDataSource.getProfile(PROFILE_ID))
                .thenReturn(Single.just(new ProfileEntity()));

        // Act
        dataRepository.getProfile(PROFILE_ID).subscribe((profileModel, throwable) -> {
            //
        });

        // Assert
        Mockito.verify(dataSourceFactory).getDataSourceForProfile(PROFILE_ID);
        Mockito.verify(remoteDataSource).getProfile(PROFILE_ID);
        Mockito.verify(profileEntityMapper).transform(ArgumentMatchers.any(ProfileEntity.class));
    }
    //endregion

    //region getToken
    @Test
    public void getToken_shouldInvokeDataSourceFactory() {
        // Assign
        Mockito.when(dataSourceFactory.getLocalDataSource())
                .thenReturn(localDataSource);
        Mockito.when(localDataSource.getToken())
                .thenReturn("");

        // Act
        dataRepository.getToken();

        // Assert
        Mockito.verify(dataSourceFactory).getLocalDataSource();
        Mockito.verify(localDataSource).getToken();
    }
    //endregion
}