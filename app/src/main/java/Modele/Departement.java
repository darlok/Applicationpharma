package Modele;

import java.util.ArrayList;

public class Departement {
    private String codeDpt,nomDpt;
    private Departement leDepartement;
    private ArrayList<Pharmacie>LesPharmacies;
    public Departement(String codeDpt,String nomDpt){
        this.codeDpt=codeDpt;
        this.nomDpt=nomDpt;
    }
    public String getCodeDpt(){return codeDpt;}
    public void setCodeDpt(){this.codeDpt=codeDpt;}

    public String getNomDpt(){return codeDpt;}
    public void  setNomDpt(){this.codeDpt=codeDpt;}

    public Departement getLeDepartement() {
        return leDepartement;
    }

    public void setLeDepartement(Departement leDepartement) {
        this.leDepartement = leDepartement;
    }

    public void setLaPharmacie(Pharmacie laPharmacie) {
        this.LesPharmacies.add(laPharmacie);
    }

    public ArrayList<Pharmacie> getLesPharmacies() {
        return getLesPharmacies();
    }
}
