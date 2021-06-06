package com.moncefadj.medcare.HelperClasses;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moncefadj.medcare.R;

public class patientsViewHolder extends RecyclerView.ViewHolder {

        public TextView name, email;
        public ImageView img, delete;
    public patientsViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.NomPatient);
        email = view.findViewById(R.id.HeureRDV);
        delete = view.findViewById(R.id.deleteImgBtn);
        img = view.findViewById(R.id.ImagePatient);
    }
}