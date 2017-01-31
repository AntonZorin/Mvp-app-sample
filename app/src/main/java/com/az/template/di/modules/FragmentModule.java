package com.az.template.di.modules;

import android.support.v4.app.Fragment;

import dagger.Module;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */
@Module
public class FragmentModule {
    Fragment fragment;
    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }
}
