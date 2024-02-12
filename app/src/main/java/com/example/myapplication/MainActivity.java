package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button inicio;
    private Button registro;
    private Button invitado;
    private TextView olvidar;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), Inicio.class);
            /* Puedes pasar datos adicionales al Intent
            intent.putExtra("usuario", email);
            // Indicar si es un usuario registrado o un invitado
            boolean esUsuarioRegistrado = true; // Puedes cambiar esto según la lógica de tu aplicación
            intent.putExtra("esUsuarioRegistrado", esUsuarioRegistrado);*/
            // Iniciar la actividad "Inicio.java"
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia al EditText usando findViewById
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
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
                Toast.makeText(this, "Campos válidos. ¡Comienza comprobacion de inicio de sesion!", Toast.LENGTH_SHORT).show();

                mAuth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(MainActivity.this, "Authentication correctly.",Toast.LENGTH_SHORT).show();
                                    // Crear un Intent para la actividad "Inicio.java"
                                    Intent intent = new Intent(getApplicationContext(), Inicio.class);
                                    // Puedes pasar datos adicionales al Intent
                                    intent.putExtra("usuario", emailText);
                                    // Indicar si es un usuario registrado o un invitado
                                    boolean esUsuarioRegistrado = true; // Puedes cambiar esto según la lógica de tu aplicación
                                    intent.putExtra("esUsuarioRegistrado", esUsuarioRegistrado);
                                    // Iniciar la actividad "Inicio.java"
                                    startActivity(intent);
                                    finish();
                                    //FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(this, "Formato de correo electrónico no válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}