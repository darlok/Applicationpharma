package Modele.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import Modele.Departement;
import Modele.Pharmacie;


public class DAO extends SQLiteAssetHelper {
    private SQLiteDatabase database;

    //Pharmacie
    public static final String TABLE_PHARMACIES = "Pharmacie";
    public static final  String COLUMN_P_noFiness = "noFiness";
    public static final String COLUMN_P_raisonSociale = "raisonSociale";
    public static final String COLUMN_P_numVoie = "numVoie";
    public static final String COLUMN_P_typeVoie= "typeVoie";
    public static final String COLUMN_P_voie = "voie";
    public static final String COLUMN_P_lieuDitBp = "lieuDitBp";
    public static final String  COLUMN_P_codeDepartement= "codeDepartement";
    public static final String COLUMN_P_codePostal = "codePostal";
    public static final String COLUMN_P_ville = "ville";
    public static final String COLUMN_P_telephone = "telephone";
    public static final String COLUMN_P_telecopie = "telecopie";
    public static final String COLUMN_P_numCategorie = "numCategorie";
    public static final String COLUMN_P_siret = "siret";
    public static final String COLUMN_P_codeAPE= "codeAPE";
    //Departement
    public static final String TABLE_Departement = "Departement";
    public static final String COLUMN_D_codeDpt = "codeDpt";
    public static final String COLUMN_D_nomDpt= "nomDpt";

    private static final String DATABASE_NAME = "pharmacies.db";
    private static final int DATABASE_VERSION = 2;



    private String[] allColumnsPharma = {
            COLUMN_P_noFiness ,
            COLUMN_P_raisonSociale,
            COLUMN_P_numVoie,
            COLUMN_P_typeVoie,
            COLUMN_P_voie,
            COLUMN_P_lieuDitBp,
            COLUMN_P_codeDepartement,
            COLUMN_P_codePostal ,
            COLUMN_P_ville,
            COLUMN_P_numCategorie ,
            COLUMN_P_telephone,
            COLUMN_P_telecopie ,
            COLUMN_P_siret ,
            COLUMN_P_codeAPE,
    };


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

    private Departement ligneToDepartement(Cursor c){
        Departement d = new Departement(c.getString(c.getColumnIndex(COLUMN_D_codeDpt)), c.getString(c.getColumnIndex(COLUMN_D_nomDpt)));
        return d;
    }
    private Pharmacie ligneToPharmacies(Cursor c){
       Pharmacie p = new Pharmacie(c.getString(c.getColumnIndex(COLUMN_P_noFiness)), c.getString(c.getColumnIndex(COLUMN_P_raisonSociale)), c.getString(c.getColumnIndex(COLUMN_P_numVoie)),
                c.getString(c.getColumnIndex(COLUMN_P_typeVoie)), c.getString(c.getColumnIndex(COLUMN_P_voie)),c.getString(c.getColumnIndex(COLUMN_P_lieuDitBp)),
                c.getString(c.getColumnIndex(COLUMN_P_codeDepartement)),c.getString(c.getColumnIndex(COLUMN_P_codePostal)), c.getString(c.getColumnIndex(COLUMN_P_ville)),
                c.getString(c.getColumnIndex(COLUMN_P_telephone)), c.getString(c.getColumnIndex(COLUMN_P_telecopie)), c.getString(c.getColumnIndex(COLUMN_P_numCategorie)),
               c.getString(c.getColumnIndex(COLUMN_P_siret)));

        return p;
    }

    public Pharmacie getDepartementParcodeDpt(String codeDpt){
        Cursor c = database.query(TABLE_PHARMACIES,
                allColumnsPharma,  COLUMN_P_codeDepartement+ " LIKE \"%" + codeDpt +"%\"", null, null, null, null);

        Pharmacie p = null;
        if (c.moveToNext()){
            p = ligneToPharmacies(c);
            c.close();
        }

        c.close();
        return p;

    }
    public ArrayList<Departement> getDepartementParNom(String nom){
        ArrayList<Departement> lesDepartement = new ArrayList();

        Cursor c = database.query(TABLE_Departement,
                allColumnsDepartement,  COLUMN_D_nomDpt+ " LIKE \"%" + nom +"%\"", null, null, null, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            Departement m = ligneToDepartement(c);
            lesDepartement.add(m);
            c.moveToNext();
        }

        c.close();
        return lesDepartement;
    }

    private ArrayList<Pharmacie> chargeLesPharmacies(Departement d)
    {
        ArrayList<Pharmacie> lesPharmacies = new ArrayList<Pharmacie>();
        Cursor c = database.query(TABLE_PHARMACIES,
                allColumnsPharma,  COLUMN_D_codeDpt+ " LIKE \"%" + d.getCodeDpt()+"%\"", null, null, null, null);

        while (c.moveToNext()){
            Pharmacie p = ligneToPharmacies(c);
            //d.setLaPharmacie(p);
            p.setLeDepartement(d);
            lesPharmacies.add(p);
        }
        c.close();
        return lesPharmacies;
    }
    public ArrayList<Departement> chargeLesDepartements()
    {
        ArrayList<Departement> lesDepartements = new ArrayList<Departement>();
        Cursor c = database.query(TABLE_Departement,
                allColumnsDepartement,  null, null, null, null, null);

        while (c.moveToNext()){
            Departement d = ligneToDepartement(c);
            //d.setLeDepartement(d);
            lesDepartements.add(d);
        }
        c.close();
        return lesDepartements;
    }

    public ArrayList<Pharmacie> getPharmacieParNomDpt(String nom){
        ArrayList<Pharmacie> lesPharmacies = new ArrayList();

        Cursor c = database.query(TABLE_PHARMACIES,
                allColumnsPharma, COLUMN_P_raisonSociale+ " LIKE \"%" + nom +"%\"", null, null, null, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            Pharmacie p = ligneToPharmacies(c);
            lesPharmacies.add(p);
            c.moveToNext();
        }

        c.close();
        return lesPharmacies;
    }
    public Departement getPharmaciesParcodeDpt(String codeDpt){
        Cursor c = database.query(TABLE_Departement,
                allColumnsDepartement,  COLUMN_D_codeDpt+ " LIKE \"%" + codeDpt +"%\"", null, null, null, null);

        Departement d = null;
        if (c.moveToNext()){
            d = ligneToDepartement(c);
            c.close();
        }

        c.close();
        return d;

    }


}
