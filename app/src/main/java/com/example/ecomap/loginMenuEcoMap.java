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

public class loginMenuEcoMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_menu_eco_map);

        // Configura la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarLoginMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bienvenido");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuración de los botones
        Button btnIrTiposMenu = findViewById(R.id.btnIrTiposMenu);
        btnIrTiposMenu.setOnClickListener(v -> irAMenuEcoMap());

        Button btnVerDatosUsuario = findViewById(R.id.btnVerDatosUsuario);
        btnVerDatosUsuario.setOnClickListener(v -> irDatosUsuarioEcoMap());

        Button btnVerInformacion = findViewById(R.id.btnVerInformacion);
        btnVerInformacion.setOnClickListener(v -> irInformacionEcoMap());

        Button btnSalirUsuarioMenu = findViewById(R.id.btnSalirUsuarioMenu);
        btnSalirUsuarioMenu.setOnClickListener(v -> irALoginEcoMap());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(loginMenuEcoMap.this, loginEcoMap.class);
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
        Intent intent = new Intent(loginMenuEcoMap.this, menuEcoMap.class);
        startActivity(intent);
    }

    private void irDatosUsuarioEcoMap() {
        Intent intent = new Intent(loginMenuEcoMap.this, datosUsuarioEcoMap.class);
        startActivity(intent);
    }

    private void irInformacionEcoMap() {
        Intent intent = new Intent(loginMenuEcoMap.this, informacionEcoMap.class);
        startActivity(intent);
    }

    private void irALoginEcoMap() {
        Intent intent = new Intent(loginMenuEcoMap.this, loginEcoMap.class);
        startActivity(intent);
    }
}
