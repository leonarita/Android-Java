package com.example.app15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Activity9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);
    }

    public void sendMessage(View v) {

        // retrieve the text typed in by the user in the EditText
        String message = ((EditText)(findViewById(R.id.editText_message))).getText().toString();

        // create an implicit intent to any app with SENDTO capability
        // set the destination (5556 is the phone number of the second emulator instance)

        Uri destination = Uri.parse("smsto:5556");

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, destination);

        // pass the composed message to the messaging activity
        smsIntent.putExtra("sms_body", message);

        // launch the intent
        startActivity(smsIntent);
    }
}
