package com.example.tessa.mdtprototype;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HeartRateActivity extends WearableActivity {

    //private TextView hrTextView;
    //private SensorManager mSensorManager;
    //private Sensor mHeartRateSensor;
    //private int acc;
    //private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        //setAmbientEnabled();

        /*
        hrTextView = (TextView) findViewById(R.id.hrTextView);

        Intent intent = new Intent(this, FakeHeartRateData.class);
        intent.setAction("startSensor");
        startService(intent);

        Intent dataIntent = new Intent(this, FakeHeartRateData.class);
        dataIntent.setAction("getData");
        startService(dataIntent);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateHR(intent);
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("heartrate"));
        */

        /*
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if (mSensorManager.getSensorList(Sensor.TYPE_HEART_RATE).size()!=0) {
            mHeartRateSensor = mSensorManager.getSensorList(Sensor.TYPE_HEART_RATE).get(0);
            mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_FASTEST);
            hrTextView.setText(R.string.heart_rate_loading);
        }
        else
            debuggingText.setText("No sensor");
        */
    }
/*
    public void updateHR(Intent intent) {
        String time = intent.getStringExtra("time");
        int hr = intent.getIntExtra("heartrate",1);
        String hrString = ""+hr;
        if (hr >= 60) {
            hrTextView.setTextColor(getResources().getColor(R.color.green));
        } else if (hr >=50) {
            hrTextView.setTextColor(getResources().getColor(R.color.yellow));
        } else if (hr >=40) {
            hrTextView.setTextColor(getResources().getColor(R.color.orange));
        } else {
            hrTextView.setTextColor(getResources().getColor(R.color.red)); }
        hrTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_text));
        hrTextView.setText(hrString);
    }
*/
    /*
    public void onSensorChanged(SensorEvent event) {
        int hr = (int)event.values[0];
        //int acc = event.accuracy;

        String msg = "";
        if (hr >= 60) {
            hrTextView.setTextColor(getResources().getColor(R.color.green));
        } else if (hr >=50) {
            hrTextView.setTextColor(getResources().getColor(R.color.yellow));
        } else if (hr >=40) {
            hrTextView.setTextColor(getResources().getColor(R.color.orange));
        } else {
            hrTextView.setTextColor(getResources().getColor(R.color.red));
        }
        if (acc < 3) {
            msg = "Low accuracy";
            hrTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_text));
        } else {
            msg = "" + hr;
            hrTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.large_text));
        }
        hrTextView.setText(msg);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (sensor == mHeartRateSensor) {
            acc = accuracy;
            String printmsg = "" + acc;
            debuggingText.setText(printmsg);
        }

    }
    */

    protected void onResume() {
        super.onResume();
        //mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_FASTEST);
        //debuggingText.setText("Reregistered");
        //Intent intent = new Intent(this, FakeHeartRateData.class);
        //intent.setAction("startSensor");
        //startService(intent);
        //registerReceiver(broadcastReceiver, new IntentFilter("heartrate"));


    }

    protected void onPause() {
        super.onPause();
        //mSensorManager.unregisterListener(this);
        //debuggingText.setText("Unregistered");
        //unregisterReceiver(broadcastReceiver);
    }

    public void backToMenu(View v) {
        Log.d("HeartRateActivity","backToMenu");
        Intent openMainActivity= new Intent(HeartRateActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openMainActivity);
        finish();

    }

}
