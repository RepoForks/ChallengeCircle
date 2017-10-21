package com.jemshit.challenge.presentation.ui.profile_detail.di;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.profile_detail.mvp.ProfileDetailContract;
import com.jemshit.challenge.presentation.ui.profile_detail.mvp.ProfileDetailPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ProfileDetailActivityBindsModule {
    @Binds @ActivityScope
    abstract ProfileDetailContract.Presenter providePresenter(ProfileDetailPresenter p);
}