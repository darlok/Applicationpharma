package Modele;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Departement implements Parcelable {
    private String codeDpt,nomDpt;
    private Departement leDepartement;
    private ArrayList<Pharmacie>LesPharmacies;
    public Departement(String codeDpt,String nomDpt){
        this.codeDpt=codeDpt;
        this.nomDpt=nomDpt;
    }

    protected Departement(Parcel in) {
        codeDpt = in.readString();
        nomDpt = in.readString();
        leDepartement = in.readParcelable(Departement.class.getClassLoader());
        LesPharmacies = in.createTypedArrayList(Pharmacie.CREATOR);
    }

    public static final Creator<Departement> CREATOR = new Creator<Departement>() {
        @Override
        public Departement createFromParcel(Parcel in) {
            return new Departement(in);
        }

        @Override
        public Departement[] newArray(int size) {
            return new Departement[size];
        }
    };

    public String getCodeDpt(){return codeDpt;}
    public void setCodeDpt(){this.codeDpt=codeDpt;}

    public String getNomDpt(){return nomDpt;}
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codeDpt);
        dest.writeString(nomDpt);
        dest.writeParcelable(leDepartement, flags);
        dest.writeTypedList(LesPharmacies);
    }
}
