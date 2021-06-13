package com.moncefadj.medcare.HelperClasses;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.moncefadj.medcare.DataClasses.PatientData;
import com.moncefadj.medcare.R;

import java.util.ArrayList;


public class patientsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    ArrayList<PatientData> PatientsList = new ArrayList<>();

    public patientsAdapter(Context context) {
        this.context = context;
    }

    public void setItems (ArrayList <PatientData> patients){
        PatientsList.addAll(patients);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_patients_adapter, parent,false);
        return new com.moncefadj.medcare.HelperClasses.patientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        com.moncefadj.medcare.HelperClasses.patientsViewHolder viewHolder = (com.moncefadj.medcare.HelperClasses.patientsViewHolder) holder;
        PatientData patients = PatientsList.get(position);
        viewHolder.name.setText(patients.getName());
        viewHolder.heure.setText(patients.getHeure());

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(viewHolder.img.getContext());
                builder.setTitle("Voulez vous vraiment supprimer ce patient?");
                builder.setMessage("Si vous cliquez sur Oui, vous aller annuler le rendez-vous pris par ce patient et il sera par la suite supprimé de votre liste de reception.");

                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String id = PatientsList.get(position).getId();
                        FirebaseDatabase.getInstance().getReference().child("Users").child("Patients").child(id).removeValue();
                    }
                });

                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return PatientsList.size();
    }

}