package com.example.tessa.mdtprototype;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends WearableActivity {

    private BoxInsetLayout mContainerView;
    private TextView hrButton;
    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        Intent openIntent = new Intent(this,HeartRateActivity.class);
        //openIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openIntent);

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        hrButton = (TextView) findViewById(R.id.hrButton);

        Log.d("tag", "Started Main");

        Intent startHeart = new Intent(this,FakeHeartRateData.class);
        startHeart.setAction("startSensor");
        startService(startHeart);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter("heartrate"));
        Intent intent = new Intent(this, FakeHeartRateData.class);
        intent.setAction("getData");
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    public void onHRClick(View v){
        Intent openIntent = new Intent(this,HeartRateActivity.class);
        openIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openIntent);
    }

    public void onClipboardClick(View v){
        Intent openIntent = new Intent(this,ClipboardActivity.class);
        startActivity(openIntent);
    }

    public void onSymptomClick(View v){
        Intent openIntent = new Intent(this,SymptomActivity.class);
        startActivity(openIntent);
    }

    public void updateHR(Intent intent) {
        String time = intent.getStringExtra("time");
        int hr = intent.getIntExtra("heartrate",1);
        String hrString = ""+hr;
        hrButton.setText(hrString);
    }
}
