package com.jemshit.challenge.data.datasource;

import com.jemshit.challenge.data.datasource.cache.Cache;
import com.jemshit.challenge.data.datasource.local.LocalDataSource;
import com.jemshit.challenge.data.datasource.remote.RemoteDataSource;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class DataSourceFactoryTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
    private DataSourceFactory dataSourceFactory;
    @Mock private LocalDataSource localDataSource;
    @Mock private RemoteDataSource remoteDataSource;
    @Mock private Cache cache;
    private final String PROFILE_ID = "profileId";

    @Before
    public void setUp() throws Exception {
        dataSourceFactory = new DataSourceFactory(localDataSource, remoteDataSource, cache);
    }

    //region getDataSourceForProfileList
    @Test
    public void getDataSourceForProfileList_shouldInvokeCache() throws Exception {
        // Act
        dataSourceFactory.getDataSourceForProfileList();

        // Assert
        Mockito.verify(cache).isCachedAndValid(ArgumentMatchers.anyString());
    }

    @Test
    public void getDataSourceForProfileList_shouldReturnLocalDataSource() throws Exception {
        // Assign
        Mockito.doReturn(true)
                .when(cache)
                .isCachedAndValid(ArgumentMatchers.anyString());
        DataSource localDataSource;

        // Act
        localDataSource = dataSourceFactory.getDataSourceForProfileList();

        // Assert
        Assert.assertNotNull(localDataSource);
        Assert.assertThat(localDataSource, CoreMatchers.is(CoreMatchers.instanceOf(LocalDataSource.class)));
    }

    @Test
    public void getDataSourceForProfileList_shouldReturnRemoteDataSource() throws Exception {
        // Assign
        Mockito.doReturn(false)
                .when(cache)
                .isCachedAndValid(ArgumentMatchers.anyString());
        DataSource remoteDataSource;

        // Act
        remoteDataSource = dataSourceFactory.getDataSourceForProfileList();

        // Assert
        Assert.assertNotNull(remoteDataSource);
        Assert.assertThat(remoteDataSource, CoreMatchers.is(CoreMatchers.instanceOf(RemoteDataSource.class)));
    }
    //endregion

    //region getDataSourceForProfile
    @Test
    public void getDataSourceForProfile_shouldInvokeCache() throws Exception {
        // Act
        dataSourceFactory.getDataSourceForProfile(PROFILE_ID);

        // Assert
        Mockito.verify(cache).isCachedAndValid(PROFILE_ID);
    }

    @Test
    public void getDataSourceForProfile_shouldReturnLocalDataSource() throws Exception {
        // Assign
        Mockito.doReturn(true)
                .when(cache)
                .isCachedAndValid(ArgumentMatchers.anyString());
        DataSource localDataSource;

        // Act
        localDataSource = dataSourceFactory.getDataSourceForProfile(PROFILE_ID);

        // Assert
        Assert.assertNotNull(localDataSource);
        Assert.assertThat(localDataSource, CoreMatchers.is(CoreMatchers.instanceOf(LocalDataSource.class)));
    }

    @Test
    public void getDataSourceForProfile_shouldReturnRemoteDataSource() throws Exception {
        // Assign
        Mockito.doReturn(false)
                .when(cache)
                .isCachedAndValid(ArgumentMatchers.anyString());
        DataSource remoteDataSource;

        // Act
        remoteDataSource = dataSourceFactory.getDataSourceForProfile(PROFILE_ID);

        // Assert
        Assert.assertNotNull(remoteDataSource);
        Assert.assertThat(remoteDataSource, CoreMatchers.is(CoreMatchers.instanceOf(RemoteDataSource.class)));
    }
    //endregion

    //region getRemoteDataSource
    @Test
    public void getRemoteDataSource_shouldReturnRemoteDataSource() throws Exception {
        // Assign
        DataSource remoteDataSource;

        // Act
        remoteDataSource = dataSourceFactory.getRemoteDataSource();

        // Assert
        Assert.assertNotNull(remoteDataSource);
        Assert.assertThat(remoteDataSource, CoreMatchers.is(CoreMatchers.instanceOf(RemoteDataSource.class)));
    }
    //endregion

    //region getLocalDataSource
    @Test
    public void getLocalDataSource_shouldReturnLocalDataSource() throws Exception {
        // Assign
        DataSource localDataSource;

        // Act
        localDataSource = dataSourceFactory.getLocalDataSource();

        // Assert
        Assert.assertNotNull(localDataSource);
        Assert.assertThat(localDataSource, CoreMatchers.is(CoreMatchers.instanceOf(LocalDataSource.class)));
    }
    //endregion

}