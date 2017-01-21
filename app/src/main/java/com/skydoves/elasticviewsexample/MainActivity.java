package com.skydoves.elasticviewsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.elasticbtn0, R.id.elasticbtn1, R.id.elasticbtn2})
    public void ElasticButtons(View v) {
        switch (v.getId()){
            case R.id.elasticbtn0 :
                startActivity(new Intent(this, ExampleActivity0.class));
                break;
            case R.id.elasticbtn1 :
                startActivity(new Intent(this, ExampleActivity1.class));
                break;
            case R.id.elasticbtn2 :
                startActivity(new Intent(this, ExampleActivity2.class));
                break;
        }
    }
}
