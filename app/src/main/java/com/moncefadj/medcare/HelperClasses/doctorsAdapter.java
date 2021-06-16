package com.moncefadj.medcare.HelperClasses;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moncefadj.medcare.DataClasses.DoctorDataForHomePatient;
import com.moncefadj.medcare.Doctor.DoctorProfile;
import com.moncefadj.medcare.PatientSearch.Search;
import com.moncefadj.medcare.R;

import java.util.ArrayList;


public class doctorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    ArrayList<DoctorDataForHomePatient> DoctorsList = new ArrayList<>();

    public doctorsAdapter(Context context, ArrayList<DoctorDataForHomePatient> doctorsList) {
        this.context = context;
        DoctorsList = doctorsList;
    }

    public doctorsAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<DoctorDataForHomePatient> doctors) {
        DoctorsList.addAll(doctors);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_doctors_adapter, parent, false);
        return new doctorsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        doctorsViewHolder viewHolder = (doctorsViewHolder) holder;
        DoctorDataForHomePatient doctors = DoctorsList.get(position);
        viewHolder.name.setText(doctors.getName());
        viewHolder.email.setText(doctors.getEmail());
        viewHolder.speciality.setText(doctors.getSpecialty());

//pour recherche
    }

    @Override
    public int getItemCount() {
        return DoctorsList.size();
    }

    public ArrayList<DoctorDataForHomePatient> listage(){
        return  DoctorsList;
    }
}