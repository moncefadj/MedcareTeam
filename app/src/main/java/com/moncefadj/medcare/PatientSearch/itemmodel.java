package com.moncefadj.medcare.PatientSearch;

public class itemmodel {

    private String name;
    private int imge;

    public itemmodel(String name, int imge) {
        this.name = name;
        this.imge = imge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getImge() {
        return imge;
    }

    public void setImge(int imge) {
        this.imge = imge;
    }
}
