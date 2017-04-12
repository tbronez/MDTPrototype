package com.example.tessa.mdtprototype;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.hardware.SensorEventListener;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;


public class FakeHeartRateData extends Service {

    /*public FakeHeartRateData() {
        super("FakeHeartRateData");
    }*/
    public int fakeHeartRate=0;
    //public Intent mintent = new Intent("fakeHeartRate");
    public Intent intent2 = new Intent("heartrate");

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals("startSensor")) {
            Log.d("tag","Intent to startSensor");
            fakeHeartRate = 60;
            // start timer
        } else if (intent.getAction().equals("getData")) {
            intent2.putExtra("heartrate",fakeHeartRate);
            intent2.putExtra("time", new Date().toLocaleString());
            sendBroadcast(intent2);
            String log = "Intent to get data "+fakeHeartRate;
            Log.d("tag",log);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
