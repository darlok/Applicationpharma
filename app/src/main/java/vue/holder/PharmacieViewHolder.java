package vue.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import Modele.Pharmacie;
import vue.fragment.RecyclerViewClickListener;

public class PharmacieViewHolder extends RecyclerView.ViewHolder {
    private TextView nom,telecopie;
    private  TextView ville;
    private  TextView telephone;
    private RecyclerViewClickListener listerner;


    public PharmacieViewHolder(View itemView){
        super((itemView));
        nom = itemView.findViewById(R.id.activity_main_pharmacie_nom);
        ville = itemView.findViewById(R.id.activity_main_pharmacie_ville);
        telephone = itemView.findViewById(R.id.activity_main_pharmacie_telephone);
        telecopie= itemView.findViewById(R.id.activity_main_pharmacie_telecopie);


    }
    public  void  bind(Pharmacie Pharma){
        nom.setText(Pharma.getRaisonSociale());
        ville.setText(Pharma.getVille());
        telecopie.setText(Pharma.getTelecopie());
        telephone.setText(Pharma.getTelephone());
    }

}
