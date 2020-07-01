package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button[] simplePanelButtons = new Button[8];
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNumberGuessGame = (Button) findViewById(R.id.btnNumberGuessGame);

        simplePanelButtons[0] = findViewById(R.id.btn1);
        simplePanelButtons[1] = findViewById(R.id.bt2);
        simplePanelButtons[2] = findViewById(R.id.bt3);
        simplePanelButtons[3] = findViewById(R.id.bt4);
        simplePanelButtons[4] = findViewById(R.id.bt5);
        simplePanelButtons[5] = findViewById(R.id.bt6);
        simplePanelButtons[6] = findViewById(R.id.bt7);
        simplePanelButtons[7] = findViewById(R.id.bt8);

        for (int i=0; i < simplePanelButtons.length; i++)
        {
            simplePanelButtons[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (mediaPlayer!=null && mediaPlayer.isPlaying())
                        mediaPlayer.stop();

                    mediaPlayer = MediaPlayer.create(getBaseContext(), 1);
                    mediaPlayer.start();
                }
            });
        }

        btnNumberGuessGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, NumberGuessGame.class));
            }
        });
    }
}
