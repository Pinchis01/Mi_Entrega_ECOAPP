package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menuapp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuapp);
    }
    public void onClick (View view){

        Intent miInten=new Intent(menuapp.this,menrecicla.class);
        startActivity(miInten);
    }
    public void onClick2 (View view){

        Intent miInten=new Intent(menuapp.this,resultados.class);
        startActivity(miInten);
    }

    public void onClick3 (View view){

        Intent miInten=new Intent(menuapp.this,Tips.class);
        startActivity(miInten);
    }
       public void onClick4 (View view){

        Intent miInten=new Intent(menuapp.this,MainActivity.class);
        startActivity(miInten);
    }
}