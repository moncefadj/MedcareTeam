package com.moncefadj.medcare.DataClasses;

public class PatientData {

    private String name, email;

    public PatientData(){
    }

    public PatientData(String vNomPatient, String vHeure) {
        this.name = vNomPatient;
        this.email = vHeure;
    }

    public String getName() {
        return name;
    }

    public void setName(String vNomPatient) {
        this.name = vNomPatient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String vHeure) {
        this.email = vHeure;
    }

}
