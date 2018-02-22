package com.jemshit.challenge.data.di;

import com.jemshit.challenge.data.datasource.remote.web_service.ApiService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiServiceModule {
    @Provides @Singleton
    ApiService provideAuthApiService(@Named("ApiService") Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
