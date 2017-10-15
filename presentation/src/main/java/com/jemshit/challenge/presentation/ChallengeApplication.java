package com.jemshit.challenge.presentation;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.jemshit.challenge.presentation.di.component.ApplicationComponent;
import com.jemshit.challenge.presentation.di.component.DaggerApplicationComponent;
import com.jemshit.challenge.presentation.di.module.ApplicationModule;
import com.trevjonez.inject.PlainComponent;
import com.trevjonez.inject.activity.ActivityComponentBuilder;
import com.trevjonez.inject.activity.ActivityComponentBuilderHost;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ChallengeApplication extends Application implements ActivityComponentBuilderHost {

    private ApplicationComponent appComponent;
    public static volatile Context applicationContext;
    @Inject Map<Class<? extends Activity>, Provider<ActivityComponentBuilder>> componentBuilders;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        setupDependencyInjection();
    }

    private void setupDependencyInjection() {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        appComponent.inject(this);
    }

    @Override
    public <A extends Activity, B extends ActivityComponentBuilder<A, ? extends PlainComponent<A>>> B getActivityComponentBuilder(Class<A> activityKey, Class<B> builderType) {
        return builderType.cast(componentBuilders.get(activityKey).get());
    }
}
