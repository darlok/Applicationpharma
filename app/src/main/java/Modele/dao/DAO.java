package Modele.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import Modele.Departement;
import Modele.Pharmacie;


public class DAO extends SQLiteAssetHelper {
    private SQLiteDatabase database;

    //Pharmacie
    public static final String TABLE_PHARMACIES = "Pharmacie";
    public static final String COLUMN_M_noFiness = "noFiness";
    public static final String COLUMN_M_raisonSociale = "raisonSociale";
    public static final String COLUMN_M_numVoie = "numVoie";
    public static final String COLUMN_M_typeVoie= "typeVoie";
    public static final String COLUMN_M_voie = "voie";
    public static final String COLUMN_M_lieuDitBp = "lieuDitBp";
    public static final String COLUMN_M_codeDepartement= "codeDepartement";
    public static final String COLUMN_M_codePostal = "codePostal";
    public static final String COLUMN_M_ville = "ville";
    public static final String COLUMN_M_telephone = "telephone";
    public static final String COLUMN_M_telecopie = "telecopie";
    public static final String COLUMN_M_numCategorie = "numCategorie";
    public static final String COLUMN_M_siret = "siret";
    public static final String COLUMN_M_codeAPE= "codeAPE";
    //Departement
    public static final String TABLE_Departement = "Departement";
    public static final String COLUMN_D_codeDpt = "codeDpt";
    public static final String COLUMN_D_nomDpt= "nomDpt";

    private static final String DATABASE_NAME = "pharmacies.db";
    private static final int DATABASE_VERSION = 2;



    private String[] allColumnsPharma = {
            COLUMN_M_noFiness = "noFiness";
            COLUMN_M_raisonSociale = "raisonSociale";
            COLUMN_M_numVoie = "numVoie";
            COLUMN_M_typeVoie= "typeVoie";
            COLUMN_M_voie = "voie";
            COLUMN_M_lieuDitBp = "lieuDitBp";
            COLUMN_M_codeDepartement= "codeDepartement";
            COLUMN_M_codePostal = "codePostal";
            COLUMN_M_ville = "ville";
            COLUMN_M_telephone = "telephone";
            COLUMN_M_telecopie = "telecopie";
            COLUMN_M_numCategorie = "numCategorie";
            COLUMN_M_siret = "siret";
            COLUMN_M_codeAPE= "codeAPE";
}


    private String[] allColumnsDepartement = {
            COLUMN_D_nomDpt,
            COLUMN_D_codeDpt,
    };



    public DAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.dbHelper = new MySQLiteHelper(context);
        this.open();
    }

    private void open() throws SQLException {
        //getWritableDatabase() => méthode polymorphe qui vient de SQLiteAssetHelper
        this.database = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    private void chargeLeMedicament(Pharmacie p) {
        //je récupère le code cis du médicament correspondant au codeCIP de la présentation en paramètres
        Cursor c = database.query(TABLE_PHARMACIES,
                allColumnsPharma,  COLUMN_D_codeDpt + " = \"" + p.getCodeDepartement()+"\"", null, null, null, null);

        String codeDpt = "";
        Log.e("t",p.getCodeDepartement() + " " +c.getCount());
        if (c.moveToNext()){
            codeDpt = c.getString(c.getColumnIndex(COLUMN_D_codeDpt));
        }
        c.close();

        //récupère le médicament correspondant au code cis
        //la méthode appelée est programmée dans la suite de cette classe, elle crée le médicament
        //et tous ses liens en profondeur
        Departement d = this.getDepartementParDpt(codeDpt);
        d.setLaPharmacie(p);
        p.setLeDepartement(d);
    }
    private void chargeLesPharmacies(Departement d) {
        Cursor c = database.query(TABLE_PHARMACIES,
                allColumnsPharma,  COLUMN_M_codeDepartement + " LIKE \"%" + d.getCodeDpt()+"%\"", null, null, null, null);

        while (c.moveToNext()){
            Pharmacie p = ligneToPharmacies(c);
            d.setLaPharmacies(p);
            p.setLeDepartement(d);
        }
        c.close();
    }


}
