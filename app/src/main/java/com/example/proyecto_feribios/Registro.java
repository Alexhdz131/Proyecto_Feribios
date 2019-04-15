package com.example.proyecto_feribios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void Registrarse(View view) {
        Intent Registro = new Intent(this, Menu.class);
        startActivity(Registro);
    }
}
