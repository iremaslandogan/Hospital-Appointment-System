package com.example.hastanerandevusistemi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class hastane extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hastane);
    }

    public void hastanesil(View view) {
        Intent intocan = new Intent(hastane.this, hastanesil.class);
        startActivity(intocan);
    }

    public void hastaneGuncelle(View view) {
        Intent intocan = new Intent(hastane.this, hastaneguncelle.class);
        startActivity(intocan);
    }

    public void hastaneekle(View view) {
        Intent intocan = new Intent(hastane.this, hastaneekle.class);
        startActivity(intocan);
    }
}
