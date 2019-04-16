package com.example.proyecto_feribios;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Proyeccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyeccion);
        WebView view = (WebView) findViewById(R.id. webview);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.loadUrl("https://www.google.com/maps/d/u/0/embed?mid=1ARDXxfVIdoE_619sSM4HkQ5G8vK4Nmt7");


        view.setWebViewClient(new WebViewClient(){
            public boolean shouldOverriceUrlLoalding(WebViewClient view, String url){
                return false;
            }
        });

    }

}
