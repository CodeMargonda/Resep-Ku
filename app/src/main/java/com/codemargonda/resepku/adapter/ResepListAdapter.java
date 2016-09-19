package com.codemargonda.resepku.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codemargonda.resepku.model.Resep;
import com.codemargonda.resepmama.R;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by Jefri Yushendri on 20/5/2016.
 */
public class ResepListAdapter extends RecyclerView.Adapter<ResepListAdapter.MyViewHolder> {

    private List<Resep> resepList;
    private LayoutInflater mInflater;
    private Context mContext;

    public ResepListAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tNama;
        public ImageView imgGambar;


        public MyViewHolder(View view) {
            super(view);
            tNama = (TextView) view.findViewById(R.id.tNama);
            imgGambar = (ImageView) view.findViewById(R.id.imgGambar);
        }
    }


    public ResepListAdapter(List<Resep> resepList) {
        this.resepList = resepList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_resep, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Resep resep = resepList.get(position);
        holder.tNama.setText(resep.getNama());

        if(bitmap(resep.getGambar())!=null){
            holder.imgGambar.setImageBitmap(bitmap(resep.getGambar()));
        }
    }


    @Override
    public int getItemCount() {
        return (resepList == null) ? 0 : resepList.size();
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