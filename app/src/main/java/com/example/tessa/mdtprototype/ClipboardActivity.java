package com.example.tessa.mdtprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

public class ClipboardActivity extends SymptomActivity {

    public TextView summaryTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboard);

        summaryTextView = (TextView) findViewById(R.id.summaryTextView);
        writeSummary();

    }

    public void clearSymptomHistory(View v) {
        super.clearSymptomCount();
        writeSummary();
    }

    public void writeSummary() {
        int symptomCount = super.getSymptomCount();
        String symptomCountString = ""+symptomCount;
        if (symptomCount == 1) {
            summaryTextView.setText(symptomCountString + " time\r\n");
        } else summaryTextView.setText(symptomCountString+" times\r\n");
    }

    public void backToMenu(View v) {
        Intent openMainActivity= new Intent(ClipboardActivity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(openMainActivity);

        finish();
    }
}
