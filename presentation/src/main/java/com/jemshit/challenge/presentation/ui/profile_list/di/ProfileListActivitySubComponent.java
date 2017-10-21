package com.jemshit.challenge.presentation.ui.profile_list.di;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.profile_list.ProfileListActivity;
import com.jemshit.challenge.presentation.ui.profile_list.mvp.ProfileListContract;
import com.jemshit.challenge.presentation.ui.profile_list.mvp.ProfileListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = ProfileListActivitySubComponent.BindsModule.class)
public interface ProfileListActivitySubComponent extends AndroidInjector<ProfileListActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ProfileListActivity> {
    }

    @Module
    abstract class BindsModule {
        @Binds @ActivityScope
        abstract ProfileListContract.Presenter providePresenter(ProfileListPresenter p);
    }
}