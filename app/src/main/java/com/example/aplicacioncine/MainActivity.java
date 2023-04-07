package com.example.aplicacioncine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText edtCliente,edtCAdultos,edtCNiños;
    Spinner spnPelicula;
    RadioButton rbtnComedias,rbtnDramaticas,rbtnTerror,rbtnInfantiles;
    Button btnProcesar;

    String nombGenero, nombPelicula;
    int posGenero, posPelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCliente = findViewById(R.id.edtCliente);
        edtCAdultos = findViewById(R.id.edtCAdultos);
        edtCNiños = findViewById(R.id.edtCNiños);
        spnPelicula = findViewById(R.id.spnPelicula);
        rbtnComedias = findViewById(R.id.rbtnComedia);
        rbtnDramaticas = findViewById(R.id.rbtnDramaticas);
        rbtnTerror = findViewById(R.id.rbtnTerror);
        rbtnInfantiles = findViewById(R.id.rbtnInfantiles);
        btnProcesar = findViewById(R.id.btnProcesar);
        spnPelicula.setOnItemSelectedListener(this);
        rbtnComedias.setOnClickListener(this);
        rbtnDramaticas.setOnClickListener(this);
        rbtnTerror.setOnClickListener(this);
        rbtnInfantiles.setOnClickListener(this);
        btnProcesar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == rbtnComedias){
            nombGenero = "Comedias";
            posGenero = 0;
            String datos[]={"Super cool","¿Qué pasó ayer?"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,datos);
            spnPelicula.setAdapter(adapter);
        }
        else if(v == rbtnDramaticas){
            nombGenero = "Dramáticas";
            posGenero = 1;
            String datos[]={"Lo imposible","12 años de esclavitud","Historias cruzadas"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,datos);
            spnPelicula.setAdapter(adapter);
        }
        else if(v == rbtnTerror){
            nombGenero = "Terror";
            posGenero = 2;
            String datos[]={"Annabelle 3","Nosotros","Un lugar en silencio","Halloween"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,datos);
            spnPelicula.setAdapter(adapter);
        }
        else if (v == rbtnInfantiles) {
            nombGenero = "Infantiles";
            posGenero = 3;
            String datos[] = {"Alvin y las ardillas", "Arthur y los Minimoys", "Bolt", "Cars", "Encantada"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
            spnPelicula.setAdapter(adapter);
        }
        else if(v==btnProcesar){
            Intent intent = new Intent(this,MainProcesar.class);
            intent.putExtra("cliente", edtCliente.getText().toString());
            intent.putExtra("genero", nombGenero);
            intent.putExtra("pelicula", nombPelicula);
            intent.putExtra("cantAdultos", Integer.parseInt(edtCAdultos.getText().toString()));
            intent.putExtra("cantNiños", Integer.parseInt(edtCNiños.getText().toString()));
            intent.putExtra("posicionGenero", posGenero);
            intent.putExtra("posicionPelicula", posPelicula);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        posPelicula = spnPelicula.getSelectedItemPosition();
        nombPelicula = spnPelicula.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}