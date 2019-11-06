package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class bolumsil extends AppCompatActivity {
    private Spinner bolumlist;
    private Button bolumsil;
    DataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bolumsil);

        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        bolumlist = (Spinner)findViewById(R.id.spnbolumsil);
        loadSpinnerBolum();

        bolumsil = (Button) findViewById(R.id.btnbolumsil);


        bolumsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bolumunuz = bolumlist.getSelectedItem().toString();

                loginDataBaseAdapter.bolumsil(bolumunuz);
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
