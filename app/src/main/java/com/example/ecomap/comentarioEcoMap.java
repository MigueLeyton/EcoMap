package com.example.ecomap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class comentarioEcoMap extends AppCompatActivity {

    private EditText editTextComentario;
    private Button buttonGuardar, buttonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comentario_eco_map);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarComentarios);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Comentarios");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa los elementos de la interfaz
        editTextComentario = findViewById(R.id.editTextComentario);
        buttonGuardar = findViewById(R.id.idGuardarComentario);
        buttonCancelar = findViewById(R.id.idCancelarComentario);

        // Configura el botón para guardar el comentario
        buttonGuardar.setOnClickListener(v -> guardarComentario());

        // Configura el botón para cancelar
        buttonCancelar.setOnClickListener(v -> irAMenuEcoMap());
    }

    private void guardarComentario() {
        String comentarioTexto = editTextComentario.getText().toString();
        if (!comentarioTexto.isEmpty()) {
            // Guardar el comentario en SharedPreferences
            SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
            prefs.edit().putString("Comentario", comentarioTexto).apply();

            // Mensaje de confirmación
            Toast.makeText(this, "Comentario guardado: " + comentarioTexto, Toast.LENGTH_SHORT).show();

            // Retornar el comentario a la actividad anterior
            Intent intent = new Intent();
            intent.putExtra("nuevo_comentario", comentarioTexto);
            setResult(RESULT_OK, intent);
            finish(); // Cierra esta actividad
        } else {
            Toast.makeText(this, "Por favor, ingresa un comentario.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                irAMenuEcoMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void irAMenuEcoMap() {
        Intent intent = new Intent(comentarioEcoMap.this, menuEcoMap.class);
        startActivity(intent);
        finish();
    }
}