package vue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import java.util.List;

import Modele.Pharmacie;
import Modele.dao.DAO;
import vue.holder.PharmacieViewHolder;

public class PharmacieAdapter extends RecyclerView.Adapter<PharmacieViewHolder> {
    private List<Pharmacie>lesPharmacies;
    private DAO accesDonnees;

    public PharmacieAdapter(DAO accesDonnees,String RaisonSoiraisonSociale){
        this.accesDonnees=accesDonnees;
        this.lesPharmacies=accesDonnees
    }


    public PharmacieAdapter(List<Pharmacie>lesPharmacies){
        this.lesPharmacies=lesPharmacies;
    }
    public PharmacieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main_pharmacie, viewGroup, false);
        return new PharmacieViewHolder(view);
    }
    @Override
    public void onBindViewHolder(PharmacieViewHolder myViewHolder, int position)
    {
       Pharmacie unePharmacie = this.lesPharmacies.get(position);
        myViewHolder.bind(unePharmacie);
    }
    @Override
    public int getItemCount()
    {
        return this.lesPharmacies.size();
    }

    public void refreshPresentations(List<Pharmacie> lesP) {
        notifyItemRangeRemoved(0, this.lesPharmacies.size());
        this.lesPharmacies.clear();
        this.lesPharmacies.addAll(lesP);
        notifyItemRangeChanged(0, this.lesPharmacies.size());
    }
}
