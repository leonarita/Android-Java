package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById -> procura elemento por elemento até encontrar, perdendo performance
    //    EditText valor = findViewById(R.id.edit_value);
    //    valor.getText();

        //ViewHolder -> proveio de ListView, sendo mais performático do que findViewById
        this.mViewHolder.editValue = findViewById(R.id.edit_value);
        this.mViewHolder.textDolar = findViewById(R.id.text_dolar);
        this.mViewHolder.textEuro = findViewById(R.id.text_euro);
        this.mViewHolder.buttonCalculate = findViewById(R.id.button_calculate);

        this.mViewHolder.buttonCalculate.setOnClickListener(this);
        this.mViewHolder.textDolar.setOnClickListener(this);

        this.clearValues();
    }

    private void test()
    {
        this.mViewHolder.editValue.setText("Olá!!!");
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.button_calculate)
        {
            String value = this.mViewHolder.editValue.getText().toString();

            if ("".equals(value))
            {
                Toast.makeText(this, this.getString(R.string.informe_valor), Toast.LENGTH_LONG).show();
            }
            else
            {
                Double real = Double.valueOf(value);

                this.mViewHolder.textDolar.setText(String.format("%.2f", (real/4)));
                this.mViewHolder.textEuro.setText(String.format("%.2f", (real/5)));
            }
        }
    }

    private void clearValues()
    {
        this.mViewHolder.textDolar.setText("");
        this.mViewHolder.textEuro.setText("");
    }

    private static class ViewHolder
    {
        EditText editValue;
        TextView textDolar;
        TextView textEuro;
        Button buttonCalculate;
    }
}
