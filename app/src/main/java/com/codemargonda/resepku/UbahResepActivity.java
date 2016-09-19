package com.codemargonda.resepku;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.codemargonda.resepku.model.Resep;
import com.codemargonda.resepku.utils.DatabaseHandler;
import com.codemargonda.resepmama.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UbahResepActivity extends AppCompatActivity {

    EditText etNamaResep, etDeskripsi;
    DatabaseHandler db;
    Resep resep;
    Button bTambah, bGambar;
    ImageView imgGambar;
    private Bitmap bitmap = null;
    private Uri filePath;
    private int PICK_IMAGE_REQUEST = 1;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_resep);

        initForm();


        bTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resep.setNama(etNamaResep.getText().toString());
                resep.setDeskripsi(etDeskripsi.getText().toString());
                resep.setGambar(getImageByte(bitmap));
                db.updateResep(resep);
                Toast.makeText(getApplicationContext(),"Berhasil diubah",Toast.LENGTH_LONG);
            }
        });

        bGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });


    }

    void initForm(){
        id=getIntent().getIntExtra("ID",0);
        db = new DatabaseHandler(this);
        resep =   db.getResep(id);

        etNamaResep = (EditText) findViewById(R.id.etNama);
        etDeskripsi = (EditText) findViewById(R.id.etDeskripsi);
        bTambah = (Button) findViewById(R.id.bTambah);
        bGambar = (Button) findViewById(R.id.bGambar);
        imgGambar = (ImageView) findViewById(R.id.imgGambar);

        etNamaResep.setText(resep.getNama());
        etDeskripsi.setText(resep.getDeskripsi());
        if(resep.getGambar()!=null) {
            imgGambar.setImageBitmap(bitmap(resep.getGambar()));
        }
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
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {


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

    public Bitmap bitmap(byte[] byteImage) {
        byte[] outImage = byteImage;
        Bitmap image;
        if (outImage != null) {
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
            image = BitmapFactory.decodeStream(imageStream);
        } else {
            image = null;
        }
        return image;
    }


}
