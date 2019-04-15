package com.example.proyecto_feribios;

import android.content.Intent;
import android.view.View;

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


public class Proximoseventos extends AppCompatActivity {

    private ListView lis_even;
    private ArrayAdapter adapter;
    private String getAllContactsURL = "https://feribioswebservice.herokuapp.com/api_clientes?user_hash=12345&action=get";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximoseventos);
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
        String id_evento;
        String titulo;
        String descripcion;
        String fecha;
        String hora;
        String ubicacion;

        try{
            jsonArray = new JSONArray(jsonResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id_evento = jsonObject.getString("id_evento");
                titulo = jsonObject.getString("titulo");
                descripcion = jsonObject.getString("descripcion");
                fecha = jsonObject.getString("fecha");
                hora = jsonObject.getString("hora");
                ubicacion = jsonObject.getString("ubicacion");
                adapter.add("Titulo:" + titulo + "\n" + "descripcion:" + descripcion + "\n" + "fecha:" + fecha + "\n" + "hora:" + hora + "\n" + "ubicacion:" + ubicacion);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void Menu(View view) {
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }
    /*
    public void Tallerdelectura(View view) {
        Intent tallerdelectura = new Intent(this, Tallerdelectura.class);
        startActivity(tallerdelectura);
    }

    public void Proyeccion(View view) {
        Intent proyeccion = new Intent(this, Proyeccion.class);
        startActivity(proyeccion);
    }
    public void Gastronomiaanaya(View view) {
        Intent gastronimiaanaya = new Intent(this, Gastronomiaanaya.class);
        startActivity(gastronimiaanaya);
    }
    public void Cursodeajedrez(View view) {
        Intent cursodeajedrez= new Intent(this, Cursodeajedrez.class);
        startActivity(cursodeajedrez);
    }
    public void Ficirco(View view) {
        Intent ficirco= new Intent(this, Ficirco.class);
        startActivity(ficirco);
    }

    public void Tallerubuntu(View view) {
        Intent tallerubuntu= new Intent(this, Tallerubuntu.class);
        startActivity(tallerubuntu);
    }*/


}
