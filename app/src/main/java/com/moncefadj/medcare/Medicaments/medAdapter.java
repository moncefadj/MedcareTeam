package com.moncefadj.medcare.Medicaments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.moncefadj.medcare.R;
import java.util.ArrayList;

public class medAdapter extends RecyclerView.Adapter<medAdapter.viewHolder> {
    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView nameitm,descriptionitm, timeitm , timeitm2, timeitm3;
        ImageButton deletemed ;
        ImageView medimg;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nameitm = (TextView) itemView.findViewById(R.id.nameitem);
            descriptionitm = (TextView) itemView.findViewById(R.id.descriptionitem);
            timeitm = (TextView) itemView.findViewById(R.id.timeitem);
            timeitm2 = (TextView) itemView.findViewById(R.id.timeitem2);
            timeitm3 = (TextView) itemView.findViewById(R.id.timeitm3);
            deletemed = (ImageButton) itemView.findViewById(R.id.deletemed);
            medimg = (ImageView) itemView.findViewById(R.id.medimg);

        }
    }

    private final Context context;
    private final ArrayList<medDataDb> list ;
    public medAdapter(Context c, ArrayList<medDataDb> list) {
        this.context = c;
        this.list = list ;
    }

    @NonNull
    @Override
    public medAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent , false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(medAdapter.viewHolder holder, int position) {
        medData medicament = list.get(position);
        holder.nameitm.setText(medicament.getNomMed());
        holder.descriptionitm.setText(medicament.getDescriptionMed());
        holder.timeitm.setText(medicament.getTempsMed());
        holder.timeitm2.setText(medicament.getTempsMed2());
        holder.timeitm3.setText(medicament.getTempsMed3());
        holder.deletemed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.medimg.getContext());
                builder.setTitle("Voulez vous vraiment supprimer ce medicament?");
                builder.setMessage("Si vous cliquez sur Oui, le medicament sera plus disponible dans votre liste et donc pas de rappel pour le prendre ");

                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        FirebaseDatabase.getInstance().getReference().child("Users").child("Patients").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Medicaments").child(medicament.getNomMed()).removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            list.remove(list.get(position));
                                            notifyDataSetChanged();
                                            Toast.makeText(context , "data deleted succesfully ", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            Toast.makeText(context , "erreur "+task.getException().getMessage() , Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

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
        return list.size();
    }
}
