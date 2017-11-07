
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 skydoves
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.skydoves.elasticviews;

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
