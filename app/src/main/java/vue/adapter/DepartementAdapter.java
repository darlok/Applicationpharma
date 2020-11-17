package vue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import java.util.List;

import Modele.Departement;
import vue.holder.DepartementViewHolder;

public class DepartementAdapter extends RecyclerView.Adapter<DepartementViewHolder> {
    private List<Departement>lesDepartements;

    public DepartementAdapter(List<Departement>lesDepartements){
        this.lesDepartements=lesDepartements;
    }
    public DepartementViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main_departement, viewGroup, false);
        //View view1 = inflater.inflate(R.layout.activity_main_departement)
        return new DepartementViewHolder(view);
    }
    @Override
    public void onBindViewHolder(DepartementViewHolder myViewHolder, int position)
    {
        Departement unDepartement = this.lesDepartements.get(position);
        myViewHolder.bind(unDepartement);
    }
    @Override
    public int getItemCount()
    {
        return this.lesDepartements.size();
    }

    public void refreshPresentations(List<Departement> lesD) {
        notifyItemRangeRemoved(0, this.lesDepartements.size());
        this.lesDepartements.clear();
        this.lesDepartements.addAll(lesD);
        notifyItemRangeChanged(0, this.lesDepartements.size());
    }
}
