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
package com.skydoves.elasticviewsdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.skydoves.elasticviews.ElasticAnimation;
import java.util.ArrayList;

public class ExampleActivity1 extends AppCompatActivity {

  private ArrayList<ListViewItem> data = new ArrayList<>();
  private ListViewAdapter adapter;
  private ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_example1);

    adapter = new ListViewAdapter(this, R.layout.item, data);
    listView = findViewById(R.id.example1_listview);
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new ListViewItemClickListener());
  }

  public void ElasticFloatingButtons(View v) {
    ListViewItem listviewitem = new ListViewItem(data.size() + "");
    data.add(listviewitem);
    adapter.notifyDataSetChanged();
    listView.setSelection(data.size() - 1);
  }

  // ListView Item Touch Event
  private class ListViewItemClickListener implements AdapterView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View clickedView, final int pos, long id) {
      new ElasticAnimation(clickedView)
          .setScaleX(0.9f)
          .setScaleY(0.9f)
          .setDuration(400)
          .setOnFinishListener(
              () -> {
                // Do something after duration time
                Toast.makeText(getBaseContext(), "ListViewItem" + pos, Toast.LENGTH_SHORT).show();
              })
          .doAction();
    }
  }

  private class ListViewItem {
    private String content;

    public ListViewItem(String content) {
      this.content = content;
    }

    public String getContent() {
      return content;
    }
  }

  private class ListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ListViewItem> data;
    private int layout;

    public ListViewAdapter(Context context, int layout, ArrayList<ListViewItem> data) {
      this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      this.data = data;
      this.layout = layout;
    }

    @Override
    public int getCount() {
      return data.size();
    }

    @Override
    public String getItem(int position) {
      return data.get(position).getContent();
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

      if (convertView == null) convertView = inflater.inflate(layout, parent, false);
      ListViewItem listviewitem = data.get(position);

      TextView tv_title = convertView.findViewById(R.id.item_tv_title);
      tv_title.setText("ListViewItem" + listviewitem.getContent());

      TextView tv_content = convertView.findViewById(R.id.item_tv_content);
      tv_content.setText("This is ListViewItem" + listviewitem.getContent() + "'s content");

      return convertView;
    }
  }
}
