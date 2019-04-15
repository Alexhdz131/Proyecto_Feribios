package com.example.proyecto_feribios;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tallerubuntu extends AppCompatActivity {
    private ListView lis_even;
    private ArrayAdapter adapter;
    private String getAllContactsURL = "https://ejemplowebservice.herokuapp.com/api_ofertas?user_hash=12345&action=get";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tallerubuntu);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        lis_even = (ListView) findViewById(R.id.listview_even);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        lis_even.setAdapter(adapter);
        webSerViceRest(getAllContactsURL);
    }

    private void webSerViceRest(String requestURL) {
        try{
            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.e("abrir conexion",connection.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line= "";
            String webServiceResult="";
            while ((line = bufferedReader.readLine()) !=null) {
                webServiceResult += line;
            }
            bufferedReader.close();
            parseInformation(webServiceResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseInformation(String jsonResult) {
        JSONArray jsonArray = null;
        String id_oferta;
        String descripcion;
        String puesto;
        String evento;
        String fecha;
        String hora;


        try{
            jsonArray = new JSONArray(jsonResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id_oferta = jsonObject.getString("id_oferta");
                descripcion = jsonObject.getString("descripcion");
                puesto = jsonObject.getString("puesto");
                evento = jsonObject.getString("evento");
                fecha = jsonObject.getString("fecha");
                hora = jsonObject.getString("hora");
                adapter.add("Descripcion:" + descripcion + "\n" + "Numero puesto:" + puesto + "\n" + "evento:" + evento +  "\n" + "fecha:" + fecha + "\n" + "hora:" + hora);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
