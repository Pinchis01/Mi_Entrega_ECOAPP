package com.example.myapplication;

public class Estadistica {
    private Double kg;
    private Double valor;

    public Estadistica(Double kg, Double valor) {


        this.kg = kg;
        this.valor = valor;
    }

    public void setkg(Double kg){

        this.kg = kg;
    }

    public Double getkg() {
        return kg;

    }

    public void setValor(Double valor){

        this.valor = valor;
    }

    public Double getvalor() {
        return valor;

    }

}
