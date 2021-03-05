package Modele;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.gson.Gson;

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

public class WebService extends AsyncTask<String,Void,JSONArray> {
    private AppCompatActivity myActivity;

    public WebService(AppCompatActivity mainActivity)
    {
        myActivity = mainActivity;
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
    }

    @Override
    protected void onPostExecute(JSONArray s)
    {
        try
        {
            //Pour un simple JSONObject
            //String name = s.getString("name");
            //Toast.makeText(myActivity, "Fin de traitement. Résultat : " + name, Toast.LENGTH_SHORT).show();
            List<Departement> lesDepartements = new ArrayList<Departement>();
            JSONObject jsonStation;
            for (int i = 0; i<s.length(); i++)
            {
                jsonStation = s.getJSONObject(i);
                lesDepartements.add(new Departement(jsonStation.getString("codeDpt"),jsonStation.getString("nomDpt")));
            }
            String exemple = lesDepartements.get(5).getNomDpt();
            Toast.makeText(myActivity, "Fin de traitement. Résultat : " + exemple, Toast.LENGTH_SHORT).show();


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
