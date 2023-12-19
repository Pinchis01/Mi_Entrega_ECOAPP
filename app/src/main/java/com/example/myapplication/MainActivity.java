package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Usuarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia al elemento de interfaz de usuario para registrarse
        TextView registrarse = findViewById(R.id.registro);

        // Obtener referencia al botón de ingreso
        Button botonIngresar = findViewById(R.id.ingreso);

        // Obtener referencias a los campos de texto para usuario y contraseña
        EditText usuarioEditText = findViewById(R.id.casillauser);
        EditText passwordEditText = findViewById(R.id.casillacontras);

        // Crear intents para las actividades de registro de usuario y principal
        Intent registroIntent = new Intent(this, registro.class);
        Intent menuappIntent = new Intent(this, menuapp.class);

        // Leer los datos del archivo
        File file = new File(getFilesDir(), "MyminibaseD.txt");
        ArrayList<Usuarios> usuarios = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // Dividir la línea en los datos del usuario separados por comas
                String[] userData = line.split(",");
                String NombresCompletos = userData[0];
                String Correos = userData[1];
                String Apodos = userData[2];
                String password = userData[3];
                // Crear un objeto Usuario y añadirlo a la lista de usuarios
                Usuarios Usuarionew = new Usuarios(NombresCompletos, Correos, Apodos, password);
                usuarios.add(Usuarionew);
            }
            reader.close();

            // Imprimir información de los usuarios leídos en el archivo
            for (Usuarios usuario : usuarios) {
                Log.d("Usuarios",
                        "Nombre: " + usuario.getNombrecomple() + ", Correo: " + usuario.getCorreo() +
                                ", Nickname: " + usuario.getApodo() + ", Password: " + usuario.getContraseña());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configurar el evento de clic para el botón de ingreso
        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar si se han ingresado datos de usuario y contraseña

                if (!usuarioEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty()) {
                    String inputUsuario = usuarioEditText.getText().toString();
                    String inputPassword = passwordEditText.getText().toString();

                    // Buscar el usuario coincidente en la lista de usuarios
                    for (Usuarios usuario : usuarios) {
                        if (usuario.getContraseña().equals(inputPassword)) {
                            if (usuario.getApodo().equals(inputUsuario) || usuario.getCorreo().equals(inputUsuario) || usuario.getNombrecomple().equals(inputUsuario)) {
                                // Si se encuentra una coincidencia, iniciar la actividad principal y salir del bucle
                                startActivity(menuappIntent);
                                return;
                            }
                        }
                    }
                    // Si no se encontró una coincidencia, mostrar un mensaje de error utilizando Toast
                    Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Los campos no pueden estar vacíos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar el evento de clic para el enlace de registro
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad de registro de usuario
                startActivity(registroIntent);
            }
        });
    }

}