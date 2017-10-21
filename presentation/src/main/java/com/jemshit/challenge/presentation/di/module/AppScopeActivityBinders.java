package com.jemshit.challenge.presentation.di.module;

import android.app.Activity;

import com.jemshit.challenge.presentation.ui.login.LoginActivity;
import com.jemshit.challenge.presentation.ui.login.di.LoginActivitySubComponent;
import com.jemshit.challenge.presentation.ui.profile_detail.ProfileDetailActivity;
import com.jemshit.challenge.presentation.ui.profile_detail.di.ProfileDetailActivitySubComponent;
import com.jemshit.challenge.presentation.ui.profile_list.ProfileListActivity;
import com.jemshit.challenge.presentation.ui.profile_list.di.ProfileListActivitySubComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        LoginActivitySubComponent.class,
        ProfileDetailActivitySubComponent.class,
        ProfileListActivitySubComponent.class
})
public abstract class AppScopeActivityBinders {

    @Binds @IntoMap @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindLoginActivityInjectorFactory(LoginActivitySubComponent.Builder builder);

    @Binds @IntoMap @ActivityKey(ProfileListActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindProfileListActivityInjectorFactory(ProfileListActivitySubComponent.Builder builder);

    @Binds @IntoMap @ActivityKey(ProfileDetailActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindProfileDetailActivityInjectorFactory(ProfileDetailActivitySubComponent.Builder builder);
}