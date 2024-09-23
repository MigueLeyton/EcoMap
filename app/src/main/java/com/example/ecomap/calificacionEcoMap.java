package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class calificacionEcoMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calificacion_eco_map);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarCalificacion);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Habilita el botón de retroceso
        getSupportActionBar().setTitle("Calificación");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración de los botones
        Button btnVidrio = findViewById(R.id.idSalirCalificacion);
        btnVidrio.setOnClickListener(v -> irAMenuEcoMap());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(calificacionEcoMap.this, menuEcoMap.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpia la pila de actividades
                startActivity(intent);
                finish(); // Cierra la actividad actual
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Métodos para redirigir a las actividades correspondientes
    private void irAMenuEcoMap() {
        Intent intent = new Intent(calificacionEcoMap.this, menuEcoMap.class);
        startActivity(intent);
    }
}