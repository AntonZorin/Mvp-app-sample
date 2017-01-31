package com.az.template.ui.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.az.template.BuildConfig;
import com.az.template.di.components.ApplicationComponent;
import com.az.template.di.components.DaggerApplicationComponent;
import com.az.template.di.modules.ApiModule;
import com.az.template.di.modules.ApplicationModule;
import com.az.template.di.modules.CiceroneModule;

import ru.terrakok.cicerone.Cicerone;
import timber.log.Timber;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */

public class TemplateApplication extends Application {
    public static TemplateApplication INSTANCE;
    private static ApplicationComponent appComponent;

    public static ApplicationComponent getApplicationComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        INSTANCE = this;
        super.onCreate();
        appComponent = DaggerApplicationComponent
                .builder()
                .apiModule(new ApiModule())
                .applicationModule(new ApplicationModule(this))
                .ciceroneModule(new CiceroneModule(Cicerone.create()))
                .build();

        if (BuildConfig.DEBUG) { //init Timber
            Timber.plant(new Timber.DebugTree());
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
