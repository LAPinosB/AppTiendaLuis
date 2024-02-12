package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Inicio extends AppCompatActivity {
    ListView lst_personas;
    TextView textView;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        lst_personas = findViewById(R.id.lista_personas);
        textView = findViewById(R.id.textView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if(user==null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            textView.setText(user.getEmail());
        }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem addItem = menu.findItem(R.id.icon_add);
        MenuItem saveItem = menu.findItem(R.id.icon_save);
        MenuItem deleteItem = menu.findItem(R.id.icon_delete);
        addItem.setVisible(false);
        saveItem.setVisible(false);
        deleteItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.icon_close){
            // If sign in fails, display a message to the user.
            Toast.makeText(Inicio.this, "Authentication closed Session.",
                    Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}