package com.codemargonda.resepku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.codemargonda.resepmama.R;

public class MainActivity extends AppCompatActivity {

    Button tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tambah = (Button) findViewById(R.id.button);


        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TambahResepActivity.class );
                startActivity(i);
            }
        });
    }
}
