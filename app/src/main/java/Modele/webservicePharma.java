package Modele;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationpharma.ActivityPharmacie;
import com.example.applicationpharma.MainActivity;
import com.example.applicationpharma.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vue.adapter.DepartementAdapter;
import vue.adapter.PharmacieAdapter;
import vue.fragment.RecyclerViewClickListener;

public class webservicePharma extends AsyncTask<String,Void,JSONArray> {
    private AppCompatActivity myActivity;
    private RecyclerView Rv;

    public webservicePharma(AppCompatActivity ActivityPharmacie)
    {
        myActivity = ActivityPharmacie;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        Toast.makeText(myActivity, "Début du traitement asynchrone", Toast.LENGTH_SHORT).show();
    }

    @Override
    //protected JSONObject doInBackground(String... strings)
    protected JSONArray doInBackground(String... strings)
    {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try
        {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); //Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); //Stream

            result = readStream(in); // Read stream
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        JSONArray json = null;
        try
        {
            //json = new JSONObject(result);
            json = new JSONArray(result);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return json; // returns the result*/
        //return  null;

    }

    @Override
    protected void onPostExecute(JSONArray s)
    {
        try
        {
            //Pour un simple JSONObject
            //String name = s.getString("name");
            //Toast.makeText(myActivity, "Fin de traitement. Résultat : " + name, Toast.LENGTH_SHORT).show();
            List<Pharmacie> lesPharmacies = new ArrayList<Pharmacie>();
            //lesPharmacies.add(new Pharmacie("01","cc","9","1","1","1","1","1","1","1","1","1","1"));
            JSONObject jsonStation;
            for (int i = 0; i<s.length(); i++)
            {
                jsonStation = s.getJSONObject(i);
                lesPharmacies.add(new Pharmacie(jsonStation.getString("noFiness"),jsonStation.getString("raisonSociale"),
                        jsonStation.getString("numVoie"),jsonStation.getString("typeVoie"),jsonStation.getString("voie"),
                        jsonStation.getString("lieuDitBp"),jsonStation.getString("codeDepartement"),jsonStation.getString("codePostal"),
                        jsonStation.getString("ville"),jsonStation.getString("telephone"),jsonStation.getString("telecopie"),String.valueOf(jsonStation.getInt("numCategorie")),
                        jsonStation.getString("siret")));
            }




            ((ActivityPharmacie)myActivity).setLesPharmacies(lesPharmacies);
            PharmacieAdapter ph = new PharmacieAdapter(lesPharmacies, (RecyclerViewClickListener) myActivity);
            Rv = (RecyclerView) myActivity.findViewById(R.id.activity_main_Pharmacie_recyclerview);
            Rv.setLayoutManager(new LinearLayoutManager(myActivity));
            Rv.setHasFixedSize(true);
            Rv.setAdapter(ph);



        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine())
        {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}
