package com.example.javaprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtN1, edtN2;
    TextView txtSoma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtN1 = findViewById(R.id.edtN1);
        edtN2 = findViewById(R.id.edtN2);
        txtSoma = findViewById(R.id.txtMsg);
    }

    public void somar (View view)
    {   int n1, n2, s;
        n1 = Integer.parseInt(edtN1.getText().toString());
        n2 = Integer.parseInt(edtN2.getText().toString());
        s = n1 + n2;
        txtSoma.setText(String.format("Você digitou %d e %d. A soma é %d.", n1, n2, s));
    }
}

