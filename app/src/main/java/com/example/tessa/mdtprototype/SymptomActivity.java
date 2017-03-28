package com.example.tessa.mdtprototype;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SymptomActivity extends DataSummary {

    public int heartrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        setAmbientEnabled();
        //heartrate = 60; //TAKE THIS OUT
    }

    public void backToMenu(View v) {
        Intent openMainActivity= new Intent(SymptomActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openMainActivity);

        finish();
    }

    public void logSymptom(View v) {
        //when logSymptom is called, get the last recorded heart rate
        //boolean symptomatic = (heartrate < 50);
        super.addToSymptomCount();
        int newCount = super.getSymptomCount();
        String newCountString = ""+newCount;
        TextView symptomCount = (TextView) findViewById(R.id.symptomCount);
        if (newCount==1) {
            symptomCount.setText(newCountString+" symptom logged");
        } else symptomCount.setText(newCountString+" symptoms logged");
    }

}
