package Modele.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import Modele.Departement;
import Modele.Pharmacie;


public class DAO extends SQLiteAssetHelper {
    private SQLiteDatabase database;

    //Pharmacie
    public static final String TABLE_PHARMACIES = "Pharmacie";
    public static String COLUMN_P_noFiness = "noFiness";
    public static String COLUMN_P_raisonSociale = "raisonSociale";
    public static String COLUMN_P_numVoie = "numVoie";
    public static String COLUMN_P_typeVoie= "typeVoie";
    public static String COLUMN_P_voie = "voie";
    public static String COLUMN_P_lieuDitBp = "lieuDitBp";
    public static String  COLUMN_P_codeDepartement= "codeDepartement";
    public static String COLUMN_P_codePostal = "codePostal";
    public static String COLUMN_P_ville = "ville";
    public static String COLUMN_P_telephone = "telephone";
    public static String COLUMN_P_telecopie = "telecopie";
    public static String COLUMN_P_numCategorie = "numCategorie";
    public static String COLUMN_P_siret = "siret";
    public static String COLUMN_P_codeAPE= "codeAPE";
    //Departement
    public static final String TABLE_Departement = "Departement";
    public static String COLUMN_D_codeDpt = "codeDpt";
    public static String COLUMN_D_nomDpt= "nomDpt";

    private static final String DATABASE_NAME = "pharmacies.db";
    private static final int DATABASE_VERSION = 2;



    private String[] allColumnsPharma = {
            COLUMN_P_noFiness = "noFiness",
            COLUMN_P_raisonSociale = "raisonSociale",
            COLUMN_P_numVoie = "numVoie",
            COLUMN_P_typeVoie= "typeVoie",
            COLUMN_P_voie = "voie",
            COLUMN_P_lieuDitBp = "lieuDitBp",
            COLUMN_P_codeDepartement= "codeDepartement",
            COLUMN_P_codePostal = "codePostal",
            COLUMN_P_ville = "ville",
            COLUMN_P_numCategorie= "numCategorie",
            COLUMN_P_telephone = "telephone",
            COLUMN_P_telecopie = "telecopie",
            COLUMN_P_siret = "siret",
            COLUMN_P_codeAPE= "codeAPE",
    };


    private String[] allColumnsDepartement = {
            COLUMN_D_nomDpt="nomDpt",
            COLUMN_D_codeDpt="codeDpt",
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

    public Departement getDepartement(String codeDpt){
        Cursor c = database.query(TABLE_Departement,
                allColumnsDepartement,  COLUMN_D_codeDpt+ " LIKE \"%" + codeDpt +"%\"", null, null, null, null);

        Departement m = null;
        if (c.moveToNext()){
            m = ligneToDepartement(c);
            c.close();
        }

        c.close();
        return m;

    }






}
