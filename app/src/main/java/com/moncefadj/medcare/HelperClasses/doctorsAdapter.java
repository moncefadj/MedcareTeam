package com.moncefadj.medcare.HelperClasses;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.moncefadj.medcare.DataClasses.DoctorData;
import com.moncefadj.medcare.DataClasses.DoctorDataForHomePatient;
import com.moncefadj.medcare.Doctor.DoctorProfile;
import com.moncefadj.medcare.PatientSearch.Search;
import com.moncefadj.medcare.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;


public class doctorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context context;
    ArrayList<DoctorDataForHomePatient> DoctorsList = new ArrayList<>();
    ArrayList<DoctorDataForHomePatient> DoctorListfull = new ArrayList<>(DoctorsList);


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

//pour recherche
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DoctorProfile.class);
            intent.putExtra("Name", doctors.getName());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return DoctorsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            int i = 0;int k=0;
            ArrayList<DoctorDataForHomePatient> listp = new ArrayList<>();
            for ( DoctorDataForHomePatient doctor:DoctorsList) {
                    if(doctor.getName().toLowerCase().contains(charSequence))
                {listp.add(doctor);}
                else
                Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show();
                listp.addAll(DoctorsList);
            }

              //  if (doctor.getName().contains(charSequence))
            FilterResults filterResults = new FilterResults();
            filterResults.values = listp;
            return filterResults;
        }





        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            DoctorsList.clear();
            DoctorsList.addAll((Collection<? extends DoctorDataForHomePatient>) filterResults.values);

        }
    };
}





              /*for(i=0;i<text.length(); i++){
                  if(doctor.getEmail().charAt(i)==text.charAt(i)){
                      Toast.makeText(context,"Aywa",Toast.LENGTH_SHORT).show();
                  }
                  else{
                      Toast.makeText(context,"GHIR KHTIK",Toast.LENGTH_SHORT).show();
                  }*/






    //kant DoctorsList.size();
