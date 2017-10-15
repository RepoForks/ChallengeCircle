package com.jemshit.challenge.presentation.ui.profile_list.di;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.profile_list.ProfileListActivity;
import com.jemshit.challenge.presentation.ui.profile_list.mvp.ProfileListContract;
import com.jemshit.challenge.presentation.ui.profile_list.mvp.ProfileListPresenter;
import com.trevjonez.inject.PlainComponent;
import com.trevjonez.inject.activity.ActivityComponentBuilder;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {
        ProfileListActivityComponent.BindsModule.class
})
public interface ProfileListActivityComponent extends PlainComponent<ProfileListActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<ProfileListActivity, ProfileListActivityComponent> {
    }

    @Module
    abstract class BindsModule {
        @Binds @ActivityScope
        abstract ProfileListContract.Presenter providePresenter(ProfileListPresenter p);
    }
}