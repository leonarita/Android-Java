package com.example.app9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
    }

    // Log.d exibe mensagem no console do Android Studio

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("SegundaActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("SegundaActivity", "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("SegundaActivity", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("SegundaActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("SegundaActivity", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("SegundaActivity", "onRestart");
    }
}
