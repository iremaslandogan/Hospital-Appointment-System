package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class bolumguncelle extends AppCompatActivity {
    private Spinner bolumlist;
    private Button bolumguncelle;
    DataBaseAdapter loginDataBaseAdapter;
    EditText bolumAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bolumguncelle);

        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        bolumlist = (Spinner)findViewById(R.id.spnbolumguncelle);
        loadSpinnerBolum();

        bolumAd = (EditText) findViewById(R.id.guncelebolumad);
        bolumguncelle = (Button) findViewById(R.id.btnbolumguncelle);


        bolumguncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bolumunuz = bolumlist.getSelectedItem().toString();
                String bolumad = bolumAd.getText().toString();
                loginDataBaseAdapter.bolumguncelle(bolumad,bolumunuz);
                loadSpinnerBolum();
            }
        });
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
