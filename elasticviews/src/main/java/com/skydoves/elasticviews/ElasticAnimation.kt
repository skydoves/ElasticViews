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
@file:Suppress("unused")

package com.skydoves.elasticviews

import android.view.View
import android.view.ViewGroup
import android.view.animation.CycleInterpolator
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.skydoves.elasticviews.Definitions.DEFAULT_ANIMATION_ANCHOR
import com.skydoves.elasticviews.Definitions.DEFAULT_DURATION
import com.skydoves.elasticviews.Definitions.DEFAULT_SCALE_X
import com.skydoves.elasticviews.Definitions.DEFAULT_SCALE_Y

/** ElasticAnimation implements elastic animations for android views or view groups. */
class ElasticAnimation(private val view: View) {

  @JvmField
  @set:JvmSynthetic
  var scaleX = DEFAULT_SCALE_X

  @JvmField
  @set:JvmSynthetic
  var scaleY = DEFAULT_SCALE_Y

  @JvmField
  @set:JvmSynthetic
  var duration = DEFAULT_DURATION

  @JvmField
  @set:JvmSynthetic
  var listener: ViewPropertyAnimatorListener? = null

  @JvmField
  @set:JvmSynthetic
  var finishListener: ElasticFinishListener? = null

  var isAnimating: Boolean = false
    private set

  /** Sets a target elastic scale-x size of the animation. */
  fun setScaleX(scaleX: Float): ElasticAnimation = apply { this.scaleX = scaleX }

  /** Sets a target elastic scale-y size of the animation. */
  fun setScaleY(scaleY: Float): ElasticAnimation = apply { this.scaleY = scaleY }

  /** Sets a duration of the animation. */
  fun setDuration(duration: Int): ElasticAnimation = apply { this.duration = duration }

  /** Sets an animator listener of the animation. */
  fun setListener(listener: ViewPropertyAnimatorListener): ElasticAnimation = apply {
    this.listener = listener
  }

  /** An animator listener of the animation. */
  fun setOnFinishListener(finishListener: ElasticFinishListener?): ElasticAnimation = apply {
    this.finishListener = finishListener
  }

  /** An [ElasticFinishListener] listener of the animation. */
  @JvmSynthetic
  inline fun setOnFinishListener(crossinline block: () -> Unit): ElasticAnimation = apply {
    this.finishListener = ElasticFinishListener { block() }
  }

  /** starts elastic animation. */
  fun doAction() {
    if (!isAnimating && view.scaleX == 1f) {
      val animatorCompat = ViewCompat.animate(view)
        .setDuration(duration.toLong())
        .scaleX(scaleX)
        .scaleY(scaleY)
        .setInterpolator(CycleInterpolator(DEFAULT_ANIMATION_ANCHOR)).apply {
          listener?.let { setListener(it) } ?: setListener(object : ViewPropertyAnimatorListener {
            override fun onAnimationCancel(view: View?) = Unit
            override fun onAnimationStart(view: View?) {
              isAnimating = true
            }

            override fun onAnimationEnd(view: View?) {
              finishListener?.onFinished()
              isAnimating = false
            }
          })
        }
      if (view is ViewGroup) {
        (0 until view.childCount).map { view.getChildAt(it) }.forEach { child ->
          ViewCompat.animate(child)
            .setDuration(duration.toLong())
            .scaleX(scaleX)
            .scaleY(scaleY)
            .setInterpolator(CycleInterpolator(DEFAULT_ANIMATION_ANCHOR))
            .withLayer()
            .start()
        }
      }
      animatorCompat.withLayer().start()
    }
  }
}
