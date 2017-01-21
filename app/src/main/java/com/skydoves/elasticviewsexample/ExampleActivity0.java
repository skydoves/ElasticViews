package com.skydoves.elasticviewsexample;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.skydoves.elasticviewsexample.ElasticVIews.ElasticCheckButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExampleActivity0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example0);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.daybtn0, R.id.daybtn1, R.id.daybtn2, R.id.daybtn3, R.id.daybtn4, R.id.daybtn5, R.id.daybtn6})
    public void ElasticCheckButtons(View v){
        ElasticCheckButton elasticCheckButton = (ElasticCheckButton)v;
        Snackbar.make(v, "[Change checked state] " + elasticCheckButton.getText().toString() + " : " + elasticCheckButton.isChecked(), Snackbar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
    }

    @OnClick({R.id.layout_starttime, R.id.layout_endtime, R.id.layout_timeinterval})
    public void ElasticLayout(View v){
        Snackbar.make(v, "Pop-up likes 'TimePickerDialog'", Snackbar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
    }

    @OnClick({R.id.example0_ibtn_q_timeset01, R.id.example0_ibtn_q_timeset02})
    public void ElasticImageViews(View v){
        switch (v.getId()) {
            case R.id.example0_ibtn_q_timeset01 :
                Snackbar.make(v, "Alarm goes off between start-time and end-time", Snackbar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
                break;
            case R.id.example0_ibtn_q_timeset02 :
                Snackbar.make(v, "This is time interval description", Snackbar.LENGTH_LONG).setActionTextColor(Color.WHITE).show();
                break;
        }
    }

    @OnClick(R.id.elasticbtn_addalarm)
    public void addNewAlarm(View v){
        Toast.makeText(this, "a new Alarm added!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
