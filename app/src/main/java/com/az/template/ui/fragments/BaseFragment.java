package com.az.template.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.az.template.di.components.ActivityComponent;
import com.az.template.di.components.FragmentComponent;
import com.az.template.di.modules.FragmentModule;
import com.az.template.ui.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

    private FragmentComponent fragmentComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        fragmentComponent = getActivityComponent()
                .providesFragmentComponent(new FragmentModule(this));
        injectDependencies();
        super.onCreate(savedInstanceState);

        FragmentArgs.inject(this); // read @Arg fields
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayoutRes(), container, false);
        injectViews(view);
        return view;
    }

    protected abstract int getLayoutRes();

    public abstract void injectDependencies();

    public void injectViews(View view) {
        ButterKnife.bind(this, view);
    }


    private ActivityComponent getActivityComponent() {
        return ((BaseActivity) getActivity()).getActivityComponent();
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }
}
