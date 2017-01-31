package com.az.template.di.components;

import com.az.template.di.modules.ActivityModule;
import com.az.template.di.modules.ApplicationModule;
import com.az.template.di.scopes.ApplicationScope;
import com.az.template.mvp.presenter.HomeActivityPresenter;

import dagger.Component;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */
@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    ActivityComponent providesActivityComponent(ActivityModule activityModule);

    void inject(HomeActivityPresenter homeActivityPresenter);
}
