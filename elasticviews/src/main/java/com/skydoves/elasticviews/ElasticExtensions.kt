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
@file:JvmName("ElasticExtensions")
@file:JvmMultifileClass

package com.skydoves.elasticviews

import android.view.View

@DslMarker
internal annotation class ElasticDsl

/**
 * An extension for operating elastic animation to the target view with custom attributes.
 *
 * @param scaleX The target elastic scale-x size of the animation.
 * @param scaleY The target elastic scale-y size of the animation.
 * @param duration The duration of the animation.
 * @param listener The [ElasticFinishListener] for being notified when the animation is finished.
 */
@ElasticDsl
@JvmOverloads
@JvmSynthetic
fun View.elasticAnimation(
  scaleX: Float = Definitions.DEFAULT_SCALE_X,
  scaleY: Float = Definitions.DEFAULT_SCALE_Y,
  duration: Int = Definitions.DEFAULT_DURATION,
  listener: ElasticFinishListener? = null
): ElasticAnimation {
  return ElasticAnimation(this)
    .setScaleX(scaleX)
    .setScaleY(scaleY)
    .setDuration(duration)
    .setOnFinishListener(listener)
}

/**
 * An extension for operating elastic animation to the target view with custom attributes.
 *
 * @param scaleX The target elastic scale-x size of the animation.
 * @param scaleY The target elastic scale-y size of the animation.
 * @param duration The duration of the animation.
 * @param block The lambda for being notified when the animation is finished.
 */
@ElasticDsl
@JvmOverloads
@JvmSynthetic
inline fun View.elasticAnimation(
  scaleX: Float = Definitions.DEFAULT_SCALE_X,
  scaleY: Float = Definitions.DEFAULT_SCALE_Y,
  duration: Int = Definitions.DEFAULT_DURATION,
  crossinline block: () -> Unit
): ElasticAnimation {
  return ElasticAnimation(this)
    .setScaleX(scaleX)
    .setScaleY(scaleY)
    .setDuration(duration)
    .setOnFinishListener(ElasticFinishListener { block() })
}

/**
 * An extension for creating elastic animation with kotlin dsl style.
 *
 * @param block The dsl block of the [ElasticAnimation].
 *
 * @return A new instance of the [ElasticAnimation].
 */
@ElasticDsl
@JvmSynthetic
inline fun elasticAnimation(
  view: View,
  crossinline block: ElasticAnimation.() -> Unit
): ElasticAnimation = ElasticAnimation(view).apply(block)
