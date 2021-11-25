package com.example.listaejercicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private JSonSerialicer serialicer;

    private RecyclerView lista;
    private LinearLayoutManager linearLayout;
    private Button boton;
    private EditText titulo,contenido;
    private List<Dato> datos = new ArrayList<>();
    private Adaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datos = leerFichero();

        lista = findViewById(R.id.listaView);
        adapter = new Adaptador(datos);
        lista.setAdapter(adapter);

        linearLayout = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayout);

        titulo = findViewById(R.id.titulo);
        contenido = findViewById(R.id.contenido);
        boton = findViewById(R.id.boton);



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.add(new Dato(titulo.getText().toString(),contenido.getText().toString()));
                adapter = new Adaptador(datos);
                lista.setAdapter(adapter);

                titulo.setText("");
                contenido.setText("");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        escribirFichero();
    }

    private List<Dato> leerFichero(){
        serialicer = new JSonSerialicer("prueba.json",MainActivity.this.getApplicationContext(),datos);//lo mismo que poner this

        return serialicer.load();
    }

    private void escribirFichero(){
        serialicer = new JSonSerialicer("prueba.json",MainActivity.this.getApplicationContext(),datos);//lo mismo que poner this

        serialicer.save();
    }
}