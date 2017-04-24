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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;


public class FakeHeartRateData extends Service {

    /*public FakeHeartRateData() {
        super("FakeHeartRateData");
    }*/
    public int fakeHeartRate=60;
    public Boolean notStarted = true;
    public Intent intent2 = new Intent("heartrate");
    private Timer timer = new Timer();
    private static final long UPDATE_INTERVAL = 2 * 1000;
    private static final long DELAY_INTERVAL = 0;

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
            if (notStarted) {
                notStarted = false;
                startHR();
            }
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

    public void startHR() {

        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        if (fakeHeartRate>30) {
                            fakeHeartRate = fakeHeartRate - 1;
                        } else {
                            fakeHeartRate = fakeHeartRate + 2;
                        }
                        intent2.putExtra("time", new Date().toLocaleString());
                        intent2.putExtra("heartrate", fakeHeartRate);
                        sendBroadcast(intent2);
                    }
                },
                DELAY_INTERVAL,
                UPDATE_INTERVAL
        );

    }

}
