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
import android.widget.TextView;
import android.widget.Toast;

import com.codemargonda.resepku.model.User;
import com.codemargonda.resepku.utils.DatabaseHandler;
import com.codemargonda.resepmama.R;

/**
 * Created by Jefri Yushendri on 19/9/2016.
 */
public class LoginActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    EditText etEmail, etPassword;
    Button bLogin;
    TextView tSignUp;
    CheckBox cbKeepLogin;
    String USERNAME = "code";
    String PASSWORD = "123";
    DatabaseHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db= new DatabaseHandler(this);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("DATAUSER", 0);
        editor = pref.edit();

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tSignUp = (TextView) findViewById(R.id.tSignUp);
        cbKeepLogin = (CheckBox) findViewById(R.id.cbKeepLogin);
        bLogin = (Button) findViewById(R.id.bLogin);


        tSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);

            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = db.checkUser(etEmail.getText().toString(),etPassword.getText().toString());
                if (user !=null){
                    if (cbKeepLogin.isChecked()) {
                        editor.putBoolean("KEEPLOGIN", true);
                    }
                    editor.putString("NAMAUSER", user.getNama());
                    editor.commit();
                    Intent i = new Intent(LoginActivity.this, DaftarResepActivity.class);
                    startActivity(i);
                    finish();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Eamil/Password tidak sesuai", Toast.LENGTH_LONG).show();
                    etEmail.setError("Email tidak sesuai");
                    etPassword.setError("Password tidak sesuai");
                }
            }
        });


    }
}
