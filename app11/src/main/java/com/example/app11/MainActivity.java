package com.example.app11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtn(View view) {

        //Usando o NotificationCompat.Builder para criar uma notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(this);

        //Definindo o ícone que aparecerá na notificação
        b.setSmallIcon(R.mipmap.ic_launcher);

        //Definindo o título que aparecerá na notificação
        b.setContentTitle("Título da notificação");

        //Definindo o texto da notificação
        b.setContentText("Você foi notificado!!!");

        //Definindo o texto que aparecerá como complemento (é opcional)
        b.setSubText("Subtexto");

        NotificationManager n = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Emitindo a notificação para o android
        n.notify(1, b.build());
    }

    public void onClickShort(View view) {
        Toast t = Toast.makeText(MainActivity.this, "Toast curto criado", Toast.LENGTH_SHORT);
        t.show();
    }

    public void onClickLong(View view) {
        Toast t = Toast.makeText(MainActivity.this, "Toast longo criado", Toast.LENGTH_LONG);
        t.show();
    }
}
