package vue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import java.util.List;

import Modele.Pharmacie;
import vue.fragment.RecyclerViewClickListener;
import vue.holder.PharmacieViewHolder;

public class PharmacieAdapter extends RecyclerView.Adapter<PharmacieViewHolder> {
    private List<Pharmacie>lesPharmacies;
    private RecyclerViewClickListener mListener;

    public PharmacieAdapter(List<Pharmacie>lesPharmacies, RecyclerViewClickListener listener){
        this.mListener=listener;
        this.lesPharmacies = lesPharmacies;
    }

    public PharmacieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_pharmacie, viewGroup, false);
        return new PharmacieViewHolder(view,mListener);
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

}
