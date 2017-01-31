package com.az.template.di.components;

import com.az.template.di.modules.FragmentModule;
import com.az.template.di.scopes.FragmentScope;
import com.az.template.ui.fragments.MainFragment;

import dagger.Subcomponent;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */
@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(MainFragment mainFragment);
}
