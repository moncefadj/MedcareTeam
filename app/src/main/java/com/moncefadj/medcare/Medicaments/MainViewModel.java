package com.moncefadj.medcare.Medicaments;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

// save data in viewModel instead of Activity
public class MainViewModel extends ViewModel {

    final ArrayList<medDataDb> data ;

    public MainViewModel() {
        data = new ArrayList<medDataDb>();
    }

    void addMed(medDataDb med){
        data.add(med);
    }

    public ArrayList<medDataDb> getData() {
        return data;
    }
}