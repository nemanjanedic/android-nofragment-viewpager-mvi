package com.nemanjanedic.nofragmentviewpagermvi;

import android.app.Application;
import android.content.Context;

import com.nemanjanedic.nofragmentviewpagermvi.di.DependencyInjection;

import timber.log.Timber;

public class MviApplication extends Application {

    protected DependencyInjection dependencyInjection = new DependencyInjection();

    {
        Timber.plant(new Timber.DebugTree());
    }

    public static DependencyInjection getDependencyInjection(Context context) {
        return ((MviApplication) context.getApplicationContext()).dependencyInjection;
    }
}
