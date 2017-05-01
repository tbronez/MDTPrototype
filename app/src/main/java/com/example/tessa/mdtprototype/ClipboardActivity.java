package com.example.tessa.mdtprototype;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClipboardActivity extends SymptomActivity {

    private ListView clipboardListView;
    private ArrayList<String> dataArrayList;
    private ArrayAdapter<String> arrayAdapter;
    private RelativeLayout rl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboard);

        clipboardListView = (ListView) findViewById(R.id.clipboardListView);
        dataArrayList = new ArrayList<String>();
        rl = (RelativeLayout) findViewById(R.id.activity_clipboard);

        writeSummary();

    }

    public void clearSymptomHistory(View v) {
        super.clearSymptomCount();
        writeSummary();
    }

    public void writeSummary() {
        dataArrayList = super.getSymptomCount();
        //    String mystring = super.getSymptomCount();
        //    dataArrayList.add(mystring);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_textview, R.id.text1, dataArrayList);
        clipboardListView.setAdapter(arrayAdapter);

//        rl.removeAllViews();
//
//        for (int i = 0; i<dataArrayList.size(); i++) {
//            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//            RelativeLayout relativeLayout = new RelativeLayout(this);
//            TextView textView = new TextView(this);
//
//            textView.setId(i);
//            textView.setTextColor(Color.BLACK);
//            textView.setText(dataArrayList.get(i));
//
//            if(i!=0){
//                params1.addRule(RelativeLayout.BELOW, i-1);
//            }
//
//            relativeLayout.addView(textView,params1);
//            relativeLayout.addView(imageView,params2);
//            viewFlipper.add(relativeLayout);
//        }

    }

    public void backToMenu(View v) {
        Intent openMainActivity= new Intent(ClipboardActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openMainActivity);
        overridePendingTransition(0, 0);

        finish();
    }
}
