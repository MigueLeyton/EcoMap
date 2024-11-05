package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menuEcoMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_eco_map);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Habilita el botón de retroceso
        getSupportActionBar().setTitle("Menú");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración de los botones
        Button btnVidrio = findViewById(R.id.idVidrio);
        btnVidrio.setOnClickListener(v -> irAVidrioEcoMap());

        Button btnPlastico = findViewById(R.id.idPlastico);
        btnPlastico.setOnClickListener(v -> irAPlasticoEcoMap());

        Button btnCarton = findViewById(R.id.idCarton);
        btnCarton.setOnClickListener(v -> irACartonEcoMap());

        Button btnOrganica = findViewById(R.id.idOrganica);
        btnOrganica.setOnClickListener(v -> irAOrganicaEcoMap());

        Button btnGeneral = findViewById(R.id.idGeneral);
        btnGeneral.setOnClickListener(v -> irAGeneralEcoMap());

        Button btnHistorica = findViewById(R.id.idHistorica);
        btnHistorica.setOnClickListener(v -> irAHistoricaEcoMap());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(menuEcoMap.this, loginMenuEcoMap.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Limpia la pila de actividades
                startActivity(intent);
                finish(); // Cierra la actividad actual
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Métodos para redirigir a las actividades correspondientes
    private void irAVidrioEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, vidrioEcoMap.class);
        startActivity(intent);
    }

    private void irAPlasticoEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, plasticoEcoMap.class);
        startActivity(intent);
    }

    private void irACartonEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, cartonEcoMap.class);
        startActivity(intent);
    }

    private void irAOrganicaEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, organicaEcoMap.class);
        startActivity(intent);
    }

    private void irAGeneralEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, generalEcoMap.class);
        startActivity(intent);
    }

    private void irAHistoricaEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, historicaEcoMap.class);
        startActivity(intent);
    }
}