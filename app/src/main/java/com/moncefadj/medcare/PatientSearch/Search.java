package com.moncefadj.medcare.PatientSearch;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moncefadj.medcare.DataClasses.DoctorDataForHomePatient;
import com.moncefadj.medcare.DataClasses.DoctorsDatabase;
import com.moncefadj.medcare.DataClasses.SpecialtiesData;
import com.moncefadj.medcare.HelperClasses.doctorsAdapter;
import com.moncefadj.medcare.Medicaments.liste_medicaments;
import com.moncefadj.medcare.Patient.PatientHome;
import com.moncefadj.medcare.PatientHome.SpecialitiesAdapter;
import com.moncefadj.medcare.ProfilePatient.PatientProfile;
import com.moncefadj.medcare.R;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    Toast toast;
    ArrayList<SpecialtiesData> specialtiesData;
    SpecialitiesAdapter specialitiesAdapter;
 ArrayList<DoctorDataForHomePatient> doctorlist;
    //vertical view
    RecyclerView doctorsRecycler;
    doctorsAdapter docAdapter;
    DoctorsDatabase docdata;
EditText editText;
ListView listView;
SearchView editsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        doctorsRecycler = findViewById(R.id.doctors_recycler);
        doctorsRecycler.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        doctorsRecycler.setLayoutManager(manager);
        docAdapter = new doctorsAdapter(this);
        doctorsRecycler.setAdapter(docAdapter);
        docdata = new DoctorsDatabase();

        loadDocData();

        editsearch=findViewById(R.id.search_d);
       editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {
               docAdapter.getFilter().filter(s);
               return false;
           }
       });

        //under bar
        bottomNavigation = (MeowBottomNavigation) findViewById(R.id.bottom_navigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_med));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_search_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_profil));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override

            public void onShowItem(MeowBottomNavigation.Model item) {
                Intent intent = null;
                switch (item.getId()) {
                    case 1:
                        intent = new Intent(getApplicationContext(), PatientHome.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getApplicationContext(), liste_medicaments.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getApplicationContext(), PatientProfile.class);
                        startActivity(intent);
                        break;


                }

            }

        });
        boolean enableAnimation;
        //set home fragment initialy selected
        bottomNavigation.show(3, enableAnimation = true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //display toast
                Toast.makeText(getApplicationContext(), "you clicked" + item.getId(), Toast.LENGTH_SHORT).show();
            }

            ;
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //display toast
                Toast.makeText(getApplicationContext(), "YOU reslected" + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

   private void loadDocData() {
            docdata.get().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        ArrayList<DoctorDataForHomePatient> othDoctors = new ArrayList<>();

                        for (DataSnapshot data : snapshot.getChildren()) {

                            DoctorDataForHomePatient doctors = data.getValue(DoctorDataForHomePatient.class);
                            othDoctors.add(doctors);

                        }
                     docAdapter.setItems(othDoctors);
                      docAdapter.notifyDataSetChanged();
                    }
                }




                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
}

