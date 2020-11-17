package Modele;

public class Departement {
    private String codeDpt,nomDpt;
    private Departement leDepartement;
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

}
