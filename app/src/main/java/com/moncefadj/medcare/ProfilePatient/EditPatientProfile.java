package com.moncefadj.medcare.ProfilePatient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.moncefadj.medcare.DataClasses.PatientData;
import com.moncefadj.medcare.Doctor.EditDoctorProfile;
import com.moncefadj.medcare.R;

import java.util.HashMap;

public class EditPatientProfile extends AppCompatActivity {
    // public static final String EXTRA_MESSAGE ="com.example.test.MESSAGE";

    private DatabaseReference ref, reff;
    private String userrID;
    private FirebaseUser userr;

    private EditText mnameinput, mmotdepassinput, mdayinput, mmonthinput, myearinput, maddressseinput, mnuminput, memailinput;

    private Button mplaybutton;
    private ImageView back;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_patient_2);


        mnameinput = (EditText) findViewById(R.id.inom);
        mplaybutton = (Button) findViewById(R.id.button_confirm);
        mdayinput = (EditText) findViewById(R.id.day);
        mmonthinput = (EditText) findViewById(R.id.month);
        myearinput = (EditText) findViewById(R.id.year);
        mnuminput = (EditText) findViewById(R.id.inumero);
        memailinput = (EditText) findViewById(R.id.iemail);
        mmotdepassinput = (EditText) findViewById(R.id.imotdepass);

       /* mplaybutton.setEnabled(false);
        mnameinput.addTextChangedListener(loginTextWatcher);
        mdateinput.addTextChangedListener(loginTextWatcher);
        maddressseinput.addTextChangedListener(loginTextWatcher);
        mnuminput.addTextChangedListener(loginTextWatcher);
        memailinput.addTextChangedListener(loginTextWatcher);
        mmotdepassinput.addTextChangedListener(loginTextWatcher);*/

        back = (ImageView) findViewById(R.id.iback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ref = FirebaseDatabase.getInstance().getReference().child("Users").child("Patients");
        userr = FirebaseAuth.getInstance().getCurrentUser();
        userrID = userr.getUid();
        reff = FirebaseDatabase.getInstance().getReference().child("Users").child("Patients").child(userrID).child("BirthDay");

        ref.child(userrID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PatientData userprofile = snapshot.getValue(PatientData.class);
                if (userprofile !=null) {
                    String motdepasse = userprofile.getPassword();
                    String email= userprofile.getEmail();


                    mmotdepassinput.setText(motdepasse);
                    memailinput.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        mplaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = mnameinput.getText().toString();
                String email = memailinput.getText().toString();
                String numero = mnuminput.getText().toString();
                String motdepasse = mmotdepassinput.getText().toString();
                String day = mdayinput.getText().toString();
                String month = mmonthinput.getText().toString();
                String year = myearinput.getText().toString();

                if ((!(motdepasse.isEmpty())) && (!(email.isEmpty()))) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("name", Name);
                    hashMap.put("email", email);
                    hashMap.put("phone", numero);
                    hashMap.put("password", motdepasse);

                    HashMap<String, Object> hashMapp = new HashMap<>();

                    hashMapp.put("day", day);
                    hashMapp.put("month", month);
                    hashMapp.put("year", year);

                    ref.child(userrID).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(EditPatientProfile.this, "données modifié", Toast.LENGTH_LONG).show();
                        }
                    });
                    reff.updateChildren(hashMapp);
                }
            }
        });

    }
}
                 /*   FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                UserProfileChangeRequest request=new UserProfileChangeRequest.Builder().setDisplayName(Name).build();
                firebaseUser.updateProfile(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                      Toast.makeText(EditPatientProfile.this,"good",Toast.LENGTH_LONG).show();
                    }
                });*/

   /* private TextWatcher loginTextWatcher =new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mplaybutton.setEnabled(s.toString().length() != 0);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void envoyer (View view){

        Intent intent=new Intent(this, PatientProfile.class);
        EditText editText=(EditText) findViewById(R.id.inumero);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }*/
