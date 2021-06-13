package com.moncefadj.medcare.Medicaments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moncefadj.medcare.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ajouterMed extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, AdapterView.OnItemClickListener , DatePickerDialog.OnDateSetListener {
    private EditText EnomMed;
    private EditText Description;
    private EditText dateDebut, dateFin ;
    private TextView heure , heure2,heure3;
    private Button ajouter_med ;
    private Spinner instructions;
    private Button ajouter_au_list;
    private  int index =0;
    DatePickerDialog datePickerDialog;
    FirebaseDatabase data_base;
    DatabaseReference medsReference;
    DatabaseReference medReference;
    FirebaseUser uPatient;
    String uidPatient ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_med);
        EnomMed = (EditText) findViewById(R.id.nomMed);
        heure = (TextView) findViewById(R.id.heure);
        heure2 = (TextView) findViewById(R.id.heure2);
        heure3 = (TextView) findViewById(R.id.heure3);
        Description = (EditText) findViewById(R.id.description);

        ajouter_med = (Button) findViewById(R.id.ajoutertemps);
        ajouter_au_list = (Button ) findViewById(R.id.ajouterAuliste);
        instructions = (Spinner) findViewById(R.id.spinnerInst);
        ArrayAdapter<CharSequence> instructAdapter = ArrayAdapter.createFromResource(this, R.array.instructions, android.R.layout.simple_spinner_item);
        instructAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instructions.setAdapter(instructAdapter);
        instructions.getOnItemSelectedListener();
        dateDebut = (EditText) findViewById(R.id.dateDebut);
        dateFin = (EditText)  findViewById(R.id.dateFin);

        ajouter_med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opentimepicker();

            }
        });
        ajouter_au_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
        dateDebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });
        dateFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker2();

            }
        });


    }



    private void sendData() {
        String name = EnomMed.getText().toString();
        String descrip = Description.getText().toString();
        String time = heure.getText().toString();
        String time2 = heure2.getText().toString();
        String time3 = heure3.getText().toString();
        String instruction = instructions.getSelectedItem().toString();
        String datedebut = dateDebut.getText().toString();
        String datefin = dateFin.getText().toString();
        try {
            if (name.isEmpty()) {
                EnomMed.setError("le nom de medicament est obligatoire !");

            }
            if (descrip.isEmpty()) {
                Description.setError("veuillez donner une petit description ! ");

            }
            if (datedebut.isEmpty()) {
                dateDebut.setError("la date de debut de prise est obligatoire  !");

            }
            if (datefin.isEmpty()) {
                dateFin.setError("la fin de prise est obligatoire ! ");

            }
            if (time.isEmpty()) {
                heure.setError("veuillez donner au moins une heure  !");
                return;
            }
        }
        catch (Exception e){
            movetoliste();

        }


        Toast.makeText(this,"data passes in the second activity ",Toast.LENGTH_LONG).show();

        uPatient = FirebaseAuth.getInstance().getCurrentUser();
        uidPatient = uPatient.getUid();
        data_base = FirebaseDatabase.getInstance();
        medsReference = data_base.getReference().child("Users").child("Patients").child(uidPatient).child("Medicaments");
        medReference = medsReference.child(name);
        medReference.setValue( new medDataDb(name,descrip,time,time2,time3,datedebut,datefin,instruction));


        finish();
    }
    private void movetoliste() {
        Intent intent = new Intent(this , liste_medicaments.class);
        startActivity(intent);
    }



    private void opentimepicker() {
        DialogFragment timePickerDialog = new TimePickerFragment();
        timePickerDialog.show(getSupportFragmentManager(), "time picker");

    }
    public void onTimeSet(TimePicker timePicker, int intHourOfDay, int intMinute) {
        if(heure.getText().toString().equals("")) {

            heure.setText((intHourOfDay + ":" + intMinute));
        }
        else if (heure2.getText().toString().equals("")){
            heure2.setText((intHourOfDay + ":" + intMinute ));
        }
        else {
            heure3.setText((intHourOfDay + ":" + intMinute ));
        }


        ajouter_med.setText("ajouter une autre heure de prise ");
        ajouter_med.setTextColor(Color.GRAY);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),choice, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateDebut.setText(dayOfMonth +","+ month +"," + year);
    }
    public void openDatePicker(){
        final Calendar calendar = Calendar.getInstance();
        int myear = calendar.get(Calendar.YEAR);
        int mmonth = calendar.get(Calendar.MONTH);
        int mday = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(ajouterMed.this,R.style.AppCompatDialogStyle ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(year , month, day);
                dateDebut.setText(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
                int myear = calendar.get(Calendar.YEAR);
                int mmonth = calendar.get(Calendar.MONTH);
                int mday = calendar.get(Calendar.DAY_OF_MONTH);




            }

        },myear,mmonth,mday);
        datePickerDialog.show();
    }
    public void openDatePicker2(){
        final Calendar calendar = Calendar.getInstance();
        int myear = calendar.get(Calendar.YEAR);
        int mmonth = calendar.get(Calendar.MONTH);
        int mday = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(ajouterMed.this,R.style.AppCompatDialogStyle ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(year , month, day);
                dateFin.setText(new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
                int myear = calendar.get(Calendar.YEAR);
                int mmonth = calendar.get(Calendar.MONTH);
                int mday = calendar.get(Calendar.DAY_OF_MONTH);




            }

        },myear,mmonth,mday);
        datePickerDialog.show();
    }
}