package vue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import java.util.List;

import Modele.Departement;
import vue.fragment.RecyclerViewClickListener;
import vue.holder.DepartementViewHolder;

public class DepartementAdapter extends RecyclerView.Adapter<DepartementViewHolder> {
    private List<Departement>lesDepartements;
    private RecyclerViewClickListener mListener;


    public DepartementAdapter(List<Departement> lesDepartements,RecyclerViewClickListener listener){
        this.lesDepartements = lesDepartements;
        this.mListener=listener;
    }

    public DepartementViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main_departement, viewGroup, false);
        //View view1 = inflater.inflate(R.layout.activity_main_departement)
        return new DepartementViewHolder(view,mListener);
    }
    @Override
    public void onBindViewHolder(DepartementViewHolder myViewHolder, int position)
    {
        Departement unDepartement = this.lesDepartements.get(position);
        myViewHolder.bind(unDepartement);
    }
    @Override
    public int getItemCount() {
        return lesDepartements.size();
    }


}
