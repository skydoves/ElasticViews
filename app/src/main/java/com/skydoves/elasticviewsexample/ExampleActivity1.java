package com.skydoves.elasticviewsexample;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skydoves.elasticviewsexample.ElasticVIews.ElasticAction;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExampleActivity1 extends AppCompatActivity {

    ArrayList<Listviewitem> data;
    ListviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example1);
        ButterKnife.bind(this);

        data=new ArrayList();
        adapter=new ListviewAdapter(this, R.layout.item, data);
        ListView listView=(ListView)findViewById(R.id.example1_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListViewItemClickListener());
    }

    @OnClick(R.id.example1_fab)
    public void ElasticFloatingButtons(View v){
        Listviewitem listviewitem = new Listviewitem(data.size()+"");
        data.add(listviewitem);
        adapter.notifyDataSetChanged();
    }

    // ListView Item Touch Event
    private class ListViewItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View clickedView, final int pos, long id)
        {
            // set your duration time
            int duration = 400;

            // ElasticAction : doAction
            ElasticAction.doAction((ViewGroup)clickedView, duration, 0.9f, 0.9f); // argument : ViewGroup, duration, scaleX, scaleY

            // PostDelayed : delay duration time
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after duration time
                    Toast.makeText(getBaseContext(), "ListViewItem" + pos, Toast.LENGTH_SHORT).show();
                }
            }, duration);
        }
    };

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

            TextView tv_title = (TextView)convertView.findViewById(R.id.item_tv_title);
            tv_title.setText("ListViewItem" + listviewitem.getContent());

            TextView tv_content = (TextView)convertView.findViewById(R.id.item_tv_content);
            tv_content.setText("This is ListViewItem" +listviewitem.getContent()+ "'s content");

            return convertView;
        }
    }

}
