package com.example.proyecto_feribios;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton =(Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = ((EditText)findViewById(R.id.editText)).getText().toString();
                String password = ((EditText)findViewById(R.id.editText3)).getText().toString();
                if (usuario.equals("admin")&& password.equals("admin"))
                {
                    Intent nuevoform = new Intent(MainActivity.this, Menu
                            .class);
                    startActivity(nuevoform);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Usuario Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn=(ImageButton) findViewById(R.id.imageButton);
        btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Uri uriUrl = Uri.parse("https://www.facebook.com/CraftSystem-387231308531950/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(intent);
    }
}
