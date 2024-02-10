package com.example.apptiendaluis.model;

public class Persona {

    public String uid;
    public String nombre;
    public String apellidos;
    public int telefono;
    public String barrio;
    public String correo;
    public String password;


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
