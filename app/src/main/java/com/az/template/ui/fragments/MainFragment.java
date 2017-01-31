package com.az.template.ui.fragments;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.az.template.R;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */

@FragmentWithArgs
public class MainFragment extends BaseFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public void injectDependencies() {
        getFragmentComponent().inject(this);
    }
}
