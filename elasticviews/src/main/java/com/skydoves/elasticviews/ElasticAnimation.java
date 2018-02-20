
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
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;

public class ElasticAnimation {

    private View view;
    private float scaleX;
    private float scaleY;
    private int duration;
    private ViewPropertyAnimatorListener listener;
    private ViewPropertyAnimatorCompat animatorCompat;

    private ElasticAnimation(Builder builder) {
        this.view = builder.view;
        this.scaleX = builder.scaleX;
        this.scaleY = builder.scaleY;
        this.duration = builder.duration;
        this.animatorCompat = ViewCompat.animate(view).setDuration(duration).scaleX(scaleX).scaleY(scaleY).setInterpolator(new CycleInterpolator(0.5f));

        if(builder.listener != null) {
            this.listener = builder.listener;
            this.animatorCompat.setListener(builder.listener);
        }
        if(builder.finishListener != null) {
            final ElasticFinishListener finishListener = builder.finishListener;

            this.animatorCompat.setListener(new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {
                }

                @Override
                public void onAnimationEnd(View view) {
                    finishListener.onFinished();
                }

                @Override
                public void onAnimationCancel(View view) {
                }
            });
        }
    }

    public void doAction() {
        if(view != null) {
            if (view instanceof ViewGroup) {
                for (int index = 0; index < ((ViewGroup) view).getChildCount(); ++index) {
                    View nextChild = ((ViewGroup) view).getChildAt(index);
                    ViewCompat.animate(nextChild).setDuration(duration).scaleX(scaleX).scaleY(scaleY).setInterpolator(new CycleInterpolator(0.5f)).withLayer().start();
                }
            }
            animatorCompat.withLayer().start();
        }
    }

    public static class Builder {
        private View view;
        private float scaleX = 0.7f;
        private float scaleY = 0.7f;
        private int duration = 400;
        private ViewPropertyAnimatorListener listener;
        private ElasticFinishListener finishListener;

        public Builder setView(View view) {
            if(view.getScaleX() == 1) {
                this.view = view;
            }
            return this;
        }

        public Builder setScaleX(float scaleX) {
            this.scaleX = scaleX;
            return this;
        }

        public Builder setScaleY(float scaleY) {
            this.scaleY = scaleY;
            return this;
        }

        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder setListener(ViewPropertyAnimatorListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setOnFinishListener(ElasticFinishListener finishListener) {
            this.finishListener = finishListener;
            return this;
        }

        public void doAction() {
            final ElasticAnimation animation = new ElasticAnimation(this);
            animation.doAction();
        }
    }
}
