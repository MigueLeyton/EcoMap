package com.example.ecomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menuEcoMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_eco_map);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración del btn vidrio
        Button btnVidrio = findViewById(R.id.idVidrio);
        btnVidrio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAVidrioEcoMap();
            }
        });

        // Configuración del btn plastico
        Button btnPlastico = findViewById(R.id.idPlastico);
        btnPlastico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAPlasticoEcoMap();
            }
        });

        // Configuración del btn cartón
        Button btnCarton = findViewById(R.id.idCarton);
        btnCarton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irACartonEcoMap();
            }
        });

        // Configuración del btn orgánica
        Button btnOrganica = findViewById(R.id.idOrganica);
        btnOrganica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAOrganicaEcoMap();
            }
        });

        // Configuración del btn general
        Button btnGeneral = findViewById(R.id.idGeneral);
        btnGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAGeneralEcoMap();
            }
        });

        // Configuración del btn histórica
        Button btnHistorica = findViewById(R.id.idHistorica);
        btnHistorica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAHistoricaEcoMap();
            }
        });
    }

    // Método para redirigir al activity vidrioEcoMap
    private void irAVidrioEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, vidrioEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity plasticoEcoMap
    private void irAPlasticoEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, plasticoEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity cartonEcoMap
    private void irACartonEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, cartonEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity organicaEcoMap
    private void irAOrganicaEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, organicaEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity generalEcoMap
    private void irAGeneralEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, generalEcoMap.class);
        startActivity(intent);
    }

    // Método para redirigir al activity historicaEcoMap
    private void irAHistoricaEcoMap() {
        Intent intent = new Intent(menuEcoMap.this, historicaEcoMap.class);
        startActivity(intent);
    }
}