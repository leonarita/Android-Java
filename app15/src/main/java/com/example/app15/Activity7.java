package com.example.app15;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.NumberPicker;

public class Activity7 extends AppCompatActivity {

    // This NumberPicker allows the user to choose the web page to display.
    NumberPicker possibilities;

    // webView to display the web pages
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        // retrieve references to the Views defined in the activity_main.xml
        possibilities = (NumberPicker) findViewById(R.id.numberPicker);
        webView = (WebView) findViewById(R.id.webView);

        // define the texts to be displayed by the number picker
        String[] possibilitiesStrings = {
                "Android",
                "Checklist text-input field",
                "Coursera",
                "Supélec"
        };

        // associate the defined texts to the number picker
        possibilities.setDisplayedValues(possibilitiesStrings);

        // the number picker offers 4 choices, indexed from 0 to 3
        // (length of the array of strings is 4)
        possibilities.setMinValue(0);
        possibilities.setMaxValue(possibilitiesStrings.length - 1);
    }

    public void navigate(View v) {

        // retrieve the index of the user's choice on the number picker
        int choice = possibilities.getValue();

        // when a link is clicked in the selected page, do not open a browser to display
        // the linked page, instead display it in the (same) web view (replacing
        // the initially selected page)
        webView.setWebViewClient(new WebViewClient());

        // load the appropriate web page
        if (choice == 0) {
            webView.loadUrl("file:///android_asset/android.html");
        } else if (choice == 1)
            webView.loadUrl("file:///android_asset/checklist.html");
        else if (choice == 2)
            webView.loadUrl("http://www.coursera.org");
        else if (choice == 3)
            webView.loadUrl("file:///android_asset/supelec.html");
    }
}
