package com.moncefadj.medcare.HelperClasses;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moncefadj.medcare.R;

public class doctorsViewHolder extends RecyclerView.ViewHolder {

        public TextView name, email,speciality;
        public ImageView img, delete;
    public doctorsViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.nom_medecin);
        email = view.findViewById(R.id.description_medecin);
        img = view.findViewById(R.id.image_medecin);
        speciality=view.findViewById(R.id.speciality1);
    }
}