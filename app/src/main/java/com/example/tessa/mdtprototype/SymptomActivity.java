package com.example.tessa.mdtprototype;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SymptomActivity extends DataSummary {

    public int heartrate;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        setAmbientEnabled();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("tag","Got the data");
                readyToLog(intent);
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("heartrate"));
    }

    public void backToMenu(View v) {
        Intent openMainActivity= new Intent(SymptomActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openMainActivity);

        finish();
    }

    public void toClipboard() {
        Intent openClipboardActivity= new Intent(SymptomActivity.this, ClipboardActivity.class);
        //openClipboardActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        openClipboardActivity.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(openClipboardActivity);
        this.overridePendingTransition(0,0);

        finish();
    }

    public void logSymptom(View v) {
        //when logSymptom is called, get the last recorded heart rate
        //boolean symptomatic = (heartrate < 50);
        //Vibrator vb = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        //vb.vibrate(100);
        Intent dataIntent = new Intent(this, FakeHeartRateData.class);
        dataIntent.setAction("getData");
        startService(dataIntent);

    }

    public void readyToLog(Intent intent) {
        String time = intent.getStringExtra("time");
        heartrate = intent.getIntExtra("heartrate",1);
        String data = time+": "+heartrate;
        super.addToSymptomCount(data);
        toClipboard();
    }

    public void clearLog(View v) {
        super.clearSymptomCount();
        Log.d("SymptomActivity","clearLog");
    }

    protected void onResume() {
        super.onResume();
        //mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_FASTEST);
        //debuggingText.setText("Reregistered");
        registerReceiver(broadcastReceiver, new IntentFilter("heartrate"));

    }

    protected void onPause() {
        super.onPause();
        //mSensorManager.unregisterListener(this);
        //debuggingText.setText("Unregistered");
        unregisterReceiver(broadcastReceiver);
    }

}
