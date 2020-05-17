package com.example.projecterecum8.Model;

import android.content.Context;

public class Contacto {
    private String nombre, apellido, email, urlfotos;

    public Contacto(String nombre, String apellido, String email, String urlfotos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.urlfotos = urlfotos;
    }
    public Contacto(){

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getUrlfotos() {
        return urlfotos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUrlfotos(String urlfotos) {
        this.urlfotos = urlfotos;
    }
}
