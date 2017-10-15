package com.jemshit.challenge.presentation.di.module;

import android.content.Context;

import com.jemshit.challenge.presentation.ChallengeApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ApplicationModule {
    private final ChallengeApplication application;

    public ApplicationModule(ChallengeApplication application) {
        this.application = application;
    }

    @Provides @Singleton @Named("AppContext")
    Context provideApplicationContext() {
        return this.application;
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
