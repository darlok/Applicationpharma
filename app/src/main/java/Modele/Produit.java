package Modele;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class  Produit implements Parcelable {
    private String codePdt,denominationPdt,indicationsPdt,formePdt;

    public Produit(String codePdt, String denominationPdt,String indicationPdt,String formePdt){
        this.codePdt=codePdt;
        this.denominationPdt=denominationPdt;
        this.indicationsPdt= indicationPdt;
        this.formePdt=formePdt;
    }
    public Produit(String codePdt, String denominationPdt){
        this.codePdt=codePdt;
        this.denominationPdt=denominationPdt;
    }

    public Produit(Parcel in) {
        codePdt = in.readString();
        denominationPdt = in.readString();
        indicationsPdt= in.readString();
        formePdt= in.readString();

    }

    public static final Creator<Produit> CREATOR = new Creator<Produit>() {
        @Override
        public Produit createFromParcel(Parcel in) {
            return new Produit(in);
        }

        @Override
        public Produit[] newArray(int size) {
            return new Produit[size];
        }
    };

    public String getCodePdt(){return codePdt;}
    public void setCodePdt(){this.codePdt=codePdt;}

    public String getDenominationPdt(){return denominationPdt;}
    public void  setDenominationPdt(){this.denominationPdt=denominationPdt;}

    public String getIndicationsPdt(){return indicationsPdt;}
    public void  setIndicationsPdt(){this.indicationsPdt=indicationsPdt;}

    public String getFormePdt(){return formePdt;}
    public void  setFormePdt(){this.formePdt=formePdt;}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codePdt);
        dest.writeString(denominationPdt);
        dest.writeString(indicationsPdt);
        dest.writeString(formePdt);
    }
}
