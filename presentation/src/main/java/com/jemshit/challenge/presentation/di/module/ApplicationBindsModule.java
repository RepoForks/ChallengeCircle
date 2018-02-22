package com.jemshit.challenge.presentation.di.module;

import com.jemshit.challenge.presentation.provider.AndroidResourceProvider;
import com.jemshit.challenge.presentation.provider.ResourceProvider;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationBindsModule {

    @Binds @Singleton @Named("AndroidResourceProvider")
    public abstract ResourceProvider provideAndroidResourceProvider(AndroidResourceProvider androidResourceProvider);

}
