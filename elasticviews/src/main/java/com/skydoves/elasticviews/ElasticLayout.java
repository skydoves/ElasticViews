
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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.RelativeLayout;

public class ElasticLayout extends RelativeLayout {

    private View view;
    private OnClickListener listener;

    private int round = 3;
    private float scale = 0.9f;
    private int color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
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
                    for(int i=0; i<this.getChildCount(); i++) {
                        ViewCompat.animate(this.getChildAt(i)).setDuration(duration).scaleX(scale).scaleY(scale).setInterpolator(new CycleInterpolator(0.5f))
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
                                })
                                .withLayer()
                                .start();
                    }

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
