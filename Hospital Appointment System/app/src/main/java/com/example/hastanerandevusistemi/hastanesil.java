package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class hastanesil extends AppCompatActivity {
    private Spinner hastanelist;
    private Button hastanesil;
    DataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hastanesil);
        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        hastanelist = (Spinner)findViewById(R.id.spnhastanesil);
        loadSpinnerHastane();

        hastanesil = (Button) findViewById(R.id.btnhastanesil);


        hastanesil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hastaneniz = hastanelist.getSelectedItem().toString();

                loginDataBaseAdapter.hastanesil(hastaneniz);
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
