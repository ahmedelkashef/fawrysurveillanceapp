package com.example.cloudypedia.fawrysurveillanceapp.DataFetcher;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.cloudypedia.fawrysurveillanceapp.Activites.MapsActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dev3 on 11/24/2016.
 */

public class FetchLocationTask extends AsyncTask<Void, Void, LatLng[]> {

    private ProgressDialog progressDialog;
    String serviceType;
    Context context;
    public FetchLocationTask(Context context ,ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
        Log.e("Fawry", "in connection");
    }
    private final String LOG_TAG = FetchLocationTask.class.getSimpleName();

    private LatLng[] getLocationDataFromJson(String LocationJsonStr)
            throws JSONException {

        String Long = "longitude" ;
        String Lat = "latitude" ;


        JSONArray LocationArray = new JSONArray(LocationJsonStr);
        LatLng[] latLngs = new LatLng[ LocationArray.length()];

        for (int  i = 0 ;  i < LocationArray.length(); i++ )
        {
            JSONObject SingleJson = LocationArray.getJSONObject(i);
            LatLng latlng = new LatLng(SingleJson.getDouble(Long),SingleJson.getDouble(Lat) );
//            LatLng latlng = new LatLng(SingleJson.getDouble(Lat),SingleJson.getDouble(Long) );
            latLngs[i] = latlng ;
        }
        return latLngs;
    }

    @Override
    protected LatLng[] doInBackground(Void... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        // Will contain the raw JSON response as a string.
        String MoviesJsonStr = null;

        //Press HERE API_KEY
        //  String API_KEY = "674437857f8bd7add004a68e85f81896";
        try {

            final String BASE_URL = "https://fawry-test-150508.appspot.com/";
            //   final String KEY_PARAM = "api_key";

            Uri BuiltUri = Uri.parse(BASE_URL).buildUpon()
                    .build();

            URL url = new URL(BuiltUri.toString());
            Log.v("uri=",BuiltUri.toString());
            // Create the request to theMovieDb, and open the connectio
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }

            MoviesJsonStr = buffer.toString();
            Log.v(LOG_TAG, "JSON String = " + MoviesJsonStr);
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        try {
            return getLocationDataFromJson(MoviesJsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(LatLng[] Locations) {

        ArrayList<MarkerOptions> markers = new ArrayList<MarkerOptions>();
        Intent MapIntent = new Intent(context, MapsActivity.class);
        if (Locations == null)
        {
            Toast.makeText(context , "Error in Fetching Data",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
        else
        {

            for (int  i = 0 ;  i < Locations.length; i++ )
            {
                markers.add(new MarkerOptions().position(Locations[i]).title("" + Locations[i].latitude + Locations[i].longitude));
            }

            MapIntent.putExtra("markers", markers);
            context.startActivity(MapIntent);
            progressDialog.dismiss();
        }



    }

}