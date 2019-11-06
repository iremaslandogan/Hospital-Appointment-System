package com.example.hastanerandevusistemi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class doktorsil extends AppCompatActivity {
    private Spinner doktorlist;
    private Button doktorsil;
    DataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doktorsil);
        loginDataBaseAdapter = new DataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        doktorlist = (Spinner)findViewById(R.id.spndoktorsil);
        loadSpinnerDoktor();

        doktorsil = (Button) findViewById(R.id.btndoktorsil);


        doktorsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doktorumuz = doktorlist.getSelectedItem().toString();

                loginDataBaseAdapter.doktorsil(doktorumuz);
                loadSpinnerDoktor();
            }
        });
    }
    private void loadSpinnerDoktor() {
        DataBaseAdapter db = new DataBaseAdapter(getApplicationContext());
        List<String> lables = db.DoktorGetir();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doktorlist.setAdapter(dataAdapter);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}
