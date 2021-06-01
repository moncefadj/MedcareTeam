package com.moncefadj.medcare.docc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.moncefadj.medcare.R;

public class accountActivity extends AppCompatActivity {

    Button myButton1;
    ImageButton myImageButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        myImageButton1 = (ImageButton) findViewById(R.id.ib2);
        myImageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(com.moncefadj.medcare.docc.accountActivity.this, com.moncefadj.medcare.docc.DoctorDashboard.class);
                startActivity(intentLoadNewActivity);
            }
        });

        myButton1 = (Button) findViewById(R.id.ib3);
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(com.moncefadj.medcare.docc.accountActivity.this, com.moncefadj.medcare.docc.DoctorDashboard.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }
}