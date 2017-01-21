
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
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.Button;

import com.skydoves.elasticviewsexample.R;

public class ElasticCheckButton extends Button {

    private Button view;
    private OnClickListener listener;

    private int round = 20;
    private float scale = 0.9f;
    private int color = R.color.colorPrimary;
    private int duration = 500;
    private float alpha = 0.7f;

    private String labelText = "";
    private int labelColor = Color.WHITE;
    private int labelSize = 10;
    private int labelStyle = 0;

    private boolean checked = false;

    public ElasticCheckButton(Context context){
        super(context);
        onCreate();
    }

    public ElasticCheckButton(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        onCreate();
        getAttrs(attributeSet);
    }

    public ElasticCheckButton(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
        onCreate();
        getAttrs(attributeSet, defStyle);
    }

    private void onCreate(){
        view = this;
        view.setAllCaps(false);
        view.setBackgroundResource(R.drawable.rectangle_checkbutton);
    }

    private void getAttrs(AttributeSet attrs)
    {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticCheckButton);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle)
    {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticCheckButton, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray){
        GradientDrawable bgShape = (GradientDrawable)view.getBackground();

        round = typedArray.getInt(R.styleable.ElasticCheckButton_checkbutton_round, round);
        bgShape.setCornerRadius(round);

        color = typedArray.getInt(R.styleable.ElasticCheckButton_checkbutton_backgroundColor, color);
        bgShape.setColor(color);

        scale = typedArray.getFloat(R.styleable.ElasticCheckButton_checkbutton_scale, scale);

        duration = typedArray.getInt(R.styleable.ElasticCheckButton_checkbutton_duration, duration);

        labelText = typedArray.getString(R.styleable.ElasticCheckButton_checkbutton_labelText);
        view.setText(labelText);

        labelColor = typedArray.getInt(R.styleable.ElasticCheckButton_checkbutton_labelColor, labelColor);
        view.setTextColor(labelColor);

        labelSize = typedArray.getInt(R.styleable.ElasticCheckButton_checkbutton_labelSize, labelSize);
        view.setTextSize(labelSize);

        labelStyle = typedArray.getInt(R.styleable.ElasticCheckButton_checkbutton_labelStyle, labelStyle);

        if(labelStyle == 0)
            view.setTypeface(null, Typeface.NORMAL);
        else if(labelStyle == 1)
            view.setTypeface(null, Typeface.BOLD);
        else if(labelStyle == 2)
            view.setTypeface(null, Typeface.ITALIC);

        alpha = typedArray.getFloat(R.styleable.ElasticCheckButton_checkbutton_alpha, alpha);

        checked = typedArray.getBoolean(R.styleable.ElasticCheckButton_checkbutton_ischecked, checked);
        if(checked) view.setAlpha(alpha);
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
                                    checked = !checked;
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
        if(checked) view.setAlpha(alpha);
        else view.setAlpha(1.0f);
        listener.onClick(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public boolean isChecked(){
        return checked;
    }
}
