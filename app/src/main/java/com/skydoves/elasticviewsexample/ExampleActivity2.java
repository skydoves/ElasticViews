package com.skydoves.elasticviewsexample;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skydoves.elasticviewsexample.ElasticVIews.ElasticAction;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExampleActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.example2_view0, R.id.example2_view1, R.id.example2_view2, R.id.example2_view3, R.id.example2_imv, R.id.example2_fab})
    public void Views(View v){
        if(v.getId() == R.id.example2_view3){
            // set your duration time
            int duration = 500;

            // ElasticAction : doAction
            ElasticAction.doAction((ViewGroup)v, duration, 0.85f, 0.85f); // argument : ViewGroup, duration, scaleX, scaleY

            // PostDelayed : delay duration time
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after duration time
                }
            }, duration);
        }
        else if(v.getId() == R.id.example2_imv)
            Snackbar.make(v, "This is ElasticImageView", Snackbar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
        else if(v.getId() == R.id.example2_fab)
            Snackbar.make(v, "This is ElasticFloatActionButton", Snackbar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
    }
}
