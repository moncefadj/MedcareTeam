package com.moncefadj.medcare.medicaments;

import androidx.lifecycle.ViewModel;

import com.moncefadj.medcare.medicaments.medData;

import java.util.ArrayList;

// save data in viewModel instead of Activity
public class MainViewModel extends ViewModel {

    final ArrayList<medData> data = new ArrayList<medData>();

    void addMed(medData med){
        data.add(med);
    }

    public ArrayList<medData> getData() {
        return data;
    }
}