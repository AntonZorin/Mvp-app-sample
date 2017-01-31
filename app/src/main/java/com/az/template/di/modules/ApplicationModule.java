package com.az.template.di.modules;

import android.app.Application;
import android.content.Context;

import com.az.template.di.qualifiers.ApplicationContext;
import com.az.template.di.scopes.ApplicationScope;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */
@Module(includes = {ApiModule.class, PresenterModule.class, CiceroneModule.class})
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    EventBus provideEventBus() {
        return EventBus.builder().throwSubscriberException(false).eventInheritance(true).build();
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    Context provideApplicationContext() {
        return application;
    }
}
