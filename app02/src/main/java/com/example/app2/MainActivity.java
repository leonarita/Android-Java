package com.example.app2;

//Para adicionar a lista no spinner, é necessário criar um array em strings.xml e referênciá-los no texto de activity_main (em entries)

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

//The class extends the Android Activity class.
public class MainActivity extends AppCompatActivity {

    //This is the onCreate() method. It’s called when the activity is first created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView tells Android which layout the activity uses. In this case, it’s activity_main
        setContentView(R.layout.activity_main);
    }

    //The method must have a single parameter of type View.
    public void onClickFindBeer(View view) {

        //Get a reference to the TextView
        TextView brands = (TextView) findViewById(R.id.brands);

        //Get a reference to the Spinner
        Spinner color = (Spinner) findViewById(R.id.color);

        //Get the selected item in the Spinner
        String beerType = String.valueOf(color.getSelectedItem());

        //Display the selected item
        brands.setText(beerType);
        color.getSelectedItem();
    }
}
