package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.myapplication.Usuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class registro extends AppCompatActivity {

    // Obtener referencias a los elementos de la interfaz




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        final Intent intent1 = new Intent(this, MainActivity.class);
        EditText Nombreedi = findViewById(R.id.casillaname);
        EditText Apodoedi = findViewById(R.id.casillapodo);
        EditText Correoedi = findViewById(R.id.casillacorreo);
        EditText Contraseñaedi = findViewById(R.id.casillacontraregis);
        EditText Repicontraseña = findViewById(R.id.casiilarepitecontr);
        Button botonRegistro = findViewById(R.id.ingresoapp);

        /**
         * Botón para registrar usuarios
         */
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override


    public void onClick(View view) {



        // Revisar si los campos están diligenciados

        if (!Nombreedi.getText().toString().isEmpty() && !Correoedi.getText().toString().isEmpty() &&
                !Apodoedi.getText().toString().isEmpty() && !Contraseñaedi.getText().toString().isEmpty() &&
                !Repicontraseña.getText().toString().isEmpty()) {

            // Revisar si las contraseñas coinciden
            if (Contraseñaedi.getText().toString().equals(Repicontraseña.getText().toString())) {

            // Validar si los datos ya existen en el archivo
            if (UserExiste(Nombreedi.getText().toString(), Apodoedi.getText().toString(), Correoedi.getText().toString())) {

                // Los datos ya existen
                Toast.makeText(getApplicationContext(), "El correo, usuario o nickname ya existen", Toast.LENGTH_SHORT).show();
            } else {

                // Los datos no existen, realizar el registro
                // Crear un nuevo objeto Usuario
                Usuarios Usuarionew = new Usuarios(Nombreedi.getText().toString(),
                        Correoedi.getText().toString(), Apodoedi.getText().toString(),
                        Contraseñaedi.getText().toString());

                // Guardar los datos en el archivo
                RegistroSave(Usuarionew);

                // Ir al activity de inicio de sesión
                startActivity(intent1);


            }
        } else {

            // Las contraseñas no coinciden
            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden",
                    Toast.LENGTH_SHORT).show();
        }

    } else {
        // Los campos estan vacios
        Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacíos",
                Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


/******************************************************************************************************/
    /**
     * Verifica si los datos de usuario ya existen en el archivo
     *
     * @param correoss        Correo electrónico del usuario
     * @param Apodoss      Nombre de usuario
     * @param UsuarioNew Nombre completo del usuario
     * @return true si los datos existen, false en caso contrario
     */

    private boolean UserExiste(String correoss, String Apodoss, String UsuarioNew) {
        File file = new File(getFilesDir(), "MyminibaseD.txt");

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            List<String> existingCorreos = new ArrayList<>();
            List<String> existingApodos = new ArrayList<>();
            List<String> existingNombresCompletos = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                existingCorreos.add(data[1]);
                existingApodos.add(data[2]);
                existingNombresCompletos.add(data[0]);
            }

            bufferedReader.close();

            return existingCorreos.contains(correoss) || existingApodos.contains(Apodoss) || existingNombresCompletos.contains(UsuarioNew);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


/******************************************************************************************************/

    /**
     * Guarda el nuevo registro de usuario en el archivo
     *
     * @param UserNew Objeto Usuario con los datos del nuevo usuario
     */
    private void RegistroSave (Usuarios UserNew) {
        File file = new File(getFilesDir(), "MyminibaseD.txt");

        try {
            FileWriter writer = new FileWriter(file, true); // El segundo parámetro "true" indica que se agregará al final del archivo existente
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String nuevoRegistro = UserNew.getNombrecomple() + "," + UserNew.getCorreo() + "," +
                    UserNew.getApodo() + "," + UserNew.getContraseña() + "\n";
            bufferedWriter.write(nuevoRegistro);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//devolverme a la interfaz Login

    public void onClick2 (View view){

        Intent miInten=new Intent(registro.this,MainActivity.class);
        startActivity(miInten);
    }

}