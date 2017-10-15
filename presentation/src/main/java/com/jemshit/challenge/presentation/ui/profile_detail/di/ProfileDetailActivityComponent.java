package com.jemshit.challenge.presentation.ui.profile_detail.di;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.profile_detail.ProfileDetailActivity;
import com.jemshit.challenge.presentation.ui.profile_detail.mvp.ProfileDetailContract;
import com.jemshit.challenge.presentation.ui.profile_detail.mvp.ProfileDetailPresenter;
import com.trevjonez.inject.PlainComponent;
import com.trevjonez.inject.activity.ActivityComponentBuilder;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {
        ProfileDetailActivityComponent.BindsModule.class
})
public interface ProfileDetailActivityComponent extends PlainComponent<ProfileDetailActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<ProfileDetailActivity, ProfileDetailActivityComponent> {
    }

    @Module
    abstract class BindsModule {
        @Binds @ActivityScope
        abstract ProfileDetailContract.Presenter providePresenter(ProfileDetailPresenter p);
    }
}