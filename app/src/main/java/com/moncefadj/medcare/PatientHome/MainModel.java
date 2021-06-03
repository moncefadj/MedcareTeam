package com.moncefadj.medcare.PatientHome;

public class MainModel {
    Integer categoryLogo;
    String  categoryName;

    public MainModel(Integer categoryLogo,String categoryName) {
        this.categoryLogo = categoryLogo;
        this.categoryName = categoryName;
    }

    public Integer getCategorieLogo() {
        return categoryLogo;
    }

    public String getCategorieName() {
        return categoryName;
    }
}
