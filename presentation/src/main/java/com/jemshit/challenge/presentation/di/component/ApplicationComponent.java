package com.jemshit.challenge.presentation.di.component;

import com.jemshit.challenge.data.di.ApiServiceModule;
import com.jemshit.challenge.data.di.DataModule;
import com.jemshit.challenge.data.di.NetworkModule;
import com.jemshit.challenge.data.di.RepositoryModule;
import com.jemshit.challenge.presentation.ChallengeApplication;
import com.jemshit.challenge.presentation.di.module.AppScopeActivityBinders;
import com.jemshit.challenge.presentation.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiServiceModule.class,
                DataModule.class,
                NetworkModule.class,
                RepositoryModule.class,

                AppScopeActivityBinders.class
        }
)
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        ApplicationComponent build();

        @BindsInstance Builder application(ChallengeApplication application);
    }

    void inject(ChallengeApplication application);
}
