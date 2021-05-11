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
import android.view.View
import android.view.View.OnClickListener
import androidx.annotation.FloatRange
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatButton

@Suppress("unused")
class ElasticCheckButton @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = androidx.appcompat.R.attr.buttonStyle
) : AppCompatButton(context, attrs, defStyle), ElasticInterface {

  /** The target elastic scale size of the animation. */
  var scale = Definitions.DEFAULT_SCALE

  /** The default duration of the animation. */
  var duration = Definitions.DEFAULT_DURATION

  @FloatRange(from = 0.0, to = 1.0)
  var checkedAlpha = 0.5f
    set(value) {
      field = value
      updateElasticCheckButton()
    }

  @Px
  var cornerRadius = 0f

  var isChecked = false
    set(value) {
      field = value
      updateElasticCheckButton()
    }

  private var onClickListener: OnClickListener? = null
  private var onFinishListener: ElasticFinishListener? = null

  init {
    onCreate()
    when {
      attrs != null && defStyle != androidx.appcompat.R.attr.buttonStyle ->
        getAttrs(attrs, defStyle)
      attrs != null -> getAttrs(attrs)
    }
  }

  private fun onCreate() {
    this.isAllCaps = false
    super.setOnClickListener {
      this.isChecked = !this.isChecked
      elasticAnimation(this) {
        setDuration(this@ElasticCheckButton.duration)
        setScaleX(this@ElasticCheckButton.scale)
        setScaleY(this@ElasticCheckButton.scale)
        setOnFinishListener { invokeListeners() }
      }.doAction()
    }
  }

  private fun getAttrs(attrs: AttributeSet) {
    val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ElasticCheckButton)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
    val typedArray =
      context.obtainStyledAttributes(attrs, R.styleable.ElasticCheckButton, defStyle, 0)
    try {
      setTypeArray(typedArray)
    } finally {
      typedArray.recycle()
    }
  }

  private fun setTypeArray(typedArray: TypedArray) {
    this.scale = typedArray.getFloat(R.styleable.ElasticCheckButton_checkButton_scale, this.scale)
    this.duration =
      typedArray.getInt(R.styleable.ElasticCheckButton_checkButton_duration, this.duration)
    this.cornerRadius =
      typedArray.getDimension(
        R.styleable.ElasticCheckButton_checkButton_cornerRadius,
        this.cornerRadius
      )
    this.checkedAlpha =
      typedArray.getFloat(R.styleable.ElasticCheckButton_checkButton_alpha, this.checkedAlpha)
    this.isChecked =
      typedArray.getBoolean(R.styleable.ElasticCheckButton_checkButton_isChecked, this.isChecked)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    initializeBackground()
    updateElasticCheckButton()
  }

  private fun initializeBackground() {
    if (background is ColorDrawable) {
      background = GradientDrawable().apply {
        cornerRadius = this@ElasticCheckButton.cornerRadius
        setColor((background as ColorDrawable).color)
      }.mutate()
    }
  }

  private fun updateElasticCheckButton() {
    if (this.isChecked) {
      this.alpha = this.checkedAlpha
    }
  }

  override fun setOnClickListener(listener: OnClickListener?) {
    this.onClickListener = listener
  }

  override fun setOnFinishListener(listener: ElasticFinishListener?) {
    this.onFinishListener = listener
  }

  override fun setOnClickListener(block: (View) -> Unit) =
    setOnClickListener(OnClickListener(block))

  override fun setOnFinishListener(block: () -> Unit) =
    setOnFinishListener(ElasticFinishListener(block))

  private fun invokeListeners() {
    this.alpha = when (this.isChecked) {
      true -> this.checkedAlpha
      false -> 1.0f
    }
    this.onClickListener?.onClick(this)
    this.onFinishListener?.onFinished()
  }

  /** Builder class for creating [ElasticCheckButton]. */
  class Builder(context: Context) {
    private val elasticCheckButton = ElasticCheckButton(context)

    fun setScale(value: Float) = apply { this.elasticCheckButton.scale = value }
    fun setDuration(value: Int) = apply { this.elasticCheckButton.duration = value }
    fun setCornerRadius(@Px value: Float) = apply { this.elasticCheckButton.cornerRadius = value }

    @JvmSynthetic
    fun setOnClickListener(block: (View) -> Unit) = apply {
      setOnClickListener(OnClickListener(block))
    }

    fun setOnClickListener(value: OnClickListener) = apply {
      this.elasticCheckButton.setOnClickListener(value)
    }

    @JvmSynthetic
    fun setOnFinishListener(block: () -> Unit) = apply {
      setOnFinishListener(ElasticFinishListener(block))
    }

    fun setOnFinishListener(value: ElasticFinishListener) = apply {
      this.elasticCheckButton.setOnFinishListener(value)
    }

    fun build() = this.elasticCheckButton
  }
}
