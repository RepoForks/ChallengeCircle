package com.jemshit.challenge.presentation.di.module;

import com.jemshit.challenge.presentation.ui.login.LoginActivity;
import com.jemshit.challenge.presentation.ui.login.di.LoginActivityComponent;
import com.jemshit.challenge.presentation.ui.profile_detail.ProfileDetailActivity;
import com.jemshit.challenge.presentation.ui.profile_detail.di.ProfileDetailActivityComponent;
import com.jemshit.challenge.presentation.ui.profile_list.ProfileListActivity;
import com.jemshit.challenge.presentation.ui.profile_list.di.ProfileListActivityComponent;
import com.trevjonez.inject.activity.ActivityComponentBuilder;
import com.trevjonez.inject.activity.ActivityKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        LoginActivityComponent.class,
        ProfileDetailActivityComponent.class,
        ProfileListActivityComponent.class
})
public abstract class AppScopeActivityBinders {

    @Binds @IntoMap @ActivityKey(LoginActivity.class)
    public abstract ActivityComponentBuilder loginComponentBuilder(LoginActivityComponent.Builder impl);

    @Binds @IntoMap @ActivityKey(ProfileDetailActivity.class)
    public abstract ActivityComponentBuilder profileDetailComponentBuilder(ProfileDetailActivityComponent.Builder impl);

    @Binds @IntoMap @ActivityKey(ProfileListActivity.class)
    public abstract ActivityComponentBuilder profileListComponentBuilder(ProfileListActivityComponent.Builder impl);
}