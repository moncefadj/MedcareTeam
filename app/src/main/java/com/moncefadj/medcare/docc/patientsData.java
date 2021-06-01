package com.moncefadj.medcare.docc;

public class patientsData {

        String NomPatient, deb, fin;
        int imgPatient;

        public int getimgPatient() {
            return imgPatient;
        }

        public void setimgPatient(Integer imgPatient) {
            this.imgPatient = imgPatient;
        }

        public patientsData(String NomPatient, String deb, String fin, int imgPatient) {
            this.NomPatient = NomPatient;
            this.deb = deb;
            this.fin = fin;
            this.imgPatient = imgPatient;
        }

        public String geNomPatient() {
            return NomPatient;
        }

        public void setNomPatient(String NomPatient) {
            this.NomPatient = NomPatient;
        }

        public String getdeb() {
            return deb;
        }

        public void setdeb(String deb) {
            this.deb = deb;
        }

        public String getfin() {
            return fin;
        }

        public void setimgPatient(int imgPatient) {
            this.imgPatient = imgPatient;
        }

    public String getNomPatient() { return NomPatient;
    }
}
