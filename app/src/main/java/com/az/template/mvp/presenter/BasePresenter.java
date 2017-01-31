package com.az.template.mvp.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created: Zorin A.
 * Date: 16.11.2016.
 */

abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> {


    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void unsubscribeOnDestroy(@NonNull Disposable subscription) {
        compositeDisposable.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

//    AppComponent getAppComponent() {
//        return MoxySampleApplication.getAppComponent();
//    }
}
