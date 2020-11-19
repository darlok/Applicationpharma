package com.example.applicationpharma;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Modele.Pharmacie;
import vue.adapter.PharmacieAdapter;

public class ActivityPharmacie extends AppCompatActivity {

    private List<Pharmacie> lesPharmacies;
    private RecyclerView PharmacierecyclerView;
    private PharmacieAdapter monAdapterPharma;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacie);
        Intent i = getIntent();
        String dpt = i.getStringExtra("codeDpt");
        Toast.makeText(ActivityPharmacie.this,dpt, Toast.LENGTH_SHORT).show();
        //this.gererViewRecycler();

    }
    private void gererViewRecycler()
    {
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


        // lesPharmacies=accesDonnees.chargeLesPharmacies();
        PharmacierecyclerView = (RecyclerView) findViewById(R.id.activity_main_Pharmacie_recyclerview);
        PharmacierecyclerView.setLayoutManager(new LinearLayoutManager(this));
        monAdapterPharma = new PharmacieAdapter(lesPharmacies);
        PharmacierecyclerView.setAdapter(monAdapterPharma);
    }
}