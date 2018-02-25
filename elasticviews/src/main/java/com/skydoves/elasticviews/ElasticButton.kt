
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

package com.skydoves.elasticviews

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button

class ElasticButton : AppCompatButton {

    private lateinit var view: Button
    private var listener: View.OnClickListener? = null
    private var onFinishListener: ElasticFinishListener? = null

    private var round = 20
    private var scale = 0.9f
    private var color = ContextCompat.getColor(context, R.color.colorPrimary)
    private var duration = 500

    private var labelText: String? = ""
    private var labelColor = Color.WHITE
    private var labelSize = 10
    private var labelStyle = 0

    constructor(context: Context) : super(context) {
        onCreate()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        onCreate()
        getAttrs(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
        onCreate()
        getAttrs(attributeSet, defStyle)
    }

    private fun onCreate() {
        view = this
        view.setAllCaps(false)
        view.setBackgroundResource(R.drawable.rectangle_button)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ElasticButton)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ElasticButton, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val bgShape = view.background as GradientDrawable

        round = typedArray.getInt(R.styleable.ElasticButton_button_round, round)
        bgShape.cornerRadius = round.toFloat()

        color = typedArray.getInt(R.styleable.ElasticButton_button_backgroundColor, color)
        bgShape.setColor(color)

        scale = typedArray.getFloat(R.styleable.ElasticButton_button_scale, scale)

        duration = typedArray.getInt(R.styleable.ElasticButton_button_duration, duration)

        labelText = typedArray.getString(R.styleable.ElasticButton_button_labelText)
        view.text = labelText

        labelColor = typedArray.getInt(R.styleable.ElasticButton_button_labelColor, labelColor)
        view.setTextColor(labelColor)

        labelSize = typedArray.getInt(R.styleable.ElasticButton_button_labelSize, labelSize)
        view.textSize = labelSize.toFloat()

        labelStyle = typedArray.getInt(R.styleable.ElasticButton_button_labelStyle, labelStyle)

        when(labelStyle) {
            0 -> view.setTypeface(null, Typeface.NORMAL)
            1 -> view.setTypeface(null, Typeface.BOLD)
            2 -> view.setTypeface(null, Typeface.ITALIC)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if(listener != null || onFinishListener != null) {
                if (view.scaleX == 1f) {
                    ElasticAnimation(this).setDuration(duration).setScaleX(scale).setScaleY(scale).setOnFinishListener(object : ElasticFinishListener {
                        override fun onFinished() {
                            onClick()
                        }
                    }).doAction()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    fun setOnFinishListener(listener: ElasticFinishListener) {
        this.onFinishListener = listener
    }

    private fun onClick() {
        listener?.onClick(this)
        onFinishListener?.onFinished()
    }
}
