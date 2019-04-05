package com.example.proyecto_feribios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void Galeria(View view) {
        Intent galeria = new Intent(this, Galeria.class);
        startActivity(galeria);

    }

    public void ProximosEventos(View view) {
        Intent eventos = new Intent(this, Proximoseventos.class);
        startActivity(eventos);
    }
}
