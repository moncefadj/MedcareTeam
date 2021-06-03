package com.moncefadj.medcare.Medicaments;

import androidx.lifecycle.ViewModel;

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