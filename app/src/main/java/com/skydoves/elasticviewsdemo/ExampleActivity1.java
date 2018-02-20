package com.skydoves.elasticviewsdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skydoves.elasticviews.ElasticAnimation;
import com.skydoves.elasticviews.ElasticFinishListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Developed by skydoves on 2017-01-21.
 * Copyright (c) 2017 skydoves rights reserved.
 */

public class ExampleActivity1 extends AppCompatActivity {

    private ArrayList<Listviewitem> data;
    private ListviewAdapter adapter;

    @Bind(R.id.example1_listview)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example1);
        ButterKnife.bind(this);

        data=new ArrayList();
        adapter=new ListviewAdapter(this, R.layout.item, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewItemClickListener());
    }

    @OnClick(R.id.example1_fab)
    public void ElasticFloatingButtons(View v){
        Listviewitem listviewitem = new Listviewitem(data.size()+"");
        data.add(listviewitem);
        adapter.notifyDataSetChanged();
        listView.setSelection(data.size()-1);
    }

    // ListView Item Touch Event
    private class ListViewItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View clickedView, final int pos, long id) {
            new ElasticAnimation.Builder().setView(clickedView).setScaleX(0.9f).setScaleY(0.9f).setDuration(400)
                    .setOnFinishListener(new ElasticFinishListener() {
                        @Override
                        public void onFinished() {
                            //Do something after duration time
                            Toast.makeText(getBaseContext(), "ListViewItem" + pos, Toast.LENGTH_SHORT).show();
                        }
                    }).doAction();
        }
    }

    private class Listviewitem {
        private String content;
        public String getContent(){return content;}

        public Listviewitem(String content){
            this.content = content;
        }
    }

    private class ListviewAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private ArrayList<Listviewitem> data;
        private int layout;

        public ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data){
            this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.data=data;
            this.layout=layout;
        }

        @Override
        public int getCount(){return data.size();}

        @Override
        public String getItem(int position){return data.get(position).getContent();}

        @Override
        public long getItemId(int position){return position;}

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){

            if(convertView==null)
                convertView=inflater.inflate(layout,parent,false);
            Listviewitem listviewitem =data.get(position);

            TextView tv_title = convertView.findViewById(R.id.item_tv_title);
            tv_title.setText("ListViewItem" + listviewitem.getContent());

            TextView tv_content = convertView.findViewById(R.id.item_tv_content);
            tv_content.setText("This is ListViewItem" +listviewitem.getContent()+ "'s content");

            return convertView;
        }
    }
}
