package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.example.myapplication.Estadistica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class menrecicla extends AppCompatActivity {

    /* anunciar variables */

    private Button enviar;
    private EditText cantidadeditext;
    private EditText precioeditext;
    private double sumapesoplas;
    private double sumaprecioplas;
    private double sumapesopayca;
    private double sumapreciopayca;




    /* Array con los item de la lista despegable*/
    String[] items =  {"Plastico","Papel y Carton"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menrecicla);




        /* configuración de la lista despegable*/

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this,R.layout.lista_item,items);
        autoCompleteTxt.setAdapter(adapterItems);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });


       /* Obtener referencias */

        cantidadeditext = findViewById(R.id.cantidadeditex);
        precioeditext = findViewById(R.id.precioeditex);
        enviar = (Button) findViewById(R.id.enviar);

         cantidadeditext.setOnClickListener(new View.OnClickListener()

        {
            public void onClick (View view){
                cantidadeditext.setText("");
            }
        });

        precioeditext.setOnClickListener(new View.OnClickListener()

        {
            public void onClick (View view){
                precioeditext.setText("");
            }
        });

        /* configuración de mensaje de alerta, cuadro de dialogo */

    enviar.setOnClickListener(new View.OnClickListener(){

        public void onClick (View view){

            AlertDialog.Builder alerta = new AlertDialog.Builder(menrecicla.this);
            alerta.setMessage("¿Quieres seguir reciclando?")
                    .setCancelable(true)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int i) {

                            cantidadeditext.setText("Cantidad en Kg");
                            precioeditext.setText("Valor por Kg");
                            dialog.cancel();
                        }
                    })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            finish();
                        }
                    });

            /* Obtener datos despues de diligenciados*/

            String item = autoCompleteTxt.getText().toString();
            double kg = Double.parseDouble(cantidadeditext.getText().toString());
            double valor = Double.parseDouble(precioeditext.getText().toString());

            /* Suma de cada vez que se recicla al momento de dar en "Si" al cuadro de dialogo */

            if (item.equals("Plastico")){

                // Crear un nuevo objeto Estadistica de Plastico

                Estadistica itemplasticonew = new Estadistica(kg, valor);

                // Guardar los datos en el archivo
                RegistroSaveplastico(itemplasticonew);

                valor = valor * kg;
                sumaprecioplas = sumaprecioplas + valor;
                sumapesoplas = sumapesoplas + kg;

                /* Mensaje "Titulo" del cuadro de dialogo */

                AlertDialog totalreciclado = alerta.create();
                totalreciclado.setTitle("Felicitaciones ganaste $" + sumaprecioplas + " por reciclar " + sumapesoplas + " Kg" + " de "+ item);
                totalreciclado.show();

            } else if (item.equals("Papel y Carton")) {




                valor = valor * kg;
                sumapreciopayca = sumapreciopayca + valor;
                sumapesopayca = sumapesopayca + kg;

                /* Mensaje "Titulo" del cuadro de dialogo */

                AlertDialog totalreciclado = alerta.create();
                totalreciclado.setTitle("Felicitaciones ganaste $" + sumapreciopayca + " por reciclar " + sumapesopayca + " Kg" + " de "+ item);
                totalreciclado.show();

                // Crear un nuevo objeto Estadistica de papel y carton
                Estadistica itempaycart = new Estadistica(kg, valor);

                // Guardar los datos en el archivo
                RegistroSavepapelycarton(itempaycart);
            }




        }

    });

    }

    private void RegistroSaveplastico (Estadistica ItemNew) {
        File file = new File(getFilesDir(), "resultadoplastico.txt");

        try {
            FileWriter writer = new FileWriter(file, true);

            // El segundo parámetro "true" indica que se agregará al final del archivo existente
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String nuevoRegistro = ItemNew.getkg() + "," + ItemNew.getvalor() + "\n";
            bufferedWriter.write(nuevoRegistro);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void RegistroSavepapelycarton (Estadistica ItemNew) {
        File file = new File(getFilesDir(), "resultadopapelycarton.txt");

        try {
            FileWriter writer = new FileWriter(file, true);

            // El segundo parámetro "true" indica que se agregará al final del archivo existente
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String nuevoRegistro = ItemNew.getkg() + "," + ItemNew.getvalor() + "\n";
            bufferedWriter.write(nuevoRegistro);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick2 (View view){

        Intent miInten=new Intent(menrecicla.this,menuapp.class);
        startActivity(miInten);

    }



}