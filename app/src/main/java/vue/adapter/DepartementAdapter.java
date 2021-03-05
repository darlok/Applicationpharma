package vue.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.R;

import java.util.List;

import Modele.Departement;
import vue.fragment.RecyclerViewClickListener;
import vue.holder.DepartementViewHolder;

public class DepartementAdapter extends RecyclerView.Adapter<DepartementViewHolder> implements InterfaceDepartement {
    private List<Departement>lesDepartements;
    private List<String> lesDeparts;
    private RecyclerViewClickListener mListener;
    private TextView text;

    public DepartementAdapter(List<Departement> lesDepartements, RecyclerViewClickListener listener){
        this.lesDepartements = lesDepartements;
        this.mListener=listener;
    }

    public DepartementAdapter(Context context, Cursor cursor, List<String> lesDeparts){
        this.lesDeparts = lesDeparts;
    }

    public DepartementViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_main_departement, viewGroup, false);
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

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        text.setText((CharSequence) lesDepartements.get(cursor.getPosition()));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_main_departement, parent, false);

        text = (TextView) view.findViewById(R.id.text);

        return view;
    }
}
