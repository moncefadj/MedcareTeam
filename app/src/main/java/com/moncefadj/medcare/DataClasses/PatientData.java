package com.moncefadj.medcare.DataClasses;

public class PatientData {

    private String name, email,phone,day,month,year;
    private String id;

    public PatientData(){
    }

    public PatientData(String vNomPatient, String vHeure,String phone,String id,String day,String month,String year) {
        this.name = vNomPatient;
        this.email = vHeure;
        this.phone=phone;
        this.id=id;
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNum() {
        return phone;
    }

    public void setNum(String phone) { this.phone =phone; }

    public String getDay() {
        return day;
    }

    public void setDay(String day) { this.day =day; }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month =month; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year =year; }



}
