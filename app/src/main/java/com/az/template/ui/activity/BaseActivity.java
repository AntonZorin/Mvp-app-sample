package com.az.template.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.az.template.R;
import com.az.template.di.components.ActivityComponent;
import com.az.template.di.components.ApplicationComponent;
import com.az.template.di.modules.ActivityModule;
import com.az.template.navigation.SupportFragmentNavigator;
import com.az.template.ui.application.TemplateApplication;
import com.az.template.ui.fragments.MainFragmentBuilder;
import com.az.template.ui.screens.Screens;

import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Command;

import static com.az.template.navigation.AnimationType.NO_ANIM;

/**
 * Created: Zorin A.
 * Date: 30.01.2017.
 */

public abstract class BaseActivity extends MvpAppCompatActivity {


    protected Navigator baseNavigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.activity_home_fragment_container, NO_ANIM) {
        @Override
        public void applyCommand(Command command) {
            super.applyCommand(command);
        }

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.MAIN_FRAGMENT_SCREEN:
                    return new MainFragmentBuilder().build();
            }
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(BaseActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };
    private ActivityComponent activityComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activityComponent = getApplicationComponent().providesActivityComponent(new ActivityModule(this));
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
    }

    ApplicationComponent getApplicationComponent() {
        return TemplateApplication.getApplicationComponent();
    }

    public abstract int getLayoutRes();

    public abstract void injectDependency();

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

}
