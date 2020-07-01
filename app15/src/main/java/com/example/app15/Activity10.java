package com.example.app15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class Activity10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_10);

        Button btn1,btn2;
        final EditText et1,et2;

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double miles = Double.parseDouble(et1.getText().toString());
                double km = miles/.62137;
                DecimalFormat format = new DecimalFormat("##.##");
                et2.setText(format.format(km));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double km = Double.parseDouble(et2.getText().toString());
                double miles = km * (.62137);
                DecimalFormat format = new DecimalFormat("##.##");
                et1.setText(format.format(miles));

            }
        });
    }
}
