package com.codemargonda.resepku.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codemargonda.resepku.DetailResepActivity;
import com.codemargonda.resepku.UbahResepActivity;
import com.codemargonda.resepku.model.Resep;
import com.codemargonda.resepku.utils.DatabaseHandler;
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
        public Button bUbah, bHapus, bLihat;


        public MyViewHolder(View view) {
            super(view);
            tNama = (TextView) view.findViewById(R.id.tNama);
            imgGambar = (ImageView) view.findViewById(R.id.imgGambar);
            bUbah = (Button) view.findViewById(R.id.bUbah);
            bHapus = (Button) view.findViewById(R.id.bHapus);
            bLihat = (Button) view.findViewById(R.id.bLihat);
        }
    }


    public ResepListAdapter(Context context, List<Resep> resepList) {
        this.mContext = context;
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
        final Resep resep = resepList.get(position);
        holder.tNama.setText(resep.getNama());

        if (bitmap(resep.getGambar()) != null) {
            holder.imgGambar.setImageBitmap(bitmap(resep.getGambar()));
        }

        holder.bUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, UbahResepActivity.class);
                i.putExtra("ID", resep.getID());
                mContext.startActivity(i);
            }
        });

        holder.bLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, DetailResepActivity.class);
                i.putExtra("ID", resep.getID());
                mContext.startActivity(i);
            }
        });

        holder.bHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler db = new DatabaseHandler(mContext);
                db.deleteResep(resep);
                resepList.remove(position);
                notifyDataSetChanged();

            }
        });

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
