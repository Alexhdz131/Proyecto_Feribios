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


public class Organizadores extends AppCompatActivity {
    private ListView lis_even;
    private ArrayAdapter adapter;
    private String getAllContactsURL = "https://ejemplowebservice.herokuapp.com/api_organizadores?user_hash=12345&action=get";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizadores);
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
        String id_organizador;
        String nombre;
        String apellido_paterno;
        String apellido_materno;
        String empresa;

        try{
            jsonArray = new JSONArray(jsonResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id_organizador = jsonObject.getString("id_organizador");
                nombre = jsonObject.getString("nombre");
                apellido_paterno = jsonObject.getString("apellido_paterno");
                apellido_materno = jsonObject.getString("apellido_materno");
                empresa = jsonObject.getString("empresa");
                adapter.add("Nombre:" + nombre + apellido_paterno + apellido_materno + "\n" + "Empresa:" + empresa);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void Menu(View view) {
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }
}
