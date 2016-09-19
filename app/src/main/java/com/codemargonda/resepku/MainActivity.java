package com.codemargonda.resepku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codemargonda.resepmama.R;

public class MainActivity extends AppCompatActivity {

    Button tambah, lihat;
    TextView tUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tambah = (Button) findViewById(R.id.bTambah);
        lihat = (Button) findViewById(R.id.bLihat);
        tUser = (TextView) findViewById(R.id.tUser);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("DATAUSER",0);
        tUser.setText("Selamat Datang " +pref.getString("USERNAME",""));
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TambahResepActivity.class );
                startActivity(i);
            }
        });
        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DaftarResepActivity.class );
                startActivity(i);
            }
        });
    }
}
