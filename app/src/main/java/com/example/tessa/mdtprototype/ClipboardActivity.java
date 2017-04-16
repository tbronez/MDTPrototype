package com.example.tessa.mdtprototype;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class ClipboardActivity extends SymptomActivity {

    private ListView clipboardListView;
    private ArrayList<String> dataArrayList;
    private ArrayAdapter<String> arrayAdapter;
    private RelativeLayout rl;
    ViewFlipper viewFlipper;
    private int initial_count=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboard);

        //clipboardListView = (ListView) findViewById(R.id.clipboardListView);
        //dataArrayList = new ArrayList<String>();
        //rl = (RelativeLayout) findViewById(R.id.activity_clipboard);
        viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper);
        //writeSummary();
        //for (int counter = 1;counter<=4;counter++) {
//        displayView();//
        //}
    }

    public void clearSymptomHistory(View v) {
        super.clearSymptomCount();
        writeSummary();
    }

    public void writeSummary() {
        dataArrayList = super.getSymptomCount(); // access data
        //    String mystring = super.getSymptomCount();
        //    dataArrayList.add(mystring);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArrayList);//
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

    public void displayView () {
        LayoutInflater inflater = (LayoutInflater) this //creates layout inflator
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout, null); //creates new view by inflating testlayout
        for (int i = 1;i<=4;i++) {
            viewFlipper.addView(view); //adds inflated view to viewflipper
            TextView heart_rate = (TextView) view.findViewById(R.id.heart_rate_variable);
            //ArrayList<String> calendar_list = super.getSymptomCount();
            //heart_rate.setText(calendar_list.get(i));
            heart_rate.setText(String.format("%d",initial_count));
            initial_count++;
        }
    }

    public void onButtonDown (View v){
        viewFlipper.setInAnimation(this, R.anim.low_to_high_enter);
        viewFlipper.setOutAnimation(this,R.anim.low_to_high_out);
        viewFlipper.showNext();
    }

    public void onButtonUp (View v){
        viewFlipper.setInAnimation(this, R.anim.high_to_low_enter);
        viewFlipper.setOutAnimation(this,R.anim.high_to_low_out);
        viewFlipper.showPrevious();
    }


    public void backToMenu(View v) {
        Intent openMainActivity= new Intent(ClipboardActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openMainActivity);

        finish();
    }
}
