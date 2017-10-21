package com.jemshit.challenge.presentation.di.module;

import com.jemshit.challenge.presentation.di.scope.ActivityScope;
import com.jemshit.challenge.presentation.ui.login.LoginActivity;
import com.jemshit.challenge.presentation.ui.login.di.LoginActivityBindsModule;
import com.jemshit.challenge.presentation.ui.profile_detail.ProfileDetailActivity;
import com.jemshit.challenge.presentation.ui.profile_detail.di.ProfileDetailActivityBindsModule;
import com.jemshit.challenge.presentation.ui.profile_list.ProfileListActivity;
import com.jemshit.challenge.presentation.ui.profile_list.di.ProfileListActivityBindsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AppScopeActivityBinders {

    @ContributesAndroidInjector(modules = LoginActivityBindsModule.class) @ActivityScope
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = ProfileListActivityBindsModule.class) @ActivityScope
    abstract ProfileListActivity bindProfileListActivity();

    @ContributesAndroidInjector(modules = ProfileDetailActivityBindsModule.class) @ActivityScope
    abstract ProfileDetailActivity bindProfileDetailActivity();
}