package vue.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import Modele.Pharmacie;

public class PharmacieViewHolder extends RecyclerView.ViewHolder {
    private TextView nomText;
    private  TextView codeDepartement;
    private  TextView codePostal;

    public PharmacieViewHolder(View itemView){
        super((itemView));
        nomText= itemView.findViewById(R.id.activity_main_Pharmacie_nom);
        codeDepartement=itemView.findViewById(R.id.activity_main_Pharmacie_Departement);
        codePostal=itemView.findViewById(R.id.activity_main_Pharmacie_codePostal);

    }
    public  void  bind(Pharmacie Pharma){
        nomText.setText(Pharma.getVille());
        codePostal.setText(Pharma.getCodePostal());
        codeDepartement.setText(Pharma.getCodeDepartement());
    }


}
