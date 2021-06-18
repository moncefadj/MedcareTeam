package com.moncefadj.medcare.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moncefadj.medcare.DataClasses.DoctorDataForHomePatient;
import com.moncefadj.medcare.Doctor.DoctorProfile;
import com.moncefadj.medcare.Patient.DoctorProfileFromPatientHome;
import com.moncefadj.medcare.R;

import java.util.ArrayList;
import java.util.Collection;

public class adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable  {
    ArrayList<DoctorDataForHomePatient> Doctors=new ArrayList<>();

    Context context;

    public adapter(Context context) {
        this.context = context;
    }
    public void setItems(ArrayList<DoctorDataForHomePatient> doctors) {
        Doctors.addAll(doctors);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.verow, parent, false);
        return new row(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        row viewHolder = (row) holder;
        DoctorDataForHomePatient doctors =Doctors.get(position);
        viewHolder.namedoc.setText(doctors.getName());
        viewHolder.specdoc.setText(doctors.getSpecialty());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DoctorProfileFromPatientHome.class);
            intent.putExtra("Name", doctors.getName());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return Doctors.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<DoctorDataForHomePatient> liste = new ArrayList<>();
            for (DoctorDataForHomePatient item : Doctors) {
                if (item.getName().contains(charSequence)) {
                    liste.add(item);
                }
            }
FilterResults filterResults=new FilterResults();
            filterResults.values = liste;
            return filterResults;

        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            Doctors.clear();

            Doctors.addAll((Collection<? extends DoctorDataForHomePatient>) filterResults.values);
        }

    };
}