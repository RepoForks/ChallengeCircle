package com.jemshit.challenge.presentation.ui.profile_list.di;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.profile_list.mvp.ProfileListContract;
import com.jemshit.challenge.presentation.ui.profile_list.mvp.ProfileListPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ProfileListActivityBindsModule {
    @Binds @ActivityScope
    abstract ProfileListContract.Presenter providePresenter(ProfileListPresenter p);
}