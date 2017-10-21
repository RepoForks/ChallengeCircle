package com.jemshit.challenge.presentation.ui.profile_detail.di;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.profile_detail.ProfileDetailActivity;
import com.jemshit.challenge.presentation.ui.profile_detail.mvp.ProfileDetailContract;
import com.jemshit.challenge.presentation.ui.profile_detail.mvp.ProfileDetailPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = ProfileDetailActivitySubComponent.BindsModule.class)
public interface ProfileDetailActivitySubComponent extends AndroidInjector<ProfileDetailActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProfileDetailActivity> {
    }

    @Module
    abstract class BindsModule {
        @Binds @ActivityScope
        abstract ProfileDetailContract.Presenter providePresenter(ProfileDetailPresenter p);
    }
}