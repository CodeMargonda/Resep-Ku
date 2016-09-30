package com.codemargonda.resepku;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codemargonda.resepku.model.Resep;
import com.codemargonda.resepku.utils.DatabaseHandler;
import com.codemargonda.resepmama.R;

import java.io.ByteArrayInputStream;

public class DetailResepActivity extends AppCompatActivity {

    TextView tNamaResep, tDeskripsi;
    DatabaseHandler db;
    Resep resep;

    ImageView imgGambar;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resep);

        initForm();



    }

    void initForm(){
        id=getIntent().getIntExtra("ID",0);
        db = new DatabaseHandler(this);
        resep =   db.getResep(id);

        tNamaResep = (TextView) findViewById(R.id.tNama);
        tDeskripsi = (TextView) findViewById(R.id.tDeskripsi);

        imgGambar = (ImageView) findViewById(R.id.imgGambar);

        tNamaResep.setText(resep.getNama());
        tDeskripsi.setText(resep.getDeskripsi());
        if(resep.getGambar()!=null) {
            imgGambar.setImageBitmap(bitmap(resep.getGambar()));
        }
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.ubah_resep) {
            Intent i = new Intent(DetailResepActivity.this, UbahResepActivity.class);
            i.putExtra("ID", resep.getID());
            startActivity(i);
            return true;
        }
        if (id == R.id.hapus_resep) {
            db.deleteResep(resep);
            Intent i = new Intent(DetailResepActivity.this, DaftarResepActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        initForm();
    }
}
