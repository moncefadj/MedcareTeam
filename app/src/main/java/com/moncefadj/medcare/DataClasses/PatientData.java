
package com.moncefadj.medcare.DataClasses;

public class PatientData {

    private String name, time, id;

    public PatientData(){
    }

    public PatientData(String vNomPatient, String vHeure, String vId) {
        this.name = vNomPatient;
        this.time = vHeure;
        this.id = vId;
    }

    public String getName() {
        return name;
    }

    public void setName(String vNomPatient) {
        this.name = vNomPatient;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}