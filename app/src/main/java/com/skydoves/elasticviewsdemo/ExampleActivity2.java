package com.skydoves.elasticviewsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.skydoves.elasticviews.ElasticAnimation;
import com.skydoves.elasticviews.ElasticFinishListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Developed by skydoves on 2017-01-21.
 * Copyright (c) 2017 skydoves rights reserved.
 */

public class ExampleActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.example2_view0, R.id.example2_view1, R.id.example2_view2, R.id.example2_view3, R.id.example2_imv, R.id.example2_textView0, R.id.example2_fab})
    public void Views(View v) {
        if(v.getId() == R.id.example2_view3) {
            new ElasticAnimation.Builder().setView(v).setScaleX(0.85f).setScaleY(0.85f).setDuration(500).setOnFinishListener(new ElasticFinishListener() {
                @Override
                public void onFinished() {
                    // Do something after duration time
                }
            }).doAction();
        }
        else if(v.getId() == R.id.example2_imv)
            Snackbar.make(v, "This is ElasticImageView", Snackbar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
        else if(v.getId() == R.id.example2_textView0)
            new ElasticAnimation.Builder().setView(v).setScaleX(0.75f).setScaleY(0.75f).setDuration(500).doAction();
        else if(v.getId() == R.id.example2_fab)
            Snackbar.make(v, "This is ElasticFloatActionButton", Snackbar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
    }
}
