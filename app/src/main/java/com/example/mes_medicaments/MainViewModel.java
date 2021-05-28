package com.example.mes_medicaments;

import androidx.lifecycle.ViewModel;

import com.example.mes_medicaments.models.medData;

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
