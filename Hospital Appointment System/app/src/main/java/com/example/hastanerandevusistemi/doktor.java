package com.example.hastanerandevusistemi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class doktor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doktorduzen);
    }

    public void doktorEkle(View view) {
        Intent intocan = new Intent(doktor.this, doktorekle.class);
        startActivity(intocan);
    }

    public void doktorSil(View view) {
        Intent intocan = new Intent(doktor.this, doktorsil.class);
        startActivity(intocan);
    }

    public void doktorGuncelle(View view) {
        Intent intocan = new Intent(doktor.this, doktorguncelle.class);
        startActivity(intocan);
    }
}
