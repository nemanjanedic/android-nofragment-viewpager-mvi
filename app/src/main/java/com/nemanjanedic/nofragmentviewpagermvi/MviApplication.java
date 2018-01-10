package com.nemanjanedic.nofragmentviewpagermvi;

import android.app.Application;
import android.content.Context;

import com.nemanjanedic.nofragmentviewpagermvi.di.DependencyInjection;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public class MviApplication extends Application {

    protected DependencyInjection dependencyInjection = new DependencyInjection();

    {
        Timber.plant(new Timber.DebugTree());
    }

    public static DependencyInjection getDependencyInjection(Context context) {
        return ((MviApplication) context.getApplicationContext()).dependencyInjection;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
