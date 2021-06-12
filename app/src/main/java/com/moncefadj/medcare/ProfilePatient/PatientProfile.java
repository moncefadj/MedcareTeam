package com.moncefadj.medcare.ProfilePatient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moncefadj.medcare.DataClasses.PatientData;
import com.moncefadj.medcare.Medicaments.liste_medicaments;
import com.moncefadj.medcare.Patient.PatientHome;
import com.moncefadj.medcare.PatientSearch.Search;
import com.moncefadj.medcare.R;

public class PatientProfile extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    Toast toast;
    private Button play;

    private FirebaseUser user;
    private DatabaseReference reference,referencee;
    private String userID;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_patient_1);


        play= (Button) findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent otherAct =new Intent(getApplicationContext(), EditPatientProfile.class);
                startActivity(otherAct);

            }
        });


        back=(ImageView) findViewById(R.id.iback) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       /* Intent intent= getIntent();
        String message =intent.getStringExtra(EditPatientProfile.EXTRA_MESSAGE);
        EditText editText=findViewById(R.id.n);
        editText.setText(message);*/


        //underbar
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
                    case 2: intent = new Intent(getApplicationContext(), liste_medicaments.class);
                        startActivity(intent);
                        break;
                    case 3: intent = new Intent(getApplicationContext(), Search.class);
                        startActivity(intent);
                        break;


                    //  case 4: fragment=new ProfilFragment();
                    //  break;*/

                }
            }

        });
        boolean enableAnimation;
        //set home fragment initialy selected
        bottomNavigation.show(4, enableAnimation = true);
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

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child("Patients");
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child("Patients");
        userID =user.getUid();
        referencee = FirebaseDatabase.getInstance().getReference().child("Users").child("Patients").child(userID).child("BirthDay");



        final TextView fullnametextview =(TextView) findViewById(R.id.b);
        final TextView emailtextview =(TextView) findViewById(R.id.pemail);
        final TextView numerotextview =(TextView) findViewById(R.id.n);
        final TextView datetextview =(TextView) findViewById(R.id.e);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PatientData userprofile = snapshot.getValue(PatientData.class);
                if (userprofile !=null) {
                    String fullname = userprofile.getName();
                    String email= userprofile.getEmail();
                    String numero=String.valueOf(userprofile.getNum());


                    fullnametextview.setText(fullname);
                    emailtextview.setText(email);
                    numerotextview.setText(numero);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        referencee.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PatientData userprofile = snapshot.getValue(PatientData.class);
                if (userprofile !=null) {
                    String date=userprofile.getDay();
                    String month=userprofile.getMonth();
                    String year=userprofile.getYear();

                    datetextview.setText(date+" / "+month+" / "+year);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}