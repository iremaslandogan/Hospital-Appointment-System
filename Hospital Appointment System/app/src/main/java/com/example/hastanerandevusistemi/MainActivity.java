package com.example.hastanerandevusistemi;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Context context;
    private TextView TC,Sifre;
    private Button giris;
    DataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        TC = (EditText) findViewById(R.id.girisTc);
        Sifre = (EditText) findViewById(R.id.girisSifre);

        giris = (Button) findViewById(R.id.btnGiris);


        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tc = TC.getText().toString();
                String sifre = Sifre.getText().toString();
                String admin="Admin";
                String hasta="Hasta";
                String yonetici="Yönetici";
                String doktor="Doktor";

                Intent intocan = new Intent(MainActivity.this, hastaanasayfa.class);
                startActivity(intocan);
                /*String storedrol=loginDataBaseAdapter.giris(tc,sifre);
                if(admin.equals(storedrol)){
                    Intent intocan = new Intent(MainActivity.this, admin.class);
                    startActivity(intocan);
                }
                else if(hasta.equals(storedrol)){
                    Intent intocan = new Intent(MainActivity.this, hastaanasayfa.class);
                    startActivity(intocan);
                }
                else if(doktor.equals(storedrol)){
                    Intent intocan = new Intent(MainActivity.this, admin.class);
                    startActivity(intocan);
                }
                else if(yonetici.equals(storedrol)){
                    Intent intocan = new Intent(MainActivity.this, admin.class);
                    startActivity(intocan);

                }
                else {
                    Toast.makeText(context, "TC yada Sifreniz yanlış", Toast.LENGTH_LONG).show();
                }*/


            }
        });

    }

    public void kayitsayfayonlendir(View view) {
        Intent intocan = new Intent(MainActivity.this, kayitol.class);
        startActivity(intocan);
    }


}
