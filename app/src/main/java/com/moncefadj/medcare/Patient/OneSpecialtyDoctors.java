package com.moncefadj.medcare.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.moncefadj.medcare.R;

public class OneSpecialtyDoctors extends AppCompatActivity {
 TextView textView;
    //vertical view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_one_specialty_doctors);
textView=findViewById(R.id.dd);
    }
}