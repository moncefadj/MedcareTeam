package com.moncefadj.medcare.Medicaments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.moncefadj.medcare.Medicaments.medData;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moncefadj.medcare.Patient.PatientHome;
import com.moncefadj.medcare.PatientSearch.Search;
import com.moncefadj.medcare.ProfilePatient.PatientProfile;
import com.moncefadj.medcare.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class liste_medicaments extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    Toast toast;
    public static final String TIME3 = "TIME3";
    private static final int ADD_MED_ACTIVITY = 0;

    public static final String NAME = "NAME";
    public static final String DESCR = "DESCRIPTION";
    public static final String TIME = "TIME";
    public static final String TIME2 = "TIME2";
    private FloatingActionButton ajouter_med;
    ArrayList<medDataDb> list;
    private TextView nom, description, temps;
    private RecyclerView malist;
    MainViewModel viewModel;
    private medAdapter adapter;
    DatabaseReference dbreference ;
    FirebaseUser uPatient;
    String uidPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_medicaments);
        uPatient = FirebaseAuth.getInstance().getCurrentUser();
        uidPatient = uPatient.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dbreference = database.getReference().child("Users").child("Patients").child(uidPatient).child("Medicaments");


        list = new ArrayList<medDataDb>();
        retreiveData();



        adapter = new medAdapter(this,list );
        malist = (RecyclerView) findViewById(R.id.malist);
        malist.setHasFixedSize(true);
        malist.setLayoutManager(new LinearLayoutManager(this));
        malist.setAdapter(adapter);
        ajouter_med = (FloatingActionButton) findViewById(R.id.add_med);
        ajouter_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openajouterMed();
            }
        });
        // data base

        //-------------------------------------------------------------------------------------------------------------------*/
        ajouter_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openajouterMed();
            }
        });


    }

    // get data from database ------------------------------------------
    public boolean medexist(medDataDb medicament){
        boolean exist = false;
        int i = 0;
        while ( ( i< list.size() )&& (!exist)){
            if(list.get(i).getNomMed() == medicament.getNomMed()){
                exist = true;
            }
            else {
                i++;
            }
        }
       return exist;

    }
    public void retreiveData() {
        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        medDataDb med = dataSnapshot.getValue(medDataDb.class);
                        if (!medexist(med)) {
                            list.add(med);
                            Log.i("test ", med.getNomMed());
                        }

                    }

                    adapter.notifyDataSetChanged();
                    Toast.makeText(liste_medicaments.this, "data retreived succefully ", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(liste_medicaments.this, error.getMessage(), Toast.LENGTH_LONG).show();


            }
        });


    }

    private void openajouterMed() {
        Intent intent = new Intent(this, ajouterMed.class);
        startActivityForResult(intent, ADD_MED_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_MED_ACTIVITY) {

            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra(NAME);
                String desc = data.getStringExtra(DESCR);
                String time = data.getStringExtra(TIME);
                String time2 = data.getStringExtra(TIME2);
                String time3 = data.getStringExtra(TIME3);


                viewModel.addMed((medDataDb) new medData(name, desc, time, time2, time3));
                adapter.notifyDataSetChanged();
            }
        }
    }
}