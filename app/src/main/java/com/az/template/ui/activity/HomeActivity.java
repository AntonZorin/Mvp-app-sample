package com.az.template.ui.activity;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.az.template.R;
import com.az.template.mvp.presenter.HomeActivityPresenter;
import com.az.template.mvp.view.HomeActivityView;
import com.az.template.ui.screens.Screens;

import timber.log.Timber;

public class HomeActivity extends BaseActivity implements HomeActivityView {

    @InjectPresenter(type = PresenterType.GLOBAL)
    HomeActivityPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.switchFragment(Screens.MAIN_FRAGMENT_SCREEN);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    public void injectDependency() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.removeNavigator();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setNavigator(baseNavigator);
    }

    @Override
    public void performStartEvent() {
        if(!presenter.isInRestoreState(this)){
            Timber.i("presenter.isInRestoreState(this)"); //perform first app action
        }
    }
}
