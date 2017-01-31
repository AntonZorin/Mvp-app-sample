package com.az.template.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.az.template.R;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;
import ru.terrakok.cicerone.commands.SystemMessage;

import static com.az.template.navigation.AnimationType.FADE_ANIM;
import static com.az.template.navigation.AnimationType.NO_ANIM;

/**
 * Created: Zorin A.
 * Date: 31.01.2017.
 */

public abstract class SupportFragmentNavigator implements Navigator {
    private FragmentManager fragmentManager;
    private int containerId;
    private int inAnim;
    private int outAnim;

    /**
     * Creates SupportFragmentNavigator.
     *
     * @param fragmentManager support fragment manager
     * @param containerId     id of the fragments container layout
     * @param animationId     type of animation
     */
    public SupportFragmentNavigator(FragmentManager fragmentManager, int containerId, @AnimationType int animationId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        setAnimationTransaction(animationId);
    }

    public void setAnimationTransaction(int anim) {
        switch (anim) {
            case NO_ANIM:
                inAnim = NO_ANIM;
                outAnim = NO_ANIM;
                break;
            case FADE_ANIM:
                inAnim = R.anim.fade_in;
                outAnim = R.anim.fade_out;
                break;
        }
    }

    @Override
    public void applyCommand(Command command) {

        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (inAnim != NO_ANIM && outAnim != NO_ANIM) {
            transaction.setCustomAnimations(inAnim, outAnim);
        }

        if (command instanceof Forward) {
            Forward forward = (Forward) command;
            transaction.replace(containerId, createFragment(forward.getScreenKey(), forward.getTransitionData()))
                    .addToBackStack(forward.getScreenKey())
                    .commit();
        } else if (command instanceof Back) {
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStackImmediate();
            } else {
                exit();
            }
        } else if (command instanceof Replace) {
            Replace replace = (Replace) command;
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStackImmediate();
                transaction.replace(containerId, createFragment(replace.getScreenKey(), replace.getTransitionData()))
                        .addToBackStack(replace.getScreenKey())
                        .commit();
            } else {
                transaction.replace(containerId, createFragment(replace.getScreenKey(), replace.getTransitionData()))
                        .commit();
            }
        } else if (command instanceof BackTo) {
            String key = ((BackTo) command).getScreenKey();

            if (key == null) {
                backToRoot();
            } else {
                boolean hasScreen = false;
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                    if (key.equals(fragmentManager.getBackStackEntryAt(i).getName())) {
                        fragmentManager.popBackStackImmediate(key, 0);
                        hasScreen = true;
                        break;
                    }
                }
                if (!hasScreen) {
                    backToUnexisting();
                }
            }
        } else if (command instanceof SystemMessage) {
            showSystemMessage(((SystemMessage) command).getMessage());
        }
    }

    private void backToRoot() {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
        fragmentManager.executePendingTransactions();
    }

    /**
     * Creates Fragment matching {@code screenKey}.
     *
     * @param screenKey screen key
     * @param data      initialization data
     * @return instantiated fragment for the passed screen key
     */
    protected abstract Fragment createFragment(String screenKey, Object data);

    /**
     * Shows system message.
     *
     * @param message message to show
     */
    protected abstract void showSystemMessage(String message);

    /**
     * Called when we try to back from the root.
     */
    protected abstract void exit();

    /**
     * Called when we tried to back to some specific screen, but didn't found it.
     */
    protected void backToUnexisting() {
        backToRoot();
    }
}
