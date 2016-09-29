package com.codemargonda.resepku;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codemargonda.resepku.model.Resep;
import com.codemargonda.resepku.utils.DatabaseHandler;
import com.codemargonda.resepmama.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TambahResepActivity extends AppCompatActivity {

    EditText etNamaResep, etDeskripsi;
    DatabaseHandler db;
    Resep resep;

    LinearLayout bGambar, bTambah;
    ImageView imgGambar;
    private Bitmap bitmap = null;
    private Uri filePath;
    private int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_resep);
        db = new DatabaseHandler(this);
        resep = new Resep();

        etNamaResep = (EditText) findViewById(R.id.etNama);
        etDeskripsi = (EditText) findViewById(R.id.etDeskripsi);
        bTambah = (LinearLayout) findViewById(R.id.bTambah);
        bGambar = (LinearLayout) findViewById(R.id.bGambar);

        imgGambar = (ImageView) findViewById(R.id.imgGambar);

        bTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resep.setNama(etNamaResep.getText().toString());
                resep.setDeskripsi(etDeskripsi.getText().toString());
                resep.setGambar(getImageByte(bitmap));
                db.addResep(resep);
                Toast.makeText(getApplicationContext(),"Berhasil ditambahakan", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        bGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });


    }


    public void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode==RESULT_OK) {


            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgGambar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public byte[] getImageByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte imageInByte[]=null;
        if(bitmap!=null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            imageInByte=stream.toByteArray();
        }
        return imageInByte;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.submit_resep) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
