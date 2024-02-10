package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class Inicio extends AppCompatActivity {
    ListView lst_personas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        lst_personas = findViewById(R.id.lista_personas);

        // Recuperar el valor del usuario pasado como extra
        String usuario = getIntent().getStringExtra("usuario");

        // Recuperar la información sobre si es un usuario registrado
        boolean esUsuarioRegistrado = getIntent().getBooleanExtra("esUsuarioRegistrado", false);

        if (esUsuarioRegistrado) {
            // Lógica para un usuario registrado

            // Puedes usar el valor de "usuario"
        } else {
            // Lógica para un invitado
            // Puedes manejar la lógica específica para invitados aquí
        }
    }
}