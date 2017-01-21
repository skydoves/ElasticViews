
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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.RelativeLayout;

import com.skydoves.elasticviewsexample.R;

public class ElasticLayout extends RelativeLayout {

    private View view;
    private OnClickListener listener;

    private int round = 3;
    private float scale = 0.9f;
    private int color = R.color.colorPrimary;
    private int duration = 500;

    public ElasticLayout(Context context){
        super(context);
        onCreate();
    }

    public ElasticLayout(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        onCreate();
        getAttrs(attributeSet);
    }

    public ElasticLayout(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
        onCreate();
        getAttrs(attributeSet, defStyle);
    }

    private void onCreate(){
        String inflaterService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(inflaterService);
        view = layoutInflater.inflate(R.layout.elasticlayout, this, false);
        addView(view);
        view.setClickable(true);
    }

    private void getAttrs(AttributeSet attrs)
    {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticLayout);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle)
    {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticLayout, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray){
        GradientDrawable bgShape = (GradientDrawable)view.getBackground();

        round = typedArray.getInt(R.styleable.ElasticLayout_layout_round, round);
        bgShape.setCornerRadius(round);

        color = typedArray.getInt(R.styleable.ElasticLayout_layout_backgroundColor, color);
        bgShape.setColor(color);

        scale = typedArray.getFloat(R.styleable.ElasticLayout_layout_scale, scale);

        duration = typedArray.getInt(R.styleable.ElasticLayout_layout_duration, duration);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(listener != null) {
                if(view.getScaleX() == 1) {
                    ViewCompat.animate(view).setDuration(duration).scaleX(scale).scaleY(scale).setInterpolator(new CycleInterpolator(0.5f))
                            .setListener(new ViewPropertyAnimatorListener() {

                                @Override
                                public void onAnimationStart(final View view) {
                                }

                                @Override
                                public void onAnimationEnd(final View v) {
                                    onClick();
                                }

                                @Override
                                public void onAnimationCancel(final View view) {
                                }
                            })
                            .withLayer()
                            .start();
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    private  void onClick(){
        listener.onClick(this);
    }

}
