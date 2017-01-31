package com.az.template.di.modules;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */

@Module
public class ActivityModule {
    AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }
}
