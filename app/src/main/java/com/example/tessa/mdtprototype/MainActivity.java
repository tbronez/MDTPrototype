package com.example.tessa.mdtprototype;

import android.content.Intent;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends WearableActivity {

    private BoxInsetLayout mContainerView;
    private ImageButton hrButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        hrButton = (ImageButton) findViewById(R.id.hrButton);

        // if (service is not started) {
            // startService(HeartRateData intent) }
        Intent intent = new Intent(this, FakeHeartRateData.class);
        intent.setAction("startSensor");
        startService(intent);

        Log.d("tag", "Started Main");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, FakeHeartRateData.class);
        intent.setAction("startSensor");
        startService(intent);
    }

    public void onHRClick(View v){
        Intent intent = new Intent(this,HeartRateActivity.class);
        startActivity(intent);
    }

    public void onClipboardClick(View v){
        Intent intent = new Intent(this,ClipboardActivity.class);
        startActivity(intent);
    }

    public void onSymptomClick(View v){
        Intent intent = new Intent(this,SymptomActivity.class);
        startActivity(intent);
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        //updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        //updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        //updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            //mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));

        } else {
            //mContainerView.setBackground(null);
        }
    }
}
