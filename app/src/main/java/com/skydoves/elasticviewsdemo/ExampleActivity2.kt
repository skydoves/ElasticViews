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
package com.skydoves.elasticviewsdemo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.skydoves.elasticviews.ElasticAnimation
import com.skydoves.elasticviewsdemo.databinding.ActivityExample2Binding

class ExampleActivity2 : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityExample2Binding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  fun views(v: View) {
    when (v.id) {
      R.id.example2_view3 ->
        ElasticAnimation(v)
          .setScaleX(0.85f)
          .setScaleY(0.85f)
          .setDuration(500)
          .setOnFinishListener {
            // Do something after duration time
          }
          .doAction()
      R.id.example2_imv -> Snackbar.make(v, "This is ElasticImageView", 200).setActionTextColor(
        Color.WHITE
      ).show()
      R.id.example2_textView0 -> ElasticAnimation(v).setScaleX(0.75f).setScaleY(0.75f).setDuration(
        500
      ).doAction()
      R.id.example2_fab ->
        Snackbar.make(v, "This is ElasticFloatActionButton", 200)
          .setActionTextColor(Color.WHITE)
          .show()
    }
  }
}
