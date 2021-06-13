
package com.moncefadj.medcare.DataClasses;

public class PatientData {

    private String name, heure, id;

    public PatientData(){
    }

    public PatientData(String vNomPatient, String vHeure) {
        this.name = vNomPatient;
        this.heure = vHeure;
    }

    public String getName() {
        return name;
    }

    public void setName(String vNomPatient) {
        this.name = vNomPatient;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

}