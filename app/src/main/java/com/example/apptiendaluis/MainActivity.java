package com.example.apptiendaluis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button inicio;
    private Button registro;
    private Button invitado;
    private TextView olvidar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia al EditText usando findViewById
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        findViewById(R.id.inicio).setOnClickListener(v -> validarCampos());
        findViewById(R.id.registro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Registro.class);
                startActivity(intent);
            }
        });
        invitado = findViewById(R.id.invitado);
        olvidar = findViewById(R.id.olvidar);

    }
    private void validarCampos() {
        // Obtener texto de los campos
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        // Validar que los campos no estén vacíos y no comiencen con espacio en blanco
        if (!TextUtils.isEmpty(emailText) && !TextUtils.isEmpty(passwordText)) {
            // Validar formato del email
            if (Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                // Los campos son válidos, puedes continuar con el proceso de inicio de sesión
                // Por ejemplo, iniciar sesión con Firebase, enviar la solicitud al servidor, etc.
                Toast.makeText(this, "Campos válidos. ¡Iniciar sesión!", Toast.LENGTH_SHORT).show();
                // Crear un Intent para la actividad "Inicio.java"
                Intent intent = new Intent(MainActivity.this, Inicio.class);

                // Puedes pasar datos adicionales al Intent
                intent.putExtra("usuario", emailText);

                // Indicar si es un usuario registrado o un invitado
                boolean esUsuarioRegistrado = true; // Puedes cambiar esto según la lógica de tu aplicación
                intent.putExtra("esUsuarioRegistrado", esUsuarioRegistrado);


                // Iniciar la actividad "Inicio.java"
                startActivity(intent);
            } else {
                Toast.makeText(this, "Formato de correo electrónico no válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}