package com.moncefadj.medcare.Medicaments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class medDATABASE {
     DatabaseReference databaseReference;

    public medDATABASE(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference().child("Users").child("Patients").child("Medicaments");

    }
    public Query get (){

        return databaseReference.orderByKey();

    }
}

