package com.moncefadj.medcare.ProfilePatient;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.moncefadj.medcare.R;

public class EditPatientProfile extends AppCompatActivity {
    public static final String EXTRA_MESSAGE ="com.example.test.MESSAGE";


    private EditText mnameinput ,mmotdepassinput,mdateinput,maddressseinput,mnuminput,memailinput;

    private Button mplaybutton;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_patient_2);


        mnameinput =(EditText) findViewById(R.id.inom);
        mplaybutton = (Button) findViewById(R.id.button_confirm);
        mdateinput = (EditText) findViewById(R.id.idate);
        maddressseinput=(EditText) findViewById(R.id.iadresse);
        mnuminput = (EditText) findViewById(R.id.inumero);
        memailinput = (EditText) findViewById(R.id.iemail);
        mmotdepassinput =(EditText) findViewById(R.id.imotdepass);

        mplaybutton.setEnabled(false);
        mnameinput.addTextChangedListener(loginTextWatcher);
        mdateinput.addTextChangedListener(loginTextWatcher);
        maddressseinput.addTextChangedListener(loginTextWatcher);
        mnuminput.addTextChangedListener(loginTextWatcher);
        memailinput.addTextChangedListener(loginTextWatcher);
        mmotdepassinput.addTextChangedListener(loginTextWatcher);

        back=(ImageView) findViewById(R.id.iback) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private TextWatcher loginTextWatcher =new TextWatcher() {
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
    }
}