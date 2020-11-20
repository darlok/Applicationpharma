package com.example.applicationpharma;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Modele.Pharmacie;
import Modele.dao.DAO;
import vue.adapter.PharmacieAdapter;
import vue.fragment.RecyclerViewClickListener;

public class ActivityPharmacie extends AppCompatActivity implements RecyclerViewClickListener{

    private List<Pharmacie> lesPharmacies;

    private RecyclerView PharmacierecyclerView;
    private PharmacieAdapter monAdapterPharma;

    private DAO accesDonnees;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacie);

        lesPharmacies = new ArrayList<Pharmacie>();
        this.accesDonnees= new DAO(this);

        Intent i = getIntent();
        String dpt = i.getStringExtra("codeDpt");
        //Toast.makeText(ActivityPharmacie.this,"bonjour", Toast.LENGTH_SHORT).show();

        this.gererViewRecycler();

    }
    private void gererViewRecycler()
    {
        //lesPharmacies.add(new Pharmacie("01","cc","9","","","","","","","","","",""));


        //lesPharmacies=accesDonnees.getPharmaciesParDepartement("15");
        PharmacierecyclerView = (RecyclerView) findViewById(R.id.activity_main_Pharmacie_recyclerview);
        PharmacierecyclerView.setLayoutManager(new LinearLayoutManager(this));
        monAdapterPharma = new PharmacieAdapter(lesPharmacies, this);
        PharmacierecyclerView.setAdapter(monAdapterPharma);
    }

    @Override
    public void onListItemClick(int position) {
        Intent i = new Intent(ActivityPharmacie.this,ActivityPharmacie.class);
        i.putExtra("codeDpt",lesPharmacies.get(position).getNumVoie());
        startActivity(i);

    }
}