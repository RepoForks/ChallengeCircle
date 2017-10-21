package com.jemshit.challenge.presentation.di.module;

import android.content.Context;

import com.jemshit.challenge.presentation.ChallengeApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ApplicationModule {

    @Provides @Singleton @Named("AppContext")
    Context provideApplicationContext(ChallengeApplication application) {
        return application;
    }

    @Provides @Singleton @Named("IoWorkScheduler")
    Scheduler provideWorkScheduler() {
        return Schedulers.io();
    }

    @Provides @Singleton @Named("MainScheduler")
    Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
