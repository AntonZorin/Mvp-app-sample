package com.az.template.navigation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created: Zorin A.
 * Date: 31.01.2017.
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef(value = {AnimationType.NO_ANIM, AnimationType.FADE_ANIM})
public @interface AnimationType {
    int NO_ANIM = 0;
    int FADE_ANIM = 1;


}
