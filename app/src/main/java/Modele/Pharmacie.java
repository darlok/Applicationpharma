package Modele;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Pharmacie extends ArrayList<Departement> implements Parcelable {
    private String noFiness, raisonSociale,numVoie,typeVoie,voie,lieuDitBp,codeDepartement,codePostal,Ville,telephone,telecopie,siret,numCategorie;
    private Pharmacie laPharmacie;
    private Departement leDepartement;

    public Pharmacie(String noFiness, String raisonSociale,String numVoie,String typeVoie, String voie,String lieuDitBp,String codeDepartement,String codePostal,String Ville,String telephone,String telecopie,String numCategorie,String siret){
        this.noFiness=noFiness;
        this.raisonSociale=raisonSociale;
        this.numVoie=numVoie;
        this.typeVoie=typeVoie;
        this.voie=voie;
        this.lieuDitBp=lieuDitBp;
        this.codeDepartement=codeDepartement;
        this.codePostal=codePostal;
        this.Ville=Ville;
        this.telephone=telephone;
        this.telecopie=telecopie;
        this.numCategorie=numCategorie;
        this.siret=siret;
    }

    protected Pharmacie(Parcel in) {
        noFiness = in.readString();
        raisonSociale = in.readString();
        numVoie = in.readString();
        typeVoie = in.readString();
        voie = in.readString();
        lieuDitBp = in.readString();
        codeDepartement = in.readString();
        codePostal = in.readString();
        Ville = in.readString();
        telephone = in.readString();
        telecopie = in.readString();
        siret = in.readString();
        numCategorie = in.readString();
        laPharmacie = in.readParcelable(Pharmacie.class.getClassLoader());
    }

    public static final Creator<Pharmacie> CREATOR = new Creator<Pharmacie>() {
        @Override
        public Pharmacie createFromParcel(Parcel in) {
            return new Pharmacie(in);
        }

        @Override
        public Pharmacie[] newArray(int size) {
            return new Pharmacie[size];
        }
    };

    public String getNoFiness()
    { return noFiness; }
    public void setNoFiness()
    { this.noFiness= noFiness; }


    public String getRaisonSociale()
    { return raisonSociale; }
    public void setRaisonSociale()
    { this.raisonSociale= raisonSociale; }

    public String getNumVoie()
    { return numVoie; }
    public void setNumVoie()
    { this.numVoie= numVoie; }

    public String getTypeVoie()
    { return typeVoie; }
    public void setTypeVoie()
    { this.typeVoie= typeVoie; }

    public String getVoie()
    { return voie; }
    public void setVoie()
    { this.voie= voie; }

    public String getLieuDitBp()
    { return lieuDitBp; }
    public void setLieuDitBp()
    { this.lieuDitBp= lieuDitBp; }

    public String getCodeDepartement()
    { return codeDepartement; }
    public void setCodeDepartement()
    { this.codeDepartement= codeDepartement; }

    public String getCodePostal()
    {return codePostal;}
    public void setCodePostal()
    {this.codePostal= codePostal;}

    public String getVille()
    {return Ville;}
    public void setVille()
    {this.Ville= Ville;}

    public String getTelephone()
    {return telephone;}
    public void setTelephone()
    {this.telephone= telephone;}

    public String getTelecopie()
    {return telecopie;}
    public void setTelecopie()
    {this.telecopie= telecopie;}

    public String getSiret()
    {return siret;}

    public void setSiret()
    {this.siret= siret;}

    public String getNumCategorie()
    {return numCategorie;}

    public void setNumCategorie()
    {this.numCategorie= numCategorie;}

    public Pharmacie getLaPharmacie() {
        return laPharmacie ;
    }

    public void setLaPharmacie(Pharmacie laPharmacie) {
        this.laPharmacie = laPharmacie;
    }


    public Departement getLeDepartement() {
        return leDepartement;
    }

    public void setLeDepartement(Departement leDepartement) {
        this.leDepartement = leDepartement;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(noFiness);
        dest.writeString(raisonSociale);
        dest.writeString(numVoie);
        dest.writeString(typeVoie);
        dest.writeString(voie);
        dest.writeString(lieuDitBp);
        dest.writeString(codeDepartement);
        dest.writeString(codePostal);
        dest.writeString(Ville);
        dest.writeString(telephone);
        dest.writeString(telecopie);
        dest.writeString(siret);
        dest.writeString(numCategorie);
        dest.writeParcelable(laPharmacie, flags);
    }
}
