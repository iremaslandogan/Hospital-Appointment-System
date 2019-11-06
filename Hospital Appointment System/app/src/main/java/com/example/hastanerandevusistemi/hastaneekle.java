package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class hastaneekle extends AppCompatActivity {

    EditText hastaneAd,Sehir,Adres;
    Button hastanekaydet;
    DataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hastaneekle);

        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        hastaneAd = (EditText) findViewById(R.id.hastanead);
        Sehir = (EditText) findViewById(R.id.hastanesehir);
        Adres = (EditText) findViewById(R.id.hastaneadres);

        hastanekaydet = (Button) findViewById(R.id.btnhastaneekleme);


        hastanekaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hastanead = hastaneAd.getText().toString();
                String sehir = Sehir.getText().toString();
                String adres = Adres.getText().toString();

                loginDataBaseAdapter.insertHastane(hastanead, sehir,adres);
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
