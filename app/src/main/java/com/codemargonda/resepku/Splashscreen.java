package com.codemargonda.resepku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.codemargonda.resepmama.R;

/**
 * Created by Jefri Yushendri on 5/9/2016.
 */
public class Splashscreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);
        pref = getApplicationContext().getSharedPreferences("DATAUSER",0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                if(pref.getBoolean("KEEPLOGIN", false)){
                    i = new Intent(Splashscreen.this,DaftarResepActivity.class);
                }
                else{
                    i = new Intent(Splashscreen.this, LoginActivity.class);
                }

                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
