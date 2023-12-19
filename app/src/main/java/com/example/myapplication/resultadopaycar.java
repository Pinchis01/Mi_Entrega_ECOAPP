package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class resultadopaycar extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView promediokg = findViewById(R.id.prompes);
        TextView promediovalor = findViewById(R.id.proganpes);

        setContentView(R.layout.activity_resultadopaycar);
        Button botonIngresar = findViewById(R.id.bottips);


        botonIngresar.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){



                // Leer los datos del archivo
                File file = new File(getFilesDir(), "resultadopapelycarton.txt");
                ArrayList<Estadistica> ItemNew = new ArrayList<>();


                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Dividir la línea en los datos del usuario separados por comas
                        String[] userData = line.split(",");
                        Double kg = Double.parseDouble(userData[0]);
                        Double valor = Double.parseDouble(userData[1]);

                        // Crear un objeto Usuario y añadirlo a la lista de usuarios
                        Estadistica ItemNewpaycart = new Estadistica(kg, valor);
                        ItemNew.add(ItemNewpaycart);


                    }
                    reader.close();

                    // Imprimir información de los usuarios leídos en el archivo
                    for (Estadistica Items : ItemNew) {
                        Log.d("Items",
                                "Cantidad: " + Items.getkg() + ", Valor: " + Items.getvalor());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }






            }
        });






    }


    /**************************************************************************************************/



    private Double Calcularpromediokg (List<Estadistica>kgList) {

        Double sumar = (double) 0;
        Double promedio;



        for (Estadistica item : kgList){
            sumar += item.getkg();
        }
        promedio = sumar / kgList.size();

        return promedio;
    }





    public void onClick (View view){

        Intent miInten=new Intent(resultadopaycar.this,resultadoplastic.class);
        startActivity(miInten);



    }

    public void onClick3 (View view){

        Intent miInten=new Intent(resultadopaycar.this,resultados.class);
        startActivity(miInten);

    }
}