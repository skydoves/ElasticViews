package com.skydoves.elasticviewsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.skydoves.elasticviews.ElasticCheckButton;

/** Developed by skydoves on 2017-01-21. Copyright (c) 2017 skydoves rights reserved. */
public class ExampleActivity0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example0);
    }

    public void ElasticCheckButtons(View v) {
        ElasticCheckButton elasticCheckButton = (ElasticCheckButton) v;
        Snackbar.make(
                        v,
                        "[Change checked state] "
                                + elasticCheckButton.getText().toString()
                                + " : "
                                + elasticCheckButton.isChecked(),
                        200)
                .setActionTextColor(Color.WHITE)
                .show();
    }

    public void ElasticLayout(View v) {
        Snackbar.make(v, "Pop-up likes 'TimePickerDialog'", 200)
                .setActionTextColor(Color.WHITE)
                .show();
    }

    public void ElasticImageViews(View v) {
        switch (v.getId()) {
            case R.id.example0_ibtn_q_timeset01:
                Snackbar.make(v, "Alarm goes off between start-time and end-time", 200)
                        .setActionTextColor(Color.WHITE)
                        .show();
                break;
            case R.id.example0_ibtn_q_timeset02:
                Snackbar.make(v, "This is time interval description", 200)
                        .setActionTextColor(Color.WHITE)
                        .show();
                break;
        }
    }

    public void addNewAlarm(View v) {
        Toast.makeText(getBaseContext(), "a new Alarm added!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
