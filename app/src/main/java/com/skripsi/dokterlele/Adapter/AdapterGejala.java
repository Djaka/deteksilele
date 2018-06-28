package com.skripsi.dokterlele.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.dokterlele.R;

import java.util.List;

public class AdapterGejala extends RecyclerView.Adapter<AdapterGejala.GejalaViewHolder>{

    List<String> gejala;

    public AdapterGejala(List<String> gejala) {
        this.gejala = gejala;
    }

    @Override
    public GejalaViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gejala,parent,false);
        return new GejalaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GejalaViewHolder holder, int position) {
        String data = gejala.get(position);
        holder.textViewNo.setText(String.valueOf(position+1));
        holder.textViewGejala.setText(data);
    }

    @Override
    public int getItemCount() {
        return gejala.size();
    }

    public class GejalaViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNo, textViewGejala;

        public GejalaViewHolder(View itemView) {
            super(itemView);

            textViewNo = itemView.findViewById(R.id.txt_no);
            textViewGejala = itemView.findViewById(R.id.txt_gejala);
        }
    }
}
