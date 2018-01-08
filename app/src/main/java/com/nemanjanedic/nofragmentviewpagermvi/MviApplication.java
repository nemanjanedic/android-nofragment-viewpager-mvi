package com.nemanjanedic.nofragmentviewpagermvi;

import android.app.Application;
import android.content.Context;

import com.nemanjanedic.nofragmentviewpagermvi.di.DependencyInjection;

public class MviApplication extends Application {

    protected DependencyInjection dependencyInjection = new DependencyInjection();

    public static DependencyInjection getDependencyInjection(Context context) {
        return ((MviApplication) context.getApplicationContext()).dependencyInjection;
    }
}
