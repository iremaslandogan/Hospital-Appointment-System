package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class doktorekle extends AppCompatActivity {
    private Spinner hastanelist,bolumlist;
    private TextView doktorad,doktorsoyad,doktortc,doktoremail,doktorsifre,doktortel;
    private Button doktorkaydet;
    DataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doktorekle);
        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        hastanelist = (Spinner)findViewById(R.id.spnhastane);
        bolumlist = (Spinner)findViewById(R.id.spnBolum);
        loadSpinnerHastane();
        loadSpinnerBolum();
        doktorad = (EditText) findViewById(R.id.doktorad);
        doktorsoyad = (EditText) findViewById(R.id.doktorsoyad);
        doktortc = (EditText) findViewById(R.id.doktortc);
        doktoremail = (EditText) findViewById(R.id.doktoremail);
        doktorsifre = (EditText) findViewById(R.id.doktorsifre);
        doktortel = (EditText) findViewById(R.id.doktortel);

        doktorkaydet = (Button) findViewById(R.id.btndoktorekleme);


        doktorkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adınız = doktorad.getText().toString();
                String soyadınız = doktorsoyad.getText().toString();
                String tcniz = doktortc.getText().toString();
                String emailiniz = doktoremail.getText().toString();
                String sifreniz = doktorsifre.getText().toString();
                String teliniz = doktortel.getText().toString();
                String hastaneniz = hastanelist.getSelectedItem().toString();
                String bolumunuz = bolumlist.getSelectedItem().toString();

                loginDataBaseAdapter.insertDoktor(adınız, soyadınız,tcniz,emailiniz,sifreniz,teliniz,hastaneniz,bolumunuz);
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
        bolumlist.setAdapter(dataAdapter);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
