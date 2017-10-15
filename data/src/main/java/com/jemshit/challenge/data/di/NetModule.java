package com.jemshit.challenge.data.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jemshit.challenge.data.other.ConstantsWebService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jemshit.challenge.data.other.ConstantsWebService.BUILD_CONFIG_DEBUG;

@Module
public class NetModule {

    private static final int DISK_CACHE_SIZE = 15 * 1024 * 1024; //15 mb
    private static final int NETWORK_TIMEOUT = 20; //20 seconds

    @Provides @Singleton @Named("ApiService")
    public OkHttpClient provideApiOkHttpClient(@Named("AppContext") Context contextApp) {
        File cacheDir = new File(contextApp.getCacheDir(), "okHttp_cache");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache);

        if (BUILD_CONFIG_DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);
        }

        return client.build();
    }

    @Provides @Singleton @Named("ApiService")
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides @Singleton @Named("Other")
    public Gson provideGsonOther() {
        return new GsonBuilder().create();
    }


    @Provides @Singleton @Named("ApiService")
    public Retrofit provideRetrofit(@Named("ApiService") OkHttpClient client, @Named("ApiService") Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(ConstantsWebService.BASE_URL_WEB_SERVICE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
