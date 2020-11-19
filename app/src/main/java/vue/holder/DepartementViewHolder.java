package vue.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import Modele.Departement;
import vue.fragment.RecyclerViewClickListener;

public class DepartementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView nomText;
    private  TextView codeDep;
    private RecyclerViewClickListener mListener;
    private Button bt;

    public DepartementViewHolder(View itemView, RecyclerViewClickListener listerner){
        super((itemView));
        this.mListener= listerner;

        nomText = itemView.findViewById(R.id.activity_main_Departement_nom);
        codeDep = itemView.findViewById(R.id.activity_main_Departement_code);
        bt = itemView.findViewById(R.id.activity_main_Departement_button);
        //itemView.setOnClickListener(this);
        bt.setOnClickListener(this);
    }

    public  void  bind(Departement Depart){
        nomText.setText(Depart.getNomDpt());
        codeDep.setText(Depart.getCodeDpt());
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        mListener.onListItemClick(position);

    }
}