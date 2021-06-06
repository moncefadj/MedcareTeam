package com.moncefadj.medcare.Doctor;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.moncefadj.medcare.DataClasses.PatientData;
import com.moncefadj.medcare.DataClasses.PatientsDatabase;
import com.moncefadj.medcare.HelperClasses.patientsAdapter;
import com.moncefadj.medcare.R;

import java.util.ArrayList;

public class DoctorDashboard extends AppCompatActivity {

    ImageButton docProfileBtn;
    RecyclerView patientsRecycler;
    patientsAdapter adapter;
    PatientsDatabase pdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        docProfileBtn = findViewById(R.id.CompteMedecin);
        docProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(DoctorDashboard.this, DoctorProfile.class);
                startActivity(intentLoadNewActivity);
            }
        });

        patientsRecycler = findViewById(R.id.patients_recycler);
        patientsRecycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        patientsRecycler.setLayoutManager(manager);
        adapter = new patientsAdapter(this);
        patientsRecycler.setAdapter(adapter);
        pdata = new PatientsDatabase();

        loadData();

    }

    private void loadData() {
        pdata.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<PatientData> othPtients = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()){

                    PatientData patients = data.getValue(PatientData.class);
                    othPtients.add(patients);

                }

                adapter.setItems(othPtients);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}