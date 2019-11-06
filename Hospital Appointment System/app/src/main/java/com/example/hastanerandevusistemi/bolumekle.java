package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class bolumekle extends AppCompatActivity {

    EditText bolumAd;
    Button bolumkaydet;
    DataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bolumekle);
        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        bolumAd = (EditText) findViewById(R.id.bolumAd);

        bolumkaydet = (Button) findViewById(R.id.btnbolumekleme);


        bolumkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bolumad = bolumAd.getText().toString();
                loginDataBaseAdapter.insertBolum(bolumad);
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
