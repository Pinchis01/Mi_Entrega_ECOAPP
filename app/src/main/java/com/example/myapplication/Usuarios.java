package com.example.myapplication;

public class Usuarios {


    private String Nombrecomple;
    private String Apodo;
    private String Correo;
    private String Contraseña;

    public Usuarios(String Nombrecomple, String Apodo, String Correo, String Contraseña){


        this.Nombrecomple = Nombrecomple;
        this.Apodo = Apodo;
        this.Correo = Correo;
        this.Contraseña = Contraseña;

    }


    public void setNombrecomple(String Nombrecomple){

        this.Nombrecomple = Nombrecomple;
    }

    public void setApodo(String Apodo){

        this.Apodo = Apodo;
    }

    public void setCorreo(String Correo){
        this.Correo = Correo;
    }

    public void setContraseña(String Contraseña) {

        this.Contraseña = Contraseña;
    }



        public String getNombrecomple(){
            return Nombrecomple;
        }

        public String getApodo(){
            return Apodo;
        }

        public String getCorreo(){
            return Correo;
        }

        public String getContraseña(){
            return Contraseña;

    }

}
