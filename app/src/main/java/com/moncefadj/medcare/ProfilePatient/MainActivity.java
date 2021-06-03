package com.moncefadj.medcare.ProfilePatient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.moncefadj.medcare.R;

public class MainActivity extends AppCompatActivity {

    private Button play;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_patient_1);


        play= (Button) findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAct =new Intent(getApplicationContext(),MainActivity2.class);
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

        Intent intent= getIntent();
        String message =intent.getStringExtra(MainActivity2.EXTRA_MESSAGE);
        EditText editText=findViewById(R.id.n);
        editText.setText(message);

    }
}