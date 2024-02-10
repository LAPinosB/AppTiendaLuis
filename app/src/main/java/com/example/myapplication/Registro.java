package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.example.myapplication.model.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;

public class Registro extends AppCompatActivity {

    EditText edt_email, edt_pass, edt_barrio, edt_nombre, edt_apellidos, edt_telf;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializaFirebase();
        edt_email = findViewById(R.id.email);
        edt_pass = findViewById(R.id.password);
        edt_barrio = findViewById(R.id.barrio);
        edt_nombre = findViewById(R.id.nombre);
        edt_apellidos = findViewById(R.id.apellidos);
        edt_telf = findViewById(R.id.telefono);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void inicializaFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance("https://my-application-3778e-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference();
    }


    private boolean validarCampos() {
        String email = edt_email.getText().toString().trim();
        String password = edt_pass.getText().toString().trim();
        String barrio = edt_barrio.getText().toString().trim();
        String nombre = edt_nombre.getText().toString().trim();
        String apellidos = edt_apellidos.getText().toString().trim();
        String telefono = edt_telf.getText().toString().trim();

        // Verificar si alguno de los campos está vacío
        boolean camposVacios = false;
        if (email.isEmpty()) {
            edt_email.setError("Campo requerido");
            camposVacios = true;
        }
        if (password.isEmpty()) {
            edt_pass.setError("Campo requerido");
            camposVacios = true;
        }
        if (barrio.isEmpty()) {
            edt_barrio.setError("Campo requerido");
            camposVacios = true;
        }
        if (nombre.isEmpty()) {
            edt_nombre.setError("Campo requerido");
            camposVacios = true;
        }
        if (apellidos.isEmpty()) {
            edt_apellidos.setError("Campo requerido");
            camposVacios = true;
        }
        if (telefono.isEmpty()) {
            edt_telf.setError("Campo requerido");
            camposVacios = true;
        }

        // Si hay campos vacíos, mostrar un mensaje y devolver false
        if (camposVacios) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validar el formato del correo electrónico
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_email.setError("Correo electrónico no válido");
            return false;
        }

        // Puedes agregar más validaciones según tus necesidades

        // Si todas las validaciones pasan, devuelve true
        return true;
    }

    private void limpiarCampos() {
        // Limpiar el texto en todos los campos
        edt_email.setText("");
        edt_pass.setText("");
        edt_barrio.setText("");
        edt_nombre.setText("");
        edt_apellidos.setText("");
        edt_telf.setText("");

        // También puedes eliminar los errores de los campos, si es necesario
        edt_email.setError(null);
        edt_pass.setError(null);
        edt_barrio.setError(null);
        edt_nombre.setError(null);
        edt_apellidos.setError(null);
        edt_telf.setError(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String email = edt_email.getText().toString().trim();
        String password = edt_pass.getText().toString().trim();
        String barrio = edt_barrio.getText().toString().trim();
        String nombre = edt_nombre.getText().toString().trim();
        String apellidos = edt_apellidos.getText().toString().trim();
        String telefono = edt_telf.getText().toString().trim();

        if (item.getItemId() == R.id.icon_add) {
            if (validarCampos()) {
                Persona persona = new Persona();
                persona.setUid(UUID.randomUUID().toString());
                persona.setCorreo(email);
                persona.setPassword(password);
                persona.setBarrio(barrio);
                persona.setNombre(nombre);
                persona.setApellidos(apellidos);
                persona.setTelefono(Integer.parseInt(telefono));
                databaseReference.child("Persona").child(persona.getUid()).setValue(persona);
                Toast.makeText(this, "Añadido", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            }
        } else if (item.getItemId() == R.id.icon_save) {
            Toast.makeText(this, "Actualizar", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.icon_delete) {
            Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);

    }
}