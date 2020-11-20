package vue.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import Modele.Pharmacie;
import vue.fragment.RecyclerViewClickListener;

public class PharmacieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView nom,telecopie;
    private  TextView ville;
    private  TextView telephone;
    private Button bt;
    private RecyclerViewClickListener mListener;


    public PharmacieViewHolder(View itemView,RecyclerViewClickListener listerner){
        super((itemView));
        this.mListener= listerner;

        nom = itemView.findViewById(R.id.activity_main_pharmacie_nom);
        ville = itemView.findViewById(R.id.activity_main_pharmacie_ville);
        telephone = itemView.findViewById(R.id.activity_main_pharmacie_telephone);
        telecopie= itemView.findViewById(R.id.activity_main_pharmacie_telecopie);
        bt=itemView.findViewById(R.id.activity_main_pharmacie_button);

        bt.setOnClickListener(this);

    }
    public  void  bind(Pharmacie Pharma){
        nom.setText(Pharma.getRaisonSociale());
        ville.setText(Pharma.getVille());
        telecopie.setText(Pharma.getTelecopie());
        telephone.setText(Pharma.getTelephone());
    }
    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        mListener.onListItemClick(position);

    }

}
