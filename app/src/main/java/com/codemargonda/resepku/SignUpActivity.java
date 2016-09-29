package com.codemargonda.resepku;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codemargonda.resepku.model.User;
import com.codemargonda.resepku.utils.DatabaseHandler;
import com.codemargonda.resepmama.R;

/**
 * Created by Jefri Yushendri on 19/9/2016.
 */
public class SignUpActivity extends AppCompatActivity {

    EditText etNama, etEmail, etPassword;
    Button bSignUp;
    DatabaseHandler db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHandler(this);
        etNama = (EditText) findViewById(R.id.etNama);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bSignUp = (Button) findViewById(R.id.bSignUp);

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNama.getText().toString().trim().equals("")||
                        etEmail.getText().toString().trim().equals("")||
                        etPassword.getText().toString().trim().equals("")
                ) {
                    Toast.makeText(getApplicationContext(), "Pastikan semua form terisi", Toast.LENGTH_LONG).show();

                }
                else{
                    User user = new User();
                    user.setNama(etNama.getText().toString());
                    user.setEmail(etEmail.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    db.addUser(user);
                    Toast.makeText(getApplicationContext(), "Berhasil Terdaftar", Toast.LENGTH_LONG).show();
                    finish();

                }
            }
        });


    }
}
