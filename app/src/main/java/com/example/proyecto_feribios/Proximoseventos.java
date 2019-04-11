package com.example.proyecto_feribios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Proximoseventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximoseventos);
    }
    public void Menu(View view) {
        Intent menu = new Intent(this, Menu.class);
        startActivity(menu);
    }

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
    }


}
