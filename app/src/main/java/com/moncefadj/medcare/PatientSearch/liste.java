package com.moncefadj.medcare.PatientSearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import com.moncefadj.medcare.Patient.PatientHome;
import com.moncefadj.medcare.R;

public class liste extends AppCompatActivity {
    ListView list;
    String[] titles;
    String[] description;
    int[] imgs={R.drawable.doc12,R.drawable.doc12,R.drawable.doc12,R.drawable.doc12
            ,R.drawable.doc12,R.drawable.doc12,R.drawable.doc12
            ,R.drawable.doc12,R.drawable.doc12,R.drawable.doc12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        Resources res=getResources();
        titles =res.getStringArray(R.array.titles);
        description=res.getStringArray(R.array.description);
        list=(ListView) findViewById(R.id.list1);


    }
}