package com.example.applicationpharma;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Modele.Departement;
import Modele.Pharmacie;
import Modele.WebService;
import Modele.dao.DAO;
import Modele.webservicePharma;
import vue.adapter.PharmacieAdapter;
import vue.fragment.RecyclerViewClickListener;

public class ActivityPharmacie extends AppCompatActivity implements RecyclerViewClickListener{

    private List<Pharmacie> lesPharmacies;

    private RecyclerView PharmacierecyclerView;
    private PharmacieAdapter monAdapterPharma;

    private DAO accesDonnees;
    private String dpt;
    private MapsActivity MapsActivity;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacie);
        this.accesDonnees= new DAO(this);

        Intent i = getIntent();
        dpt = i.getStringExtra("codeDpt");
        //Toast.makeText(ActivityPharmacie.this,"bonjour", Toast.LENGTH_SHORT).show();

        this.gererViewRecycler(dpt);

    }
    public void setLesPharmacies(List<Pharmacie> ph)
    {
        this.lesPharmacies=ph;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //ajoute les entrées de menu_pharma à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_pharma, menu);
        return true;
    }

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

    private void gererViewRecycler(String dpt)
    {
        webservicePharma webservice = new webservicePharma((AppCompatActivity)this);
        webservice.execute("http://192.168.90.49:8080/api/Pharmacies/Departements/"+dpt);
        //lesPharmacies.add(new Pharmacie("01","cc","9","1","1","1","1","1","1","1","1","1","1"));
        //lesPharmacies.add(new Pharmacie("01","testpharm","9","1","1","1","1","1","1","1","1","1","1"));
    /*
        lesPharmacies.add(new Pharmacie(
                "010002285",
                "PHARMACIE DU CHAMP DE MARS ",
                "9",
                "R ",
                "ALEXANDRE BERARD ",
                "",
                "01 ",
                "01500 ",
                "AMBERIEU EN BUGEY ",
                "0474380226 ",
                "0474382135",
                "620" ,
                "39352920100013" 	));


        lesPharmacies=accesDonnees.getPharmaciesParDepartement(dpt);
        PharmacierecyclerView = (RecyclerView) findViewById(R.id.activity_main_Pharmacie_recyclerview);
        PharmacierecyclerView.setLayoutManager(new LinearLayoutManager(this));
        monAdapterPharma = new PharmacieAdapter(lesPharmacies, this);
        PharmacierecyclerView.setAdapter(monAdapterPharma);
        */
    }

    @Override
    public void onListItemClick(int position) {
        Intent i = new Intent(ActivityPharmacie.this,ActivityPharmacie.class);
        i.putExtra("codeDpt",lesPharmacies.get(position).getNumVoie());
        startActivity(i);

    }
}