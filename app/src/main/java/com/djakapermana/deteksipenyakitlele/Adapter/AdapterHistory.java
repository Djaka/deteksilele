package com.djakapermana.deteksipenyakitlele.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djakapermana.deteksipenyakitlele.Model.History;
import com.djakapermana.deteksipenyakitlele.R;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.HistoryViewHolder> {

    List<History> histories;

    public AdapterHistory(List<History> histories) {
        this.histories = histories;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = histories.get(position);
        String waktu = history.getWaktu();
        String penyakit = history.getPenyakit();

        holder.textViewWaktu.setText(waktu);
        holder.textViewPenyakit.setText(penyakit);
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        TextView textViewWaktu, textViewPenyakit;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            textViewWaktu = itemView.findViewById(R.id.txt_waktu);
            textViewPenyakit = itemView.findViewById(R.id.txt_penyakit);

        }
    }

}
