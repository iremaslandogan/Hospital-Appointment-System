package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class doktorguncelle extends AppCompatActivity {

    EditText doktorad,doktorsoyad,doktortc,doktoremail,doktorsifre,doktortel;
    private Spinner doktorlist,hastanelist,bolumlist;
    private Button doktorguncella;
    DataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doktorguncelle);
        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        doktorlist = (Spinner)findViewById(R.id.spndoktorguncelle);
        loadSpinnerDoktor();
        hastanelist = (Spinner)findViewById(R.id.spnguncelledoktorhastane);
        bolumlist = (Spinner)findViewById(R.id.spnguncelledoktorbolum);
        loadSpinnerHastane();
        loadSpinnerBolum();
        doktorad = (EditText) findViewById(R.id.guncelledoktorad);
        doktorsoyad = (EditText) findViewById(R.id.guncelledoktorsoyad);
        doktortc = (EditText) findViewById(R.id.guncelledoktortc);
        doktoremail = (EditText) findViewById(R.id.guncelledoktoremail);
        doktorsifre = (EditText) findViewById(R.id.guncelledoktorsifre);
        doktortel = (EditText) findViewById(R.id.guncelledoktortel);

        doktorguncella = (Button) findViewById(R.id.btnguncelledoktor);



        doktorguncella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adınız = doktorad.getText().toString();
                String soyadınız = doktorsoyad.getText().toString();
                String tcniz = doktortc.getText().toString();
                String emailiniz = doktoremail.getText().toString();
                String sifreniz = doktorsifre.getText().toString();
                String teliniz = doktortel.getText().toString();
                String secilenhastanead = hastanelist.getSelectedItem().toString();
                String Secilenbolum = bolumlist.getSelectedItem().toString();
                String secilendoktorad = doktorlist.getSelectedItem().toString();
                loginDataBaseAdapter.doktorguncelle(adınız, soyadınız,tcniz,emailiniz,sifreniz,teliniz,secilendoktorad,secilenhastanead,Secilenbolum);
                loadSpinnerDoktor();
            }
        });
    }
    private void loadSpinnerDoktor() {
        DataBaseAdapter db = new DataBaseAdapter(getApplicationContext());
        List<String> lables = db.DoktorGetir();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doktorlist.setAdapter(dataAdapter);
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
        bolumlist.setAdapter(dataAdapter);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
