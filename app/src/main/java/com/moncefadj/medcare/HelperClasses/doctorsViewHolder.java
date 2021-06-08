package com.moncefadj.medcare.HelperClasses;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moncefadj.medcare.R;

public class doctorsViewHolder extends RecyclerView.ViewHolder {

        public TextView name, email;
        public ImageView img, delete;


    public doctorsViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.nom_medecin);
        email = itemView.findViewById(R.id.description_medecin);
        img = itemView.findViewById(R.id.image_medecin);
    }
}