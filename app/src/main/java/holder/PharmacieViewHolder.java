package holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import Modele.Pharmacie;

public class PharmacieViewHolder extends RecyclerView.ViewHolder {
    private TextView nomText;

    public PharmacieViewHolder(View itemView){
        super((itemView));
        this.nomText= itemView.findViewById(R.id.activity_main_Pharmacie_nom);
    }
    public  void  bind(Pharmacie Pharma){this.nomText.setText(Pharma.getRaisonSociale());}
}
