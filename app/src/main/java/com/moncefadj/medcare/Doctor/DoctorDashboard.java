package com.moncefadj.medcare.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moncefadj.medcare.DataClasses.PatientData;
import com.moncefadj.medcare.HelperClasses.patientsAdapter;
import com.moncefadj.medcare.R;

import java.util.ArrayList;
import java.util.List;

public class DoctorDashboard extends AppCompatActivity {

    ImageButton docProfileBtn;
    RecyclerView patientsRecycler;
    com.moncefadj.medcare.HelperClasses.patientsAdapter patientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        docProfileBtn = findViewById(R.id.ib1);
        docProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(DoctorDashboard.this, DoctorProfile.class);
                startActivity(intentLoadNewActivity);
            }
        });

        List<PatientData> patientDataList = new ArrayList<>();
        patientDataList.add(new PatientData("Patient 1","de 08h00","à 08h15",R.drawable.img_patient));
        patientDataList.add(new PatientData("Patient 2","de 08h15","à 08h30",R.drawable.img_patient));
        patientDataList.add(new PatientData("Patient 3","de 08h30","à 08h45",R.drawable.img_patient));
        patientDataList.add(new PatientData("Patient 4","de 08h45","à 09h00",R.drawable.img_patient));
        patientDataList.add(new PatientData("Patient 5","de 09h00","à 09h15",R.drawable.img_patient));
        patientDataList.add(new PatientData("Patient 6","de 09h15","à 09h30",R.drawable.img_patient));
        patientDataList.add(new PatientData("Patient 7","de 09h30","à 09h45",R.drawable.img_patient));
        patientDataList.add(new PatientData("Patient 8","de 09h45","à 10h00",R.drawable.img_patient));
        patientDataList.add(new PatientData("Patient 9","de 10h00","à 10h15",R.drawable.img_patient));
        patientDataList.add(new PatientData("Patient 10","de 10h15","à 10h30",R.drawable.img_patient));

        setPatientsRecycler(patientDataList);
    }

    private  void setPatientsRecycler(List<PatientData> patientDataList){

        patientsRecycler = findViewById(R.id.patients_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        patientsRecycler.setLayoutManager(layoutManager);
        patientsAdapter = new patientsAdapter(this, patientDataList);
        patientsRecycler.setAdapter(patientsAdapter);

    }

}