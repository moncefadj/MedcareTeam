package com.moncefadj.medcare.DataClasses;

public class PatientData {

    private String name, email,phone,day,month,year,password,id,time,num;



    public PatientData(){
    }


    public PatientData(String vNomPatient, String vHeure,String phone,String vId,String num ,String day,String month,String year,String password) {
        this.name = vNomPatient;
        this.email = vHeure;
        this.phone=phone;
        this.id=vId;
        this.day=day;
        this.month=month;
        this.year=year;
        this.password=password;
        this.time = vHeure;
        this.num=num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}