package com.example.applicationpharma;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import Modele.Pharmacie;
import Modele.dao.DAO;
import vue.adapter.PharmacieAdapter;

public class MainActivity extends AppCompatActivity {

    private Pharmacie Pharma;
    private List<Pharmacie>lesPharmacies;
    private RecyclerView PharmacierecyclerView;
    private DAO accesDonnees;
    private final int REQUEST_PERMISSION_EXTERNAL_CARD = 1;
    private static String DB_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ File.separator + "com.gsb.javamedicaments";
    private static String DB_NAME = "gsb_pharmacies.db";



    PharmacieAdapter monAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.gererDroits();

        lesPharmacies= new ArrayList<Pharmacie>();

        this.accesDonnees= new DAO();

        this.gererViewRecycler();

    }


    private void gererViewRecycler()
    {
        lesPharmacies.add(new Pharmacie("010002285","PHARMACIE DU CHAMP DE MARS ",	"9", 	"R ",	"ALEXANDRE BERARD ","","01 ","01500 ","AMBERIEU EN BUGEY ",	"0474380226 ","0474382135",620 ,	"39352920100013" 	));
        //etudiantsInscrits.add(new Etudiant("TERIEUR","Alex"));
        //etudiantsInscrits.add(new Etudiant("KEURIEN","Iris"));

        PharmacierecyclerView = (RecyclerView) findViewById(R.id.activity_main_Pharmacie_recyclerview);

       PharmacierecyclerView.setLayoutManager(new LinearLayoutManager(this));

        monAdapter = new PharmacieAdapter(lesPharmacies);

        PharmacierecyclerView.setAdapter(monAdapter);
    }
    private void gererDroits(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showExplanation("Permission Needed", "Rationale", Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_EXTERNAL_CARD);
            } else {
                this.requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_EXTERNAL_CARD);
            }
        } else {
            Toast.makeText(MainActivity.this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
        }
    }
    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
    }

    private void showExplanation(String title,
                                 String message,
                                 final String permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_EXTERNAL_CARD:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission autorisée par l'utilisateur", Toast.LENGTH_SHORT).show();

                    String state = Environment.getExternalStorageState();
                    if(Environment.MEDIA_MOUNTED.equals(state)){
                        // Both Read and write operations available
                        Log.i("MainActivity", "Il y a bien une carte externe");

                        //je crée le répertoire pour placer la base de données sur la carte externe
                        File appDir = new File(DB_PATH);
                        if(!appDir.exists() && !appDir.isDirectory()){
                            if (appDir.mkdirs()){
                                Log.i("MainActivity","Répertoire Downloads/com.sqlite.gsb.testsqlite créé sur la carte externe");
                                copyDataBase();
                            }
                            else{
                                Log.w("MainActivity","Impossible de créer le répertoire Downloads/com.sqlite.gsb.testsqlite sur la carte externe");
                            }
                        }else{
                            Log.i("MainActivity","Le répertoire Downloads/com.sqlite.gsb.testsqlite existe déjà sur la carte externe");
                        }
                    } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
                        // Only Read operation available
                        Toast.makeText(MainActivity.this, "Il y a bien une carte externe mais elle est en lecture seule\"", Toast.LENGTH_SHORT).show();
                    } else {
                        // SD card not mounted
                        Toast.makeText(MainActivity.this, "Il n'y a pas de carte externe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Autorisation refusée par l'utilisateur", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void copyDataBase() {

        //Open your local db as the input stream
        InputStream myInput = null;
        try {
            myInput = this.getAssets().open("databases" + File.separator + DB_NAME);
            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(DB_PATH + File.separator + DB_NAME);

            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }

            //Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
        }

    }



}