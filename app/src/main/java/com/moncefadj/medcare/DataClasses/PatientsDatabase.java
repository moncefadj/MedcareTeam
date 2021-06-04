package com.moncefadj.medcare.DataClasses;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PatientsDatabase {

    private DatabaseReference databaseReference;

    public PatientsDatabase(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference().child("Users").child("Patients");

    }

    public Task<Void> add (PatientData patientData){

        return databaseReference.push().setValue(patientData);

    }

    public Query get (){

        return databaseReference.orderByKey();

    }

}
