package vue.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import Modele.Departement;
import Modele.Pharmacie;

public class DepartementViewHolder extends RecyclerView.ViewHolder {
    private TextView nomText;
    private  TextView codeDep;

    public DepartementViewHolder(View itemView){
        super((itemView));
        nomText = itemView.findViewById(R.id.activity_main_Departement_nom);
        codeDep = itemView.findViewById(R.id.activity_main_Departement_code);
    }

    public  void  bind(Departement Depart){
        nomText.setText(Depart.getNomDpt());
        codeDep.setText(Depart.getCodeDpt());
    }
}