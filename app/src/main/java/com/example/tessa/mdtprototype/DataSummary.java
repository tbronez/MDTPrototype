package com.example.tessa.mdtprototype;

import android.content.Context;
import android.support.wearable.activity.WearableActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Tessa on 3/9/17.
 */

public class DataSummary extends WearableActivity {

    private int symptomCount = 0;
    private String symptomCountStr = "0";
    private String mFilename = "Symptom_Count";

    public int getSymptomCount() {

        try {
            FileInputStream fis = openFileInput(mFilename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("");
            }
            symptomCountStr = sb.toString();
            symptomCount = Integer.parseInt(symptomCountStr);
        }
        catch(FileNotFoundException e){

        }
        catch(IOException ex) {

        }
        return symptomCount;
    }

    public void addToSymptomCount() {
        symptomCount = getSymptomCount() +1;
        symptomCountStr = ""+symptomCount;
        try {
            FileOutputStream fos = openFileOutput(mFilename, Context.MODE_PRIVATE);
            fos.write(symptomCountStr.getBytes());
            fos.close();
        }
        catch(FileNotFoundException e){

        }
        catch(IOException ex) {

        }

    }

    public void clearSymptomCount() {
        try {
            FileInputStream fis = openFileInput(mFilename);
            deleteFile(mFilename);
            symptomCount = 0;
            symptomCountStr = "0";
        }
        catch(FileNotFoundException e){

        }
        catch(IOException ex) {

        }
    }
}
