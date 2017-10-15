package com.jemshit.challenge.presentation.di.component;

import com.jemshit.challenge.data.di.ApiServiceModule;
import com.jemshit.challenge.data.di.DataModule;
import com.jemshit.challenge.data.di.NetModule;
import com.jemshit.challenge.data.di.RepositoryModule;
import com.jemshit.challenge.presentation.ChallengeApplication;
import com.jemshit.challenge.presentation.di.module.AppScopeActivityBinders;
import com.jemshit.challenge.presentation.di.module.ApplicationModule;
import com.trevjonez.inject.PlainComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiServiceModule.class,
                DataModule.class,
                NetModule.class,
                RepositoryModule.class,
                AppScopeActivityBinders.class
        }
)
public interface ApplicationComponent extends PlainComponent<ChallengeApplication> {
}
