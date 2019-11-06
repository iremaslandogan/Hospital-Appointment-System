package com.example.hastanerandevusistemi;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class kayitol extends AppCompatActivity {

    EditText ad, soyad, tc, email, sifre, tel;
    Button hastakaydet;
    DataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kayitol);

        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        ad = (EditText) findViewById(R.id.kayitAd);
        soyad = (EditText) findViewById(R.id.kayitSoyad);
        tc = (EditText) findViewById(R.id.kayitTc);
        email = (EditText) findViewById(R.id.kayitEmail);
        sifre = (EditText) findViewById(R.id.kayitSifre);
        tel = (EditText) findViewById(R.id.kayitTel);

        hastakaydet = (Button) findViewById(R.id.btnkKayit);


        hastakaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adınız = ad.getText().toString();
                String soyadınız = soyad.getText().toString();
                String tcniz = tc.getText().toString();
                String emailiniz = email.getText().toString();
                String sifreniz = sifre.getText().toString();
                String teliniz = tel.getText().toString();

                loginDataBaseAdapter.insertKisi(adınız, soyadınız,tcniz,emailiniz,sifreniz,teliniz);
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



