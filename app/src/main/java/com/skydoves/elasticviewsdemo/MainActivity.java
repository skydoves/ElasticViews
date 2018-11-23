package com.skydoves.elasticviewsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Developed by skydoves on 2017-01-21.
 * Copyright (c) 2017 skydoves rights reserved.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ElasticButtons(View v) {
        switch (v.getId()){
            case R.id.elasticbtn0 :
                startActivity(new Intent(getBaseContext(), ExampleActivity0.class));
                break;
            case R.id.elasticbtn1 :
                startActivity(new Intent(getBaseContext(), ExampleActivity1.class));
                break;
            case R.id.elasticbtn2 :
                startActivity(new Intent(getBaseContext(), ExampleActivity2.class));
                break;
        }
    }
}
