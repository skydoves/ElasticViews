
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

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import android.view.ViewGroup
import android.view.animation.CycleInterpolator

class ElasticAnimation(private val view: View) {

    private var scaleX = 0.7f
    private var scaleY = 0.7f
    private var duration = 400
    private var listener: ViewPropertyAnimatorListener? = null
    private var finishListener: ElasticFinishListener? = null

    fun setScaleX(scaleX: Float): ElasticAnimation = apply { this.scaleX = scaleX }
    fun setScaleY(scaleY: Float): ElasticAnimation = apply { this.scaleY = scaleY }
    fun setDuration(duration: Int): ElasticAnimation = apply { this.duration = duration }
    fun setListener(listener: ViewPropertyAnimatorListener): ElasticAnimation = apply { this.listener = listener }
    fun setOnFinishListener(finishListener: ElasticFinishListener): ElasticAnimation = apply { this.finishListener = finishListener }

    fun doAction() {
        val animatorCompat = ViewCompat.animate(view).setDuration(duration.toLong()).scaleX(scaleX).scaleY(scaleY).setInterpolator(CycleInterpolator(0.5f))
        listener?.let { animatorCompat.setListener(it) }
        finishListener?.let {
            animatorCompat.setListener(object : ViewPropertyAnimatorListener {
                override fun onAnimationEnd(view: View?) {
                    it.onFinished()
                }

                override fun onAnimationCancel(view: View?) {
                }

                override fun onAnimationStart(view: View?) {
                }
            })
        }

        if(view is ViewGroup) {
            for (index in 0 until view.childCount) {
                val nextChild = view.getChildAt(index)
                ViewCompat.animate(nextChild).setDuration(duration.toLong()).scaleX(scaleX).scaleY(scaleY).setInterpolator(CycleInterpolator(0.5f)).withLayer().start()
            }
        }
        animatorCompat.withLayer().start()
    }
}
