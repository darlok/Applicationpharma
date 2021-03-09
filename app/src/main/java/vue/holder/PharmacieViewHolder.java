package vue.holder;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.VisibleForTesting;
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

        


    }
    public  void  bind(Pharmacie Pharma){
        String addresse= Pharma.getNumVoie()+" "+Pharma.getTypeVoie()+" "+Pharma.getVoie()+" "+Pharma.getLieuDitBp()+" "+Pharma.getCodePostal()+" "+Pharma.getVille() ;
        nom.setText(Pharma.getRaisonSociale());
        ville.setText(addresse);
        String telecopies = Pharma.getTelecopie();
       if (telecopies.length() == 10 )
        telecopie.setText("null");
       else{
           telecopie.setText("null");
       }
        telephone.setText(Pharma.getTelephone());

    }
    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        mListener.onListItemClick(position);

    }

}
