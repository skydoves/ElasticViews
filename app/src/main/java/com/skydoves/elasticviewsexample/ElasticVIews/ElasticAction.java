
/*
 * Copyright (C) 2017 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.elasticviewsexample.ElasticVIews;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;

public class ElasticAction {

    private final static String TAG = "ElasticAction";

    public static void doAction(View view, int duration, float scaleX, float scaleY){
        try {
            if(view.getScaleX() == 1) {
                ViewCompat.animate(view).setDuration(duration).scaleX(scaleX).scaleY(scaleY).setInterpolator(new CycleInterpolator(0.5f))
                        .setListener(new ViewPropertyAnimatorListener() {

                            @Override
                            public void onAnimationStart(final View view) {
                            }

                            @Override
                            public void onAnimationEnd(final View v) {
                            }

                            @Override
                            public void onAnimationCancel(final View view) {
                            }
                        }).withLayer().start();
            }
        }
        catch (Exception e){
            Log.e(TAG, "only ViewGroups : likes RelativeLayout, LinearLayout, etc could doAction");
        }
    }

    public static void doAction(ViewGroup view, int duration, float scaleX, float scaleY){
        try {
            if(view.getScaleX() == 1) {
                ViewCompat.animate(view).setDuration(duration).scaleX(scaleX).scaleY(scaleY).setInterpolator(new CycleInterpolator(0.5f))
                        .setListener(new ViewPropertyAnimatorListener() {

                            @Override
                            public void onAnimationStart(final View view) {
                            }

                            @Override
                            public void onAnimationEnd(final View v) {
                            }

                            @Override
                            public void onAnimationCancel(final View view) {
                            }
                        }).withLayer().start();

                for (int index = 0; index < ((ViewGroup) view).getChildCount(); ++index) {
                    View nextChild = ((ViewGroup) view).getChildAt(index);
                    ViewCompat.animate(nextChild).setDuration(duration).scaleX(scaleX).scaleY(scaleY).setInterpolator(new CycleInterpolator(0.5f))
                            .setListener(new ViewPropertyAnimatorListener() {

                                @Override
                                public void onAnimationStart(final View view) {
                                }

                                @Override
                                public void onAnimationEnd(final View v) {
                                }

                                @Override
                                public void onAnimationCancel(final View view) {
                                }
                            }).withLayer().start();
                }
            }
        }
        catch (Exception e){
            Log.e(TAG, "only ViewGroups : likes RelativeLayout, LinearLayout, etc could doAction");
        }
    }
}
