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

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.elasticviews.ElasticAnimation
import com.skydoves.elasticviewsdemo.databinding.ActivityExample1Binding
import java.util.ArrayList

class ExampleActivity1 : AppCompatActivity() {

  private val data = ArrayList<ListViewItem>()
  private var adapter: ListViewAdapter? = null
  private var listView: ListView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val binding = ActivityExample1Binding.inflate(layoutInflater)
    setContentView(binding.root)

    adapter = ListViewAdapter(this, R.layout.item, data)
    listView = findViewById(R.id.example1_listView)
    listView!!.adapter = adapter
    listView!!.onItemClickListener = ListViewItemClickListener()
  }

  fun floatingButtons(v: View) {
    val listviewitem = ListViewItem(data.size.toString() + "")
    data.add(listviewitem)
    adapter!!.notifyDataSetChanged()
    listView!!.setSelection(data.size - 1)
  }

  // ListView Item Touch Event
  private inner class ListViewItemClickListener : AdapterView.OnItemClickListener {
    override fun onItemClick(adapterView: AdapterView<*>, clickedView: View, pos: Int, id: Long) {
      ElasticAnimation(clickedView)
        .setScaleX(0.9f)
        .setScaleY(0.9f)
        .setDuration(400)
        .setOnFinishListener {
          // Do something after duration time
          Toast.makeText(baseContext, "ListViewItem$pos", Toast.LENGTH_SHORT).show()
        }
        .doAction()
    }
  }

  private inner class ListViewItem(val content: String)

  private inner class ListViewAdapter(
    context: Context,
    private val layout: Int,
    private val data: ArrayList<ListViewItem>
  ) : BaseAdapter() {

    private val inflater: LayoutInflater =
      context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
      return data.size
    }

    override fun getItem(position: Int): String {
      return data[position].content
    }

    override fun getItemId(position: Int): Long {
      return position.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
      var view = convertView

      if (view == null) view = inflater.inflate(layout, parent, false)
      val listviewitem = data[position]

      val tv_title = view!!.findViewById<TextView>(R.id.item_tv_title)
      tv_title.text = "ListViewItem" + listviewitem.content

      val tv_content = view.findViewById<TextView>(R.id.item_tv_content)
      tv_content.text = "This is ListViewItem" + listviewitem.content + "'s content"

      return view
    }
  }
}
