package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class hastaneguncelle extends AppCompatActivity {
    EditText hastaneAd,Sehir,Adres;
    private Spinner hastanelist;
    private Button hastaneguncella;
    DataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hastaneguncelle);
        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        hastanelist = (Spinner)findViewById(R.id.spnhastaneguncelle);
        loadSpinnerHastane();
        hastaneAd = (EditText) findViewById(R.id.guncellehastanead);
        Sehir = (EditText) findViewById(R.id.guncellehastanesehir);
        Adres = (EditText) findViewById(R.id.guncellehastaneadres);

        hastaneguncella = (Button) findViewById(R.id.btnhastaneguncelle);


        hastaneguncella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hastanead = hastaneAd.getText().toString();
                String sehir = Sehir.getText().toString();
                String adres = Adres.getText().toString();
                String secilenhastanead = hastanelist.getSelectedItem().toString();

                loginDataBaseAdapter.hastaneguncelle(hastanead, sehir,adres,secilenhastanead);
                loadSpinnerHastane();
            }
        });
    }
    private void loadSpinnerHastane() {
        DataBaseAdapter db = new DataBaseAdapter(getApplicationContext());
        List<String> lables = db.HastaneleriGetir();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hastanelist.setAdapter(dataAdapter);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
