package com.example.app15;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Activity4 extends AppCompatActivity {

    MediaPlayer pianoMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        // create a player ready to play the piano.mp3 file from the res/raw folder
        pianoMusic = MediaPlayer.create(this, R.raw.piano);

        // retrieve a reference to the switch View defined in the activity_main.xml
        Switch switch_looping = (Switch) findViewById(R.id.switch_looping);

        // attach a listener to the switch to control the looping mode of the music player
        switch_looping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pianoMusic.setLooping(isChecked);
            }
        });
    }

    public void playMusic (View v) {
        pianoMusic.start();
    }

    public void pauseMusic (View v) {
        if(pianoMusic.isPlaying())
            pianoMusic.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pianoMusic.release();
    }
}
