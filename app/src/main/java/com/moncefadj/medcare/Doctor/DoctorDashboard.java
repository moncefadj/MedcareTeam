package com.moncefadj.medcare.Doctor;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moncefadj.medcare.DataClasses.PatientData;
import com.moncefadj.medcare.DataClasses.PatientsDatabase;
import com.moncefadj.medcare.HelperClasses.patientsAdapter;
import com.moncefadj.medcare.Medicaments.medDataDb;
import com.moncefadj.medcare.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDashboard extends AppCompatActivity {

    ImageButton docProfileBtn;
    RecyclerView patientsRecycler;
    patientsAdapter adapter;
    String uidDoctor;
    PatientsDatabase pdata;
    ArrayList<PatientData> patientslist ;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        //Acceder au profile du médecin

        docProfileBtn = findViewById(R.id.CompteMedecin);
        docProfileBtn.setOnClickListener(v -> {
            Intent intentLoadNewActivity = new Intent(DoctorDashboard.this, DoctorProfile.class);
            startActivity(intentLoadNewActivity);
        });
        patientslist = new ArrayList<PatientData>();

        patientsRecycler = findViewById(R.id.patients_recycler);
        patientsRecycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        patientsRecycler.setLayoutManager(manager);
        adapter = new patientsAdapter(this, patientslist);
        patientsRecycler.setAdapter(adapter);

        uidDoctor = FirebaseAuth.getInstance().getCurrentUser().getUid();
        pdata = new PatientsDatabase(uidDoctor);


        loadData();

    }
    public boolean patientexist(PatientData patient ){
        boolean exist = false;
        int i = 0;
        while ( ( i< patientslist.size() )&& (!exist)){
            if(patientslist.get(i).getName() == patient.getName()){
                exist = true;
            }
            else {
                i++;
            }
        }
        return exist;

    }

    private void loadData() {
        pdata.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot data : snapshot.getChildren()){

                    PatientData patients = data.getValue(PatientData.class);
                    if (!patientexist(patients )) {
                        patientslist.add(patients);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}