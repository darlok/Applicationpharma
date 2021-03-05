package com.example.applicationpharma;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.MatrixCursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


import Modele.Departement;
import Modele.dao.DAO;
import retrofit2.Retrofit;
import vue.adapter.DepartementAdapter;
import vue.adapter.PharmacieAdapter;
import vue.fragment.RecyclerViewClickListener;


public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private List<Departement> lesDepartements;

    private RecyclerView DepartementrecyclerView;


    private DAO accesDonnees;
    private final int REQUEST_PERMISSION_EXTERNAL_CARD = 1;
    private SearchView recherche;
    private static String DB_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ File.separator + "pharma";
    private static String DB_NAME = "pharmacies.db";


    private DepartementAdapter monAdapterDep;
    private PharmacieAdapter monAdapterPharma;
    private android.view.Menu menu;
    private Object MenuItem;
    private android.view.MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showExplanation("Permission Needed", "Rationale", Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_EXTERNAL_CARD);
            } else {
                requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_EXTERNAL_CARD);
            }
        } else {
            Toast.makeText(MainActivity.this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
        }

        lesDepartements = new ArrayList<Departement>();

        this.accesDonnees= new DAO(this);

        this.gererViewRecycler();


    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //ajoute les entrées de menu_depart à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_depart, menu);

        this.menu = menu;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }
            });
        }

        return true;
    }
            /*
            Liens pour interface et barre de recherche, à conserver :
            ->
                https://stackoverflow.com/questions/21585326/implementing-searchview-in-action-bar
                https://developpement-informatique.com/article/252/interfaces-et-heritage-multiple-en-java#
             */


    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void gererViewRecycler()
    {
        //DEPARTEMENT
        //lesDepartements.add(new Departement("15","Cantal"));

        lesDepartements = accesDonnees.chargeLesDepartements();
        DepartementrecyclerView = (RecyclerView) findViewById(R.id.activity_main_Departement_recyclerview);
        DepartementrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        monAdapterDep = new DepartementAdapter(lesDepartements,this);
        DepartementrecyclerView.setAdapter(monAdapterDep);

    }

    @Override
    public void onListItemClick(int position) {
        //Toast.makeText(MainActivity.this,lesDepartements.get(position).getNomDpt(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this,ActivityPharmacie.class);
        i.putExtra("codeDpt",lesDepartements.get(position).getCodeDpt());
        startActivity(i);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_EXTERNAL_CARD:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Granted!", Toast.LENGTH_SHORT).show();

                    String state = Environment.getExternalStorageState();
                    if(Environment.MEDIA_MOUNTED.equals(state)){
                        // Both Read and write operations available
                        Log.i("t2", "Il y a bien une carte externe");

                        //je crée le répertoire pour placer la base de données sur la carte externe
                        File appDir = new File(DB_PATH);
                        if(!appDir.exists() && !appDir.isDirectory()){
                            if (appDir.mkdirs()){
                                Log.i("CreateDir","App dir created");
                                copyDataBase();
                            }
                            else{
                                Log.w("CreateDir","Unable to create app dir!");
                            }
                        }else{
                            Log.i("CreateDir","App dir already exists");
                            copyDataBase();
                        }
                    } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
                        // Only Read operation available
                        Toast.makeText(MainActivity.this, "Il y a bien une carte externe mais elle est en lecture seule\"", Toast.LENGTH_SHORT).show();
                    } else {
                        // SD card not mounted
                        Toast.makeText(MainActivity.this, "Il n'y a pas de carte externe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
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

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
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