package com.moncefadj.medcare.medicaments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moncefadj.medcare.medicaments.MainViewModel;
import com.moncefadj.medcare.R;
import com.moncefadj.medcare.medicaments.medData;

public class medAdapter extends RecyclerView.Adapter<medAdapter.viewHolder> {
    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView nameitm,descriptionitm, timeitm , timeitm2, timeitm3;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nameitm = (TextView) itemView.findViewById(R.id.nameitem);
            descriptionitm = (TextView) itemView.findViewById(R.id.descriptionitem);
            timeitm = (TextView) itemView.findViewById(R.id.timeitem);
            timeitm2 = (TextView) itemView.findViewById(R.id.timeitem2);
            timeitm3 = (TextView) itemView.findViewById(R.id.timeitm3);
        }
    }

    private final Context context;
    private final MainViewModel viewModel;
    public medAdapter(Context c, MainViewModel viewModel) {
        this.context = c;
        this.viewModel = viewModel ;
    }

    @NonNull
    @Override
    public medAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent , false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(medAdapter.viewHolder holder, int position) {
        medData medicament = viewModel.getData().get(position);
        holder.nameitm.setText(medicament.getNomMed());
        holder.descriptionitm.setText(medicament.getDescriptionMed());
        holder.timeitm.setText(medicament.getTempsMed());
        holder.timeitm2.setText(medicament.getTempsMed2());
        holder.timeitm3.setText(medicament.getTempsMed3());

    }

    @Override
    public int getItemCount() {
        return viewModel.getData().size();
    }
}
