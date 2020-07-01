package com.example.app15;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity1 extends AppCompatActivity implements View.OnClickListener {

    // the 2 buttons defined in the activity_main.xml file
    Button buttonBlue, buttonPink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        // retrieve reference to the View defined in the activity_main.xml
        buttonBlue = (Button) findViewById(R.id.button_blueInvisible);

        // create the button
        buttonPink = new Button(getApplicationContext());

        // "configure" the button: text, color...
        buttonPink.setText("The Pink Panther's To Do List");
        buttonPink.setTextColor(Color.BLACK);
        buttonPink.setBackgroundColor(Color.parseColor("#FD9BF3")); // light pink

        // set the size and position of the button:
        // width: as large as the screen
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        // position: below the blue button
        layoutParams.addRule(RelativeLayout.BELOW, buttonBlue.getId());

        // add the button to the relative layout defined in activity_main.xml
        RelativeLayout relativeLayout_mainScreen = (RelativeLayout) findViewById(R.id.relativeLayout_mainScreen);
        relativeLayout_mainScreen.addView(buttonPink, layoutParams);

        // attach the current activity as a click listener for both buttons
        buttonBlue.setOnClickListener(this);
        buttonPink.setOnClickListener(this);
    }

    public void toDo(View v) {
        if (v.equals(buttonBlue))
            v.setVisibility(View.INVISIBLE);

        if (v.equals(buttonPink))
            Toast.makeText(getApplicationContext(),"to do to do to do...", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        toDo(v);
    }
}
