package com.moncefadj.medcare.DataClasses;

public class PatientData {

    private String name, email, id;

    public PatientData(){
    }

    public PatientData(String vNomPatient, String vEmail) {
        this.name = vNomPatient;
        this.email = vEmail;
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

    public void setEmail(String vEmail) {
        this.email = vEmail;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

}
