package com.codemargonda.resepku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.codemargonda.resepmama.R;

/**
 * Created by Jefri Yushendri on 19/9/2016.
 */
public class LoginActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText etUsername, etPassword;
    Button bLogin;
    CheckBox cbKeepLogin;
    String USERNAME = "code";
    String PASSWORD = "123";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("DATAUSER", 0);
        editor = pref.edit();

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        cbKeepLogin = (CheckBox) findViewById(R.id.cbKeepLogin);
        bLogin = (Button) findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUsername.getText().toString().equalsIgnoreCase(USERNAME) && etPassword.getText().toString().equals(PASSWORD)) {
                    if (cbKeepLogin.isChecked()) {
                        editor.putBoolean("KEEPLOGIN", true);
                    }
                    editor.putString("USERNAME", USERNAME);
                    editor.commit();
                    Intent i = new Intent(LoginActivity.this, DaftarResepActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });


    }
}
