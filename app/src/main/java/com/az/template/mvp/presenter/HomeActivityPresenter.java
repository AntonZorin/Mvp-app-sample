package com.az.template.mvp.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.az.template.di.qualifiers.ApplicationContext;
import com.az.template.events.SwitchFragmentEvent;
import com.az.template.mvp.view.HomeActivityView;
import com.az.template.ui.application.TemplateApplication;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */
@InjectViewState
public class HomeActivityPresenter extends BasePresenter<HomeActivityView> {

    @Inject
    Router router;
    @Inject
    NavigatorHolder navigatorHolder;
    @Inject
    @ApplicationContext
    Context context;
    @Inject
    EventBus bus;

    public HomeActivityPresenter() {
        TemplateApplication.getApplicationComponent().inject(this);
    }

    //region overrides
    @Override
    public void attachView(HomeActivityView view) {
        super.attachView(view);
        registerBus();
    }

    @Override
    public void detachView(HomeActivityView view) {
        unregisterBus();
        super.detachView(view);
    }
    //endregion

    //region subscribe
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchFragmentEvent(SwitchFragmentEvent event) {
        if (event.getData() == null) {
            switchFragment(event.getScreen());
        } else {
            switchFragment(event.getScreen(), event.getData());
        }
    }
    //endregion

    //region methods
    private void registerBus() {
        if (!bus.isRegistered(this)) {
            bus.register(this);
        }
    }

    private void unregisterBus() {
        if (bus.isRegistered(this)) {
            bus.unregister(this);
        }
    }

    public void setNavigator(Navigator navigator) {
        navigatorHolder.setNavigator(navigator);
    }

    public void removeNavigator() {
        navigatorHolder.removeNavigator();
    }

    public void switchFragment(String screen, Object data) {
        router.navigateTo(screen, "data");
    }

    public void switchFragment(String screen) {
        router.navigateTo(screen);
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().performStartEvent();
        super.onFirstViewAttach();
    }

    //endregion
}
