package Modele;

public class Pharmacie {
    private String noFiness, raisonSociale,numVoie,typeVoie,voie,lieuDitBp,codeDepartement,codePostal,Ville,telephone,telecopie,siret;
    private Integer numCategorie;
    public Pharmacie(String noFiness, String raisonSociale,String numVoie,String typeVoie, String voie,String lieuDitBp,String codeDepartement,String codePostal,String Ville,String telephone,String telecopie,Integer numCategorie,String siret){
        this.codeDepartement=codeDepartement;
        this.codePostal=codePostal;
        this.lieuDitBp=lieuDitBp;
        this.noFiness=noFiness;
        this.numCategorie=numCategorie;
        this.numVoie=numVoie;
        this.raisonSociale=raisonSociale;
        this.siret=siret;
        this.telecopie=telecopie;
        this.telephone=telephone;
        this.typeVoie=typeVoie;
        this.Ville=Ville;
        this.voie=voie;

    }
    public String getNoFiness(){return noFiness;}
    public void setNoFiness(){this.noFiness= noFiness;}

    public String getRaisonSociale(){return raisonSociale;}
    public void setRaisonSociale(){this.raisonSociale= raisonSociale;}

    public String getNumVoie(){return numVoie;}
    public void setNumVoie(){this.numVoie= numVoie;}

    public String getTypeVoie(){return typeVoie;}
    public void setTypeVoie(){this.typeVoie= typeVoie;}

    public String getVoie(){return voie;}
    public void setVoie(){this.voie= voie;}

    public String getLieuDitBp(){return lieuDitBp;}
    public void setLieuDitBp(){this.lieuDitBp= lieuDitBp;}

    public String getCodeDepartement(){return codeDepartement;}
    public void setCodeDepartement(){this.codeDepartement= codeDepartement;}

    public String getCodePostal(){return codePostal;}
    public void setCodePostal(){this.codePostal= codePostal;}

    public String getVille(){return Ville;}
    public void setVille(){this.Ville= Ville;}

    public String getTelephone(){return telephone;}
    public void setTelephone(){this.telephone= telephone;}

    public String getTelecopie(){return telecopie;}
    public void setTelecopie(){this.telecopie= telecopie;}

    public String getSiret(){return siret;}
    public void setSiret(){this.siret= siret;}

    public Integer getNumCategorie(){return numCategorie;}
    public void setNumCategorie(){this.numCategorie= numCategorie;}


}
