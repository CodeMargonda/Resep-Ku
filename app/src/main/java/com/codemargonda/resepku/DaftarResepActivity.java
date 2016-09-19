package com.codemargonda.resepku;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_resep);
        db = new DatabaseHandler(this);
        rv = (RecyclerView) findViewById(R.id.rvResep);
        adapter = new ResepListAdapter(this, resepList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);
        prepareData();

    }

    private void prepareData(){
        resepList.addAll(db.getAllResep());
      adapter.notifyDataSetChanged();
    }



}
