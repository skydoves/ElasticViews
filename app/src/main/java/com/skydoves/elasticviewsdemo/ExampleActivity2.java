package com.skydoves.elasticviewsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.skydoves.elasticviews.ElasticAnimation;
import com.skydoves.elasticviews.ElasticFinishListener;

/** Developed by skydoves on 2017-01-21. Copyright (c) 2017 skydoves rights reserved. */
public class ExampleActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example2);
    }

    public void Views(View v) {
        if (v.getId() == R.id.example2_view3) {
            new ElasticAnimation(v)
                    .setScaleX(0.85f)
                    .setScaleY(0.85f)
                    .setDuration(500)
                    .setOnFinishListener(
                            new ElasticFinishListener() {
                                @Override
                                public void onFinished() {
                                    // Do something after duration time
                                }
                            })
                    .doAction();
        } else if (v.getId() == R.id.example2_imv)
            Snackbar.make(v, "This is ElasticImageView", 200)
                    .setActionTextColor(Color.WHITE)
                    .show();
        else if (v.getId() == R.id.example2_textView0)
            new ElasticAnimation(v).setScaleX(0.75f).setScaleY(0.75f).setDuration(500).doAction();
        else if (v.getId() == R.id.example2_fab)
            Snackbar.make(v, "This is ElasticFloatActionButton", 200)
                    .setActionTextColor(Color.WHITE)
                    .show();
    }
}
