package com.example.app15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        // These 2 lines add a background image to the button labeled "BIG".
        // This is not demonstrated in the step-by-step video,
        // but the summary slide at the end of the video mentions that possibility.
        Button button_big = (Button) findViewById(R.id.button_big);
        button_big.setBackgroundResource(R.drawable.compass);
    }
}
