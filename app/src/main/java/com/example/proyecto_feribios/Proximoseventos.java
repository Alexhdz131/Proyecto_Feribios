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
}
