package com.example.applicationpharma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Modele.Pharmacie;
import adapter.PharmacieAdapter;

public class MainActivity extends AppCompatActivity {

    private Pharmacie Pharma;
    private List<Pharmacie>lesPharmacies;
    private RecyclerView PharmacierecyclerView;

    PharmacieAdapter monAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lesPharmacies= new ArrayList<Pharmacie>();

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
}