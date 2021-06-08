package com.moncefadj.medcare.DataClasses;

public class DoctorDataForHomePatient {

    private String name, email, id;

    public DoctorDataForHomePatient(){
    }

    public DoctorDataForHomePatient(String vNomMedecin, String vEmail) {
        this.name = vNomMedecin;
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
