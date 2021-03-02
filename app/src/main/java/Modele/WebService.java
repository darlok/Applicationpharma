package Modele;

import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebService {

    Gson gson;

    public WebService()
    {
        Gson gson = new Gson();
    }

    public InputStream sendRequest(URL url) throws Exception
    {
        try
        {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                return urlConnection.getInputStream();
            }
        }
        catch(Exception e)
        {
            Log.e("WebService", "Erreur de connexion");
            throw new Exception("");
        }

        return null;
    }
}
