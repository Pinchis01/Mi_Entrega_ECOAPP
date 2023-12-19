package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class resultadoplastic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultadoplastic);
    }

    public void onClick (View view){

        Intent miInten=new Intent(resultadoplastic.this,resultadopaycar.class);
        startActivity(miInten);

    }
    public void onClick2 (View view){

        Intent miInten=new Intent(resultadoplastic.this,Tips.class);
        startActivity(miInten);

    }
    public void onClick3 (View view){

        Intent miInten=new Intent(resultadoplastic.this,resultados.class);
        startActivity(miInten);

    }
}