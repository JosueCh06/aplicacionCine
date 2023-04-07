package com.example.aplicacioncine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainProcesar extends AppCompatActivity implements View.OnClickListener{

    TextView tvClienteR,tvGeneroR,tvPeliculaR,tvCAdultosR,tvCNiñosR, tvMontoAdultos,tvMontoNiños,tvMontoConfiteria,tvTotalPagar;
    EditText edtCFamiliar,edtCPersonal;
    CheckBox chkFamiliar,chkPersonal;
    ImageView imgPelicula;
    Button btnCalcular,btnVolver;

    int posGenero, posPelicula, ID, cantBoletosAdultos, cantBoletosNiños;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesar);

        tvClienteR = findViewById(R.id.tvClienteR);
        tvGeneroR = findViewById(R.id.tvGeneroR);
        tvPeliculaR = findViewById(R.id.tvPeliculaR);
        tvCAdultosR = findViewById(R.id.tvCAdultosR);
        tvCNiñosR = findViewById(R.id.tvCNiñosR);
        tvMontoAdultos = findViewById(R.id.tvMontoAdultos);
        tvMontoNiños = findViewById(R.id.tvMontoNiños);
        tvMontoConfiteria = findViewById(R.id.tvMontoConfiteria);
        tvTotalPagar = findViewById(R.id.tvTotalPagar);
        edtCFamiliar = findViewById(R.id.edtCFamiliarR);
        edtCPersonal = findViewById(R.id.edtCPersonalR);
        chkFamiliar = findViewById(R.id.chkFamiliar);
        chkPersonal = findViewById(R.id.chkPersonal);
        imgPelicula = findViewById(R.id.imgPeliculaR);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnVolver = findViewById(R.id.btnVolver);
        btnCalcular.setOnClickListener(this);
        btnVolver.setOnClickListener(this);
        chkFamiliar.setOnClickListener(this);
        chkPersonal.setOnClickListener(this);
        mostrarDatos();
    }

    @Override
    public void onClick(View v) {
        if(v == chkFamiliar){
            if(chkFamiliar.isChecked()){
                edtCFamiliar.setEnabled(true);
                edtCFamiliar.requestFocus();
            }
            else{
                edtCFamiliar.setText("0");
                edtCFamiliar.setEnabled(false);
            }
        }
        else if(v == chkPersonal){
            if(chkPersonal.isChecked()){
                edtCPersonal.setEnabled(true);
                edtCPersonal.requestFocus();
            }
            else{
                edtCPersonal.setText("0");
                edtCPersonal.setEnabled(false);
            }
        }
        else if(v == btnCalcular){
            double costoPelicula, costoPeliculaNiños, montoConfiteria, montoAdultos, montoNiños, pagoTotal;
            int cantFamiliar,cantPersonal;
            if(posGenero==0 && posPelicula==0)
                costoPelicula=35.5;
            else if(posGenero==0 && posPelicula==1)
                costoPelicula=32.5;
            else if(posGenero==1 && posPelicula==0)
                costoPelicula=30.5;
            else if(posGenero==1 && posPelicula==1)
                costoPelicula=28.3;
            else if(posGenero==1 && posPelicula==2)
                costoPelicula=25.5;
            else if(posGenero==2 && posPelicula==0)
                costoPelicula=45.5;
            else if(posGenero==2 && posPelicula==1)
                costoPelicula=40.3;
            else if(posGenero==2 && posPelicula==2)
                costoPelicula=43;
            else if(posGenero==2 && posPelicula==3)
                costoPelicula=38.9;
            else if(posGenero==3 && posPelicula==0)
                costoPelicula=58.9;
            else if(posGenero==3 && posPelicula==1)
                costoPelicula=57;
            else if(posGenero==3 && posPelicula==2)
                costoPelicula=60;
            else if(posGenero==3 && posPelicula==3)
                costoPelicula=65.5;
            else
                costoPelicula=57.8;

            // Costo Pelicula Niños
            costoPeliculaNiños = costoPelicula - 10;

            //Precio - Confitería
            cantFamiliar = Integer.parseInt(edtCFamiliar.getText().toString());
            cantPersonal = Integer.parseInt(edtCPersonal.getText().toString());
            montoConfiteria = cantFamiliar * 35.4 + cantPersonal * 12.9;

            //Montos
            montoAdultos = costoPelicula * cantBoletosAdultos;
            montoNiños = costoPeliculaNiños * cantBoletosNiños;
            //Pago total
            pagoTotal = montoAdultos + montoNiños + montoConfiteria;
            //Mostrar Datos
            tvMontoAdultos.setText("Monto Adultos: S/." + montoAdultos);
            tvMontoNiños.setText("Monto Niños: S/." + montoNiños);
            tvMontoConfiteria.setText("Monto Confiteria: S/." + montoConfiteria);
            tvTotalPagar.setText("Total a pagar: S/." + pagoTotal);
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    void mostrarDatos(){
        Bundle datos = this.getIntent().getExtras();
        tvClienteR.setText("Cliente: " + datos.getString("cliente"));
        tvGeneroR.setText("Género: " + datos.getString("genero"));
        tvPeliculaR.setText("Película: " + datos.getString("pelicula"));
        cantBoletosAdultos = datos.getInt("cantAdultos");
        cantBoletosNiños = datos.getInt("cantNiños");
        tvCAdultosR.setText("Cantidad de Asisentos de Adultos: " + cantBoletosAdultos);
        tvCNiñosR.setText("Cantidad de Asisentos de Niños: " + cantBoletosNiños);
        //Imagenes de las peliculas
        posGenero = datos.getInt("posicionGenero");
        posPelicula = datos.getInt("posicionPelicula");
        ID = this.getResources().getIdentifier("p" + posGenero + posPelicula,"drawable",this.getPackageName());
        imgPelicula.setImageResource(ID);
    }
}