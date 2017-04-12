package com.example.tessa.mdtprototype;

import android.content.Context;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Tessa on 3/9/17.
 */

public class DataSummary extends WearableActivity {

    private String mFilename = "Symptom_Count";

    public ArrayList<String> getSymptomCount() {
    //public String getSymptomCount() {

        ArrayList<String> dataArrayList = new ArrayList<>();
        try {
            Log.d("tag","getSymptomCount");
            FileInputStream fis = openFileInput(mFilename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line = "";

            int c;
            String temp="";
            while( (c = fis.read()) != -1){
                temp = Character.toString((char)c);
                if (temp.equals(";")) {
                    dataArrayList.add(line);
                    line = "";
                } else line = line+temp;

            }


        }
        catch(FileNotFoundException e){

        }
        catch(IOException ex) {

        }
        return dataArrayList;
    }

    public void addToSymptomCount(String newData) {
        ArrayList<String> dataArrayList = getSymptomCount();
//        String dataArrayList = getSymptomCount();
        dataArrayList.add(newData);
//        dataArrayList = dataArrayList+" "+newData;
        String dataString = "";
        for (int i = 0; i<dataArrayList.size(); i++) {
            dataString = dataString+dataArrayList.get(i)+";";
        }
        Log.d("Storing",dataString);

        try {
            FileOutputStream fos = openFileOutput(mFilename, Context.MODE_PRIVATE);
            fos.write(dataString.getBytes());
            fos.close();
            Log.d("addToSymptomCount","succeeded");
        }
        catch(FileNotFoundException e){
            Log.d(DataSummary.class.getName(),"File not found");
        }
        catch(IOException ex) {
            ex.printStackTrace();
            Log.d(DataSummary.class.getName(),"exception");
        }

    }

    public void clearSymptomCount() {
        try {
            FileInputStream fis = openFileInput(mFilename);
            deleteFile(mFilename);
        }
        catch(FileNotFoundException e){

        }
        catch(IOException ex) {

        }
    }
}
