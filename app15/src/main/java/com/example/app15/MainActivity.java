package com.example.app15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void interagir(View view) {
        Intent intent = new Intent(MainActivity.this, Activity1.class);
        startActivity(intent);
    }

    public void Listar(View view) {
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        startActivity(intent);
    }

    public void brincar(View view) {
        Intent intent = new Intent(MainActivity.this, Activity3.class);
        startActivity(intent);
    }

    public void ouvir(View view) {
        Intent intent = new Intent(MainActivity.this, Activity4.class);
        startActivity(intent);
    }

    public void brincar2(View view) {
        Intent intent = new Intent(MainActivity.this, Activity5.class);
        startActivity(intent);
    }

    public void visualizar(View view) {
        Intent intent = new Intent(MainActivity.this, Activity6.class);
        startActivity(intent);
    }

    public void pesquisar(View view) {
        Intent intent = new Intent(MainActivity.this, Activity7.class);
        startActivity(intent);
    }

    public void criar(View view) {
        Intent intent = new Intent(MainActivity.this, Activity8.class);
        startActivity(intent);
    }

    public void enviarMsg(View view) {
        Intent intent = new Intent(MainActivity.this, Activity9.class);
        startActivity(intent);
    }

    public void converter(View view) {
        Intent intent = new Intent(MainActivity.this, Activity10.class);
        startActivity(intent);
    }

    public void onClickButton(View view) {
        String url = "http://api.fixer.io/latest?base=USD";
        System.out.println(new DownloadTask().execute(url));
    }


    // Classe de apoio
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                return downloadContent(params[0]);
            } catch (IOException e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Ao terminar de carregar, irá ler o conteúdo e disparar o Toast com o valor do dolar
            try {
                JSONObject parent_obj = new JSONObject(result).getJSONObject("rates");
                Toast.makeText(MainActivity.this, "Dolar está " + parent_obj.getString("BRL"), Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String downloadContent(String myurl) throws IOException {
            InputStream is = null;
            int length = 500;

            // Realiza o request e retorna o resultado como String
            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(10000);     //milliseconds
                conn.setConnectTimeout(15000);  //milliseconds
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                int response = conn.getResponseCode();
                is = conn.getInputStream();

                // Converte o InputStream para uma String
                String contentAsString = convertInputStreamToString(is, length);
                return contentAsString;
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        public String convertInputStreamToString(InputStream stream, int length) throws IOException, UnsupportedEncodingException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[length];
            reader.read(buffer);
            return new String(buffer);
        }
    }
}
