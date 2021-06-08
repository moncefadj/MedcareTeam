package com.moncefadj.medcare.PatientSearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moncefadj.medcare.DataClasses.DoctorData;
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
    String name;
    EditText searchbar;
    DoctorsDatabase docdata;
RecyclerView recyclerView;
    doctorsAdapter docAdapter;
    ArrayList<DoctorDataForHomePatient> list;
    ArrayList<SpecialtiesData> specialtiesData;
    SpecialitiesAdapter specialitiesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = (RecyclerView) findViewById(R.id.doctors_recycler);

        //show doctors
        docAdapter = new doctorsAdapter(this);
        recyclerView.setAdapter(docAdapter);
        docdata = new DoctorsDatabase();
list=new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadDocData();
     /*   Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);
        Spinner spinner3 = findViewById(R.id.spinner3);
        String[] Categouries = {"categouries", "denstiste", "cardiologue"};
        String[] wilayas = {"wilayas", "adrar", "bechar"};
        String[] disponible = {"disponibilité", "Plus diponible", "moins disponible"};
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Categouries);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, wilayas);
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, disponible);
        spinner1.setAdapter(arrayAdapter1);
        spinner2.setAdapter(arrayAdapter2);
        spinner3.setAdapter(arrayAdapter3);
        String[] doctors = {"dorcto1", "doctor2", "doctor3", "doctor4", "doctor5"};
*/
        //ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,doctors);
// mListeView.setAdapter(mAdapter);
// msearch.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
searchbar=findViewById(R.id.search_bar);
       searchbar.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
filter(editable.toString());
    }
           private void filter(String text) {
        ArrayList<DoctorDataForHomePatient> filterlist=new ArrayList<DoctorDataForHomePatient>();
        for(DoctorDataForHomePatient a : list){
            if (a.getName().contains(text)){
                filterlist.add(a);
        }
        docAdapter.filterlist(filterlist);
           }
    }
       });
        bottomNavigation = (MeowBottomNavigation)

                findViewById(R.id.bottom_navigation);
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

                ArrayList<DoctorDataForHomePatient> othDoctors = new ArrayList<>();

                for (DataSnapshot data : snapshot.getChildren()){

                    DoctorDataForHomePatient doctors = data.getValue(DoctorDataForHomePatient.class);
                    othDoctors.add(doctors);

                }

                docAdapter.setItems(othDoctors);
                docAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
