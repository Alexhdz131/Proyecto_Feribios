package com.example.proyecto_feribios;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String getAllContactsURL = "http://ejemplowebservice.herokuapp.com/api_ubicacion?user_hash=12345&action=get";
    ArrayList<Double> latitudd = new ArrayList<>();
    ArrayList<Double> longitudd = new ArrayList<>();
    ArrayList<String> eventoo = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        webServiceRest(getAllContactsURL);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private void webServiceRest(String requestURL) {
        try {
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.e("abrir conexion",connection.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line= "";
            String webServiceResult="";
            while ((line = bufferedReader.readLine()) != null) {
                webServiceResult += line;
            }
            bufferedReader.close();
            parseInformation(webServiceResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void parseInformation(String jsonResult) {
        JSONArray jsonArray = null;
        String id_ubicacion;
        Double latitud;
        Double longitud;
        String evento;
        try {
            jsonArray = new JSONArray(jsonResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id_ubicacion = jsonObject.getString("id_ubicacion");
                latitud = jsonObject.getDouble("latitud");
                longitud = jsonObject.getDouble("longitud");
                evento = jsonObject.getString("evento");
                latitudd.add(latitud);
                longitudd.add(longitud);
                eventoo.add(evento);




            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        for (int i=0; i < latitudd.size(); i ++) {
            String titulo = eventoo.get(i) + "";
            LatLng ciudad = new LatLng(latitudd.get(i), longitudd.get(i));
            mMap.addMarker(new MarkerOptions().position(ciudad).title(titulo));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ciudad, 7));

        }
    }
}
