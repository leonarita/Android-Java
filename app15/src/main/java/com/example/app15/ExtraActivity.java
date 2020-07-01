package com.example.app15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ExtraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        // get the Intent which caused the start of this activity
        Intent caller = getIntent();
        // get the rating passed by the first activity
        float rating = caller.getFloatExtra("nbStars", 0);

        // retrieve a reference to the View defined in the activity_second.xml
        TextView textView = (TextView) findViewById(R.id.textView);
        // set the text of the textView
        textView.setText("Welcome to the second activity! Your rating: " + rating);
    }
}
