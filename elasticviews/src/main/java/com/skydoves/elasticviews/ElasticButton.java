
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
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.Button;

public class ElasticButton extends AppCompatButton {

    private Button view;
    private View.OnClickListener listener;

    private int round = 20;
    private float scale = 0.9f;
    private int color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
    private int duration = 500;

    private String labelText = "";
    private int labelColor = Color.WHITE;
    private int labelSize = 10;
    private int labelStyle = 0;

    public ElasticButton(Context context){
        super(context);
        onCreate();
    }

    public ElasticButton(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        onCreate();
        getAttrs(attributeSet);
    }

    public ElasticButton(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
        onCreate();
        getAttrs(attributeSet, defStyle);
    }

    private void onCreate(){
        view = this;
        view.setAllCaps(false);
        view.setBackgroundResource(R.drawable.rectangle_button);
    }

    private void getAttrs(AttributeSet attrs)
    {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticButton);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle)
    {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticButton, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray){
        GradientDrawable bgShape = (GradientDrawable)view.getBackground();

        round = typedArray.getInt(R.styleable.ElasticButton_button_round, round);
        bgShape.setCornerRadius(round);

        color = typedArray.getInt(R.styleable.ElasticButton_button_backgroundColor, color);
        bgShape.setColor(color);

        scale = typedArray.getFloat(R.styleable.ElasticButton_button_scale, scale);

        duration = typedArray.getInt(R.styleable.ElasticButton_button_duration, duration);

        labelText = typedArray.getString(R.styleable.ElasticButton_button_labelText);
        view.setText(labelText);

        labelColor = typedArray.getInt(R.styleable.ElasticButton_button_labelColor, labelColor);
        view.setTextColor(labelColor);

        labelSize = typedArray.getInt(R.styleable.ElasticButton_button_labelSize, labelSize);
        view.setTextSize(labelSize);

        labelStyle = typedArray.getInt(R.styleable.ElasticButton_button_labelStyle, labelStyle);

        if(labelStyle == 0)
            view.setTypeface(null, Typeface.NORMAL);
        else if(labelStyle == 1)
            view.setTypeface(null, Typeface.BOLD);
        else if(labelStyle == 2)
            view.setTypeface(null, Typeface.ITALIC);
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

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    private  void onClick(){
        listener.onClick(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
