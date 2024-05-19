package com.example.aplikassetoran;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HafalanSurahAdapter extends RecyclerView.Adapter<HafalanSurahAdapter.ViewHolder> {

    private List<HafalanSurah> hafalanSurahList;

    public HafalanSurahAdapter(List<HafalanSurah> hafalanSurahList) {
        this.hafalanSurahList = hafalanSurahList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_hafalan_surah, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HafalanSurah hafalanSurah = hafalanSurahList.get(position);
        holder.tvSurah.setText(hafalanSurah.getSurah());
        holder.tvTanggal.setText(hafalanSurah.getTanggal());
        holder.tvKelancaran.setText(hafalanSurah.getKelancaran());
    }

    @Override
    public int getItemCount() {
        return hafalanSurahList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSurah, tvTanggal, tvKelancaran;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSurah = itemView.findViewById(R.id.tvSurah);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvKelancaran = itemView.findViewById(R.id.tvKelancaran);
        }
    }
}
