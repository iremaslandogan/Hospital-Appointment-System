package com.example.hastanerandevusistemi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class bolum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bolumduzen);
    }

    public void bolumguncelle(View view) {
        Intent intocan = new Intent( bolum.this, bolumguncelle.class);
        startActivity(intocan);
    }

    public void bolumekle(View view) {
        Intent intocan = new Intent(bolum.this, bolumekle.class);
        startActivity(intocan);
    }

    public void bolumsil(View view) {
        Intent intocan = new Intent(bolum.this, bolumsil.class);
        startActivity(intocan);
    }
}
