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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.skydoves.elasticviews.ElasticCheckButton
import com.skydoves.elasticviewsdemo.databinding.ActivityExample0Binding

class ExampleActivity0 : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityExample0Binding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  fun checkButtons(v: View) {
    val elasticCheckButton = v as ElasticCheckButton
    Snackbar.make(
      v,
      "[Change checked state] " +
        elasticCheckButton.text.toString() +
        " : " +
        elasticCheckButton.isChecked,
      200
    )
      .setActionTextColor(Color.WHITE)
      .show()
  }

  fun layout(v: View) {
    Snackbar.make(v, "Pop-up likes 'TimePickerDialog'", 200).setActionTextColor(Color.WHITE).show()
  }

  fun imageViews(v: View) {
    when (v.id) {
      R.id.example0_ibtn_q_timeset01 ->
        Snackbar.make(
          v,
          "Alarm goes off between start-time and end-time", 200
        )
          .setActionTextColor(Color.WHITE)
          .show()
      R.id.example0_ibtn_q_timeset02 ->
        Snackbar.make(v, "This is time interval description", 200)
          .setActionTextColor(Color.WHITE)
          .show()
    }
  }

  fun addNewAlarm(v: View) {
    Toast.makeText(baseContext, "a new Alarm added!", Toast.LENGTH_SHORT).show()
    finish()
  }
}
