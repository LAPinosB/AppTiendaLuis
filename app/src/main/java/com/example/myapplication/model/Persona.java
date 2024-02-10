package com.example.myapplication.model;

public class Persona {

    private String uid;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String barrio;
    private String correo;
    private String password;


    public Persona() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellido) {
        apellidos = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "uid='" + uid + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Apellido='" + apellidos + '\'' +
                ", Correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

