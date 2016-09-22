package com.codemargonda.resepku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.codemargonda.resepku.adapter.ResepListAdapter;
import com.codemargonda.resepku.model.Resep;
import com.codemargonda.resepku.utils.DatabaseHandler;
import com.codemargonda.resepmama.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefri Yushendri on 19/9/2016.
 */
public class DaftarResepActivity extends AppCompatActivity {
RecyclerView rv;
    ResepListAdapter adapter;
    List<Resep> resepList = new ArrayList<Resep>();
    DatabaseHandler db;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_resep);
        //setTitle("Selamat Datang " +pref.getString("USERNAME",""));
        pref = getApplicationContext().getSharedPreferences("DATAUSER",0);
        edit = pref.edit();

        db = new DatabaseHandler(this);
        rv = (RecyclerView) findViewById(R.id.rvResep);
        adapter = new ResepListAdapter(this, resepList);
      //  RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(DaftarResepActivity.this, 2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        prepareData();

    }

    private void prepareData(){
        resepList.clear();
        resepList.addAll(db.getAllResep());
      adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.tambah_resep) {
            Intent i = new Intent(DaftarResepActivity.this, TambahResepActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.logout) {
            edit.clear();
            edit.commit();
            Intent i = new Intent(DaftarResepActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        prepareData();

    }
}
