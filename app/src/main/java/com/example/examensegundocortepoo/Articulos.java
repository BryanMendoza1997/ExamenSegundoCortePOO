package com.example.examensegundocortepoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examensegundocortepoo.Adaptadores.AdaptadorArticulo;
import com.example.examensegundocortepoo.Clases.Combobox;
import com.example.examensegundocortepoo.Clases.RevistaArticulo;
import com.example.examensegundocortepoo.WebServices.Asynchtask;
import com.example.examensegundocortepoo.WebServices.WebService;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Articulos extends AppCompatActivity implements Asynchtask {
    RecyclerView recyclerView;
    ArrayList<RevistaArticulo> revistas;
    public AdaptadorArticulo adapter;
    public ArrayList<String> estados= new ArrayList<>();
    private MaterialBetterSpinner estado;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);
        String valor = getIntent().getStringExtra("opcion");
        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewArticulos);
        estado = (MaterialBetterSpinner) findViewById(R.id.cmb_estado);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Articulos.this));
        revistas = new ArrayList<>();
        //Volley
        RequestQueue request = Volley.newRequestQueue(Articulos.this);
        StringRequest volley=new StringRequest(Request.Method.GET, "https://revistas.uteq.edu.ec/ws/pubssections.php?i_id=" + Integer.parseInt(valor) + "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.length()>0){
                    try {
                        ArrayList<Combobox>lista= new ArrayList<>();
                        JSONArray JSONlista = new JSONArray(response);
                        for (int i = 0; i < JSONlista.length(); i++) {
                            JSONObject revista = JSONlista.getJSONObject(i);
                            lista.add(new Combobox(revista.getInt("section_id"),revista.getString("section")));
                        }
                        llenarCombo(lista);
                    }catch (JSONException e){

                    }
                }

            } }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Articulos.this,"Publicación no guardada",Toast.LENGTH_SHORT).show();
            }
        });
        request.add(volley);
        //Termina Volley
        estados.add("Ciencias Agrarias");
        estados.add("Ciencias Ambientales");
        estados.add("Ciencias Informáticas");
        arrayAdapter = new ArrayAdapter<String>(Articulos.this,
                android.R.layout.simple_dropdown_item_1line, estados);
        estado.setAdapter(arrayAdapter);
        if (valor != null) {
            Map<String, String> datos = new HashMap<String, String>();
            WebService ws = new WebService("https://revistas.uteq.edu.ec/ws/pubs.php?i_id=" + Integer.parseInt(valor) + "", datos, Articulos.this, Articulos.this);
            ws.execute("GET");
        }
    }

    @Override
    public void processFinish(String result) throws JSONException {
            JSONArray JSONlista = new JSONArray(result);
            for (int i = 0; i < JSONlista.length(); i++) {
                JSONObject revista = JSONlista.getJSONObject(i);
                revistas.add(new RevistaArticulo(
                        revista.getString("section"),
                        revista.getInt("publication_id"),
                        revista.getString("title"),
                        revista.getString("doi"),
                        revista.getString("abstract"),
                        revista.getString("date_published"),
                        revista.getInt("submission_id"),
                        revista.getInt("seq"),
                        Obtener(revista.getString("galeys"), 0),
                        Obtener(revista.getString("galeys"), 1),
                        keyword(revista.getString("keywords")),
                        authors(revista.getString("authors"))));
            }
            adapter = new AdaptadorArticulo(revistas, Articulos.this, new AdaptadorArticulo.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent= new Intent(Articulos.this,DetalleArticulos.class);
                    intent.putExtra("titulo",revistas.get(position).getTitulo());
                    intent.putExtra("autores",revistas.get(position).getAutores());
                    intent.putExtra("doi",revistas.get(position).getDoi());
                    intent.putExtra("keywords",revistas.get(position).getPalabrasclaves());
                    intent.putExtra("volumen",String.valueOf(revistas.get(position).getIdsecuencia()));
                    intent.putExtra("seccion",revistas.get(position).getSeccion());
                    intent.putExtra("resumen",revistas.get(position).getAbstracto());
                    intent.putExtra("html",revistas.get(position).getHtml());
                    intent.putExtra("pdf",revistas.get(position).getPdf());
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);

    }

    public String Obtener(String palabra, int codicion) throws JSONException {
        String documento = "";
        JSONArray JSONlistakeyrords = new JSONArray(palabra);
        for (int i = 0; i < JSONlistakeyrords.length(); i++) {
            JSONObject revista = JSONlistakeyrords.getJSONObject(i);
            if (codicion == 0) {
                documento = revista.getString("UrlViewGalley");
                return documento;
            } else {
                documento = revista.getString("UrlViewGalley");
                return  documento;
            }
        }
        return documento;
    }
    public String keyword(String palabra) throws JSONException {
        String documento = "";
        JSONArray JSONlistakeyrords = new JSONArray(palabra);
        for (int i = 0; i < JSONlistakeyrords.length(); i++) {
            JSONObject revista = JSONlistakeyrords.getJSONObject(i);
            documento=documento+ revista.getString("keyword")+", ";
        }
        return documento;
    }
    public String authors(String palabra) throws JSONException {
        String documento = "";
        JSONArray JSONlistakeyrords = new JSONArray(palabra);
        for (int i = 0; i < JSONlistakeyrords.length(); i++) {
            JSONObject revista = JSONlistakeyrords.getJSONObject(i);
            if(JSONlistakeyrords.length()==1){
                documento=documento+revista.getString("nombres");
            }
            else
            {
                 documento = documento + revista.getString("nombres") + ", ";
            }
        }
        return documento.trim();
    }
    public void llenarCombo( final ArrayList<Combobox> lista){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                estados.clear();
                for (int i = 0; i < lista.size(); i++) {
                    estados.add(lista.get(i).getItem());
                }
                arrayAdapter.clear();
                arrayAdapter.addAll(estados);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}