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
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.appcompat.widget.AppCompatButton

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ElasticButton : AppCompatButton {

  var scale = 0.9f
  var duration = 500
  var cornerRadius = 0f

  private var onClickListener: OnClickListener? = null
  private var onFinishListener: ElasticFinishListener? = null

  constructor(context: Context) : super(context) {
    onCreate()
  }

  constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
    onCreate()
    getAttrs(attributeSet)
  }

  constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context,
    attributeSet, defStyle) {
    onCreate()
    getAttrs(attributeSet, defStyle)
  }

  private fun onCreate() {
    this.isAllCaps = false
    super.setOnClickListener {
      if (this.scaleX == 1f) {
        elasticAnimation(this) {
          setDuration(this@ElasticButton.duration)
          setScaleX(this@ElasticButton.scale)
          setScaleY(this@ElasticButton.scale)
          setOnFinishListener { invokeListeners() }
        }.doAction()
      }
    }
  }

  private fun getAttrs(attrs: AttributeSet) {
    val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ElasticButton)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
    val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ElasticButton, defStyle, 0)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun setTypeArray(typedArray: TypedArray) {
    this.scale = typedArray.getFloat(R.styleable.ElasticButton_button_scale, this.scale)
    this.duration = typedArray.getInt(R.styleable.ElasticButton_button_duration, this.duration)
    this.cornerRadius =
      typedArray.getDimension(R.styleable.ElasticButton_button_cornerRadius, this.cornerRadius)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    initializeBackground()
  }

  private fun initializeBackground() {
    if (this.background is ColorDrawable) {
      val drawable = GradientDrawable()
      drawable.cornerRadius = this@ElasticButton.cornerRadius
      drawable.setColor((this.background as ColorDrawable).color)
      this.background = drawable
    }
  }

  override fun setOnClickListener(listener: OnClickListener?) {
    this.onClickListener = listener
  }

  fun setOnFinishListener(listener: ElasticFinishListener) {
    this.onFinishListener = listener
  }

  private fun invokeListeners() {
    this.onClickListener?.onClick(this)
    this.onFinishListener?.onFinished()
  }

  /** Builder class for creating [ElasticButton]. */
  class Builder(context: Context) {
    private val elasticButton = ElasticButton(context)

    fun setScale(value: Float) = apply { this.elasticButton.scale = value }
    fun setDuration(value: Int) = apply { this.elasticButton.duration = value }
    fun setCornerRadius(value: Float) = apply { this.elasticButton.cornerRadius = value }
    fun setOnClickListener(block: () -> Unit) = apply {
      val onClickListener = OnClickListener { block() }
      this.elasticButton.setOnClickListener(onClickListener)
    }

    fun setOnClickListener(value: OnClickListener) = apply {
      this.elasticButton.setOnClickListener(value)
    }

    fun setOnFinishListener(block: () -> Unit) = apply {
      val onElasticFinishListener = object : ElasticFinishListener {
        override fun onFinished() {
          block()
        }
      }
      this.elasticButton.setOnFinishListener(onElasticFinishListener)
    }

    fun setOnFinishListener(value: ElasticFinishListener) = apply {
      this.elasticButton.setOnFinishListener(value)
    }

    fun build() = this.elasticButton
  }
}
