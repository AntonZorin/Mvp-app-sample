package com.az.template.di.components;

import com.az.template.di.modules.ActivityModule;
import com.az.template.di.modules.FragmentModule;
import com.az.template.di.scopes.ActivityScope;
import com.az.template.ui.activity.HomeActivity;

import dagger.Subcomponent;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    FragmentComponent providesFragmentComponent(FragmentModule fragmentModule);

    void inject(HomeActivity homeActivity);
}
