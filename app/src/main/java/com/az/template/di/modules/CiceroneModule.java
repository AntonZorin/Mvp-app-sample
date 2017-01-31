package com.az.template.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */

@Module
public class CiceroneModule {

    Cicerone<Router> cicerone;

    public CiceroneModule(Cicerone<Router> cicerone) {
        this.cicerone = cicerone;
    }

    @Provides
    Router provideRouter() {
        return cicerone.getRouter();
    }

    @Provides
    NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }
}
