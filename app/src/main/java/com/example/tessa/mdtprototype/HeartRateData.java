package com.example.tessa.mdtprototype;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Tessa on 3/11/17.
 */

public class HeartRateData extends IntentService implements SensorEventListener {

    public SensorManager mSensorManager;
    public Sensor mHeartRateSensor;
    public int heartrate;
    public int accuracy;
    public Intent intent = new Intent("heartrate");


    public HeartRateData() {
        super("HeartRateData");
    }

    public int getHeartrate() {
        return heartrate;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if (mSensorManager.getSensorList(Sensor.TYPE_HEART_RATE).size()!=0) {
            mHeartRateSensor = mSensorManager.getSensorList(Sensor.TYPE_HEART_RATE).get(0);
            /*HandlerThread mHandlerThread = new HandlerThread("sensorThread");
            mHandlerThread.start();
            Handler handler = new Handler(mHandlerThread.getLooper());
            mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_FASTEST, handler);*/
            mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
        //else
            //No sensor

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        heartrate = (int)event.values[0];
        if (heartrate<40) {
            Context context = getApplicationContext();
            CharSequence text = "Low Heart Rate";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        intent.putExtra("time", new Date().toLocaleString());
        intent.putExtra("heartrate", heartrate);
        sendBroadcast(intent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int acc) {
        accuracy = acc;
    }


    /* extends Service instead of IntentService
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
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    */
}