package com.example.examensegundocortepoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleArticulos extends AppCompatActivity {
    TextView titulo;
    TextView autores;
    TextView doi;
    TextView palabrasclaves;
    TextView volumen;
    TextView seccion;
    TextView resumen;
    String title,autors,doii,keywors,volum,secci,resum,htm,pdff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulos);
        titulo=(TextView)findViewById(R.id.txttitulodetalle);
        autores=(TextView)findViewById(R.id.txtautoresdetalle);
        doi=(TextView)findViewById(R.id.txtdoidetalle);
        palabrasclaves=(TextView)findViewById(R.id.txtpalabrasclavesdetalle);
        volumen=(TextView)findViewById(R.id.txtvolumendetalle);
        seccion=(TextView)findViewById(R.id.txtsecciondetalle);
        resumen=(TextView)findViewById(R.id.textlineresumen);
         title = getIntent().getStringExtra("titulo");
         autors = getIntent().getStringExtra("autores");
         doii = getIntent().getStringExtra("doi");
         keywors = getIntent().getStringExtra("keywords");
         volum = getIntent().getStringExtra("volumen");
         secci = getIntent().getStringExtra("seccion");
         resum = getIntent().getStringExtra("resumen");
         htm = getIntent().getStringExtra("html");
         pdff = getIntent().getStringExtra("pdf");

        titulo.setText(title);
        autores.setText(autors.trim());
        doi.setText(doii);
        palabrasclaves.setText(keywors.trim());
        volumen.setText(volum);
        seccion.setText(secci);
        resumen.setText(Html.fromHtml(resum));

    }
    public void mostrarhtml(View view){
        Uri uri = Uri.parse(htm);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void mostrarpdf(View view){
        Uri uri = Uri.parse(pdff);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}