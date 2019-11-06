package com.example.hastanerandevusistemi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

    }

    public void hastane(View view) {
        Intent intocan = new Intent(admin.this, hastane.class);
        startActivity(intocan);
    }

    public void doktor(View view) {
        Intent intocan = new Intent(admin.this, doktor.class);
        startActivity(intocan);
    }

    public void bolum(View view) {
        Intent intocan = new Intent(admin.this, bolum.class);
        startActivity(intocan);
    }

    public void randevu(View view) {
        //Intent intocan = new Intent(admin.this, randevu.class);
        //startActivity(intocan);
    }
}
