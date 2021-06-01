package com.moncefadj.medcare.docc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moncefadj.medcare.R;

import java.util.List;

public class patientsAdapter extends RecyclerView.Adapter<com.moncefadj.medcare.docc.patientsAdapter.patientsViewHolder> {

    Context context;
    List<patientsData> patientsDataList;

    public patientsAdapter(Context context, List<patientsData> patientsDataList) {
        this.context = context;
        this.patientsDataList = patientsDataList;
    }

    @NonNull
    @Override
    public patientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_patients_adapter, parent, false);

        return new patientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull patientsViewHolder holder, int position) {

        holder.NomPatient.setText(patientsDataList.get(position).getNomPatient());
        holder.deb.setText(patientsDataList.get(position).getdeb());
        holder.fin.setText(patientsDataList.get(position).getfin());
        holder.imgPatient.setImageResource(patientsDataList.get(position).getimgPatient());
    }

    @Override
    public int getItemCount() {
        return patientsDataList.size();
    }

    public static final class patientsViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPatient;
        TextView NomPatient, deb, fin;

        public patientsViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPatient = itemView.findViewById(R.id.imgPatient);
            NomPatient = itemView.findViewById(R.id.NomPatient);
            deb = itemView.findViewById(R.id.deb);
            fin = itemView.findViewById(R.id.fin);
        }
    }
}