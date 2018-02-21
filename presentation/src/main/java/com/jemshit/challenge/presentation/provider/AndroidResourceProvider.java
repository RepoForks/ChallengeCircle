package com.jemshit.challenge.presentation.provider;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class AndroidResourceProvider implements ResourceProvider{
    private final Context context;

    @Inject
    public AndroidResourceProvider(@Named("AppContext") Context context) {
        this.context = context;
    }

    @Override public <KEY_TYPE> String getString(KEY_TYPE resourceIdentifier) {
        return context.getString((Integer) resourceIdentifier);
    }

    @Override public <KEY_TYPE> int getInteger(KEY_TYPE resourceIdentifier) {
        return context.getResources().getInteger((Integer) resourceIdentifier);
    }

    @Override public <KEY_TYPE> boolean getBoolean(KEY_TYPE resourceIdentifier) {
        return context.getResources().getBoolean((Integer) resourceIdentifier);
    }

    @Override public <KEY_TYPE> int getColor(KEY_TYPE resourceIdentifier) {
        return ContextCompat.getColor(context, (Integer) resourceIdentifier);
    }
}