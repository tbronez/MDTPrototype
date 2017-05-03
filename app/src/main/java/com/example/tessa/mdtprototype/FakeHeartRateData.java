package com.example.tessa.mdtprototype;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.hardware.SensorEventListener;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;


public class FakeHeartRateData extends Service {

    /*public FakeHeartRateData() {
        super("FakeHeartRateData");
    }*/
    public int fakeHeartRate=45;
    public Boolean notStarted = true;
    public Intent intent2 = new Intent("heartrate");
    private Timer timer = new Timer();
    private static final long UPDATE_INTERVAL = 10 * 1000;
    private static final long DELAY_INTERVAL = 0;
    private Boolean decreasing = true;

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
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int month = cal.get(Calendar.MONTH)+1;
            String dateString = ""+cal.get(Calendar.YEAR)+"."+month+"."+cal.get(Calendar.DATE)+"";
            intent2.putExtra("time", dateString);
            sendBroadcast(intent2);
            String log = ""+dateString+" "+fakeHeartRate;
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

                        if (fakeHeartRate==32) {
                            decreasing = false;
                            fakeHeartRate = fakeHeartRate + 1;
                        } else if(fakeHeartRate==45) {
                            decreasing = true;
                            fakeHeartRate = fakeHeartRate - 1;
                        } else if(decreasing) {
                            fakeHeartRate = fakeHeartRate -1;
                        } else {
                            fakeHeartRate = fakeHeartRate +1;
                        }
                        intent2 = new Intent("heartrate");
                        Date date = new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int month = cal.get(Calendar.MONTH)+1;
                        String dateString = ""+cal.get(Calendar.YEAR)+"."+month+"."+cal.get(Calendar.DATE)+"";
                        intent2.putExtra("time", dateString);
                        intent2.putExtra("heartrate", fakeHeartRate);
                        sendBroadcast(intent2);
                    }
                },
                DELAY_INTERVAL,
                UPDATE_INTERVAL
        );

    }

}
