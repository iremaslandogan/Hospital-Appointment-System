package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class randevualma extends AppCompatActivity {

    private Spinner hastanelist,bolumlist,doktorlist,tarihlist,saatlist;
    private Button randevual;
    DataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randevualma);
        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        hastanelist = (Spinner)findViewById(R.id.spnhastane);
        bolumlist = (Spinner)findViewById(R.id.spnbolum);
        doktorlist = (Spinner)findViewById(R.id.spndoktor);
        tarihlist = (Spinner)findViewById(R.id.spntarih);
        saatlist = (Spinner)findViewById(R.id.spnsaat);
        loadSpinnerHastane();
        loadSpinnerBolum();
        loadSpinnerDoktor();
        loadSpinnerTarih();
        loadSpinnerSaat();

        randevual = (Button) findViewById(R.id.btnrandevual);


        randevual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hastane = hastanelist.getSelectedItem().toString();
                String bolum = bolumlist.getSelectedItem().toString();
                String doktor = doktorlist.getSelectedItem().toString();
                String tarih= tarihlist.getSelectedItem().toString();
                String saat = saatlist.getSelectedItem().toString();




                loginDataBaseAdapter.insertRandevu(hastane,bolum,doktor,tarih,saat);
                loadSpinnerHastane();
                loadSpinnerBolum();
                loadSpinnerDoktor();
                loadSpinnerTarih();
                loadSpinnerSaat();
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
    private void loadSpinnerBolum() {
        DataBaseAdapter db = new DataBaseAdapter(getApplicationContext());
        List<String> lables = db.BolumleriGetir();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolumlist.setAdapter(dataAdapter); }
    private void loadSpinnerDoktor() {
        DataBaseAdapter db = new DataBaseAdapter(getApplicationContext());
        List<String> lables = db.DoktorGetir();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doktorlist.setAdapter(dataAdapter);
    }
    private void loadSpinnerTarih() {
        DataBaseAdapter db = new DataBaseAdapter(getApplicationContext());
        List<String> lables = db.TarihGetir();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tarihlist.setAdapter(dataAdapter);
    }
    private void loadSpinnerSaat() {
        DataBaseAdapter db = new DataBaseAdapter(getApplicationContext());
        List<String> lables = db.SaatGetir();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        saatlist.setAdapter(dataAdapter);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
