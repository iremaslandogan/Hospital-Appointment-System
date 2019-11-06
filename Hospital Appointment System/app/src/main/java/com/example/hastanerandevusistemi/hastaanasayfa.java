package com.example.hastanerandevusistemi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

public class hastaanasayfa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hastaanasayfa);
    }

    public void kullaniciBilgileri(View view) {
    }

    public void randevuGeçmişi(View view) {
    }

    public void randevuAl(View view) {
        Intent intocan = new Intent(hastaanasayfa.this, randevualma.class);
        startActivity(intocan);
    }

}
