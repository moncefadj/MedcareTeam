package com.moncefadj.medcare.DataClasses;

public class DoctorDataForHomePatient {

    private String name, spec, id, phone, address, desc;

    public DoctorDataForHomePatient(){
    }

    public DoctorDataForHomePatient(String vNomMedecin, String vSpec, String vPhone, String vAdr, String vDsc, String vId) {
        this.name = vNomMedecin;
        this.spec = vSpec;
        this.phone = vPhone;
        this.address = vAdr;
        this.desc = vDsc;
        this.id = vId;
    }



    public String getName() {
        return name;
    }

    public void setName(String vNomPatient) {
        this.name = vNomPatient;
    }

    public String getFullSpecialty() {
        return spec;
    }

    public void setFullSpecialty(String vSpec) {
        this.spec = vSpec;
    }

    public String getId() {
        return id;
    }

    public void setId(String vId) {
        this.id = vId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
