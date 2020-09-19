package com.example.examensegundocortepoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examensegundocortepoo.Adaptadores.AdaptadorEdiciones;
import com.example.examensegundocortepoo.Adaptadores.AdaptadorRevistas;
import com.example.examensegundocortepoo.Clases.RevistasCientificas;
import com.example.examensegundocortepoo.Clases.RevistasEdiciones;
import com.example.examensegundocortepoo.WebServices.Asynchtask;
import com.example.examensegundocortepoo.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ediciones extends AppCompatActivity  implements Asynchtask {
    RecyclerView recyclerView;
    ArrayList<RevistasEdiciones> revistas;
    AdaptadorEdiciones adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediciones);
        String valor=getIntent().getStringExtra("opcion");
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerViewEdiciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Ediciones.this));
        revistas=new ArrayList<>();
        if(valor!=null){
            Map<String, String> datos = new HashMap<String, String>();
            WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/issues.php?j_id="+Integer.parseInt(valor)+"", datos, Ediciones.this, Ediciones.this);
            ws.execute("GET");
        }
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray JSONlista=new JSONArray(result);
        for (int i=0; i<JSONlista.length();i++){
            JSONObject revista=JSONlista.getJSONObject(i);
            revistas.add(new RevistasEdiciones(
                    revista.getInt("issue_id"),
                    revista.getInt("volume"),
                    revista.getInt("number"),
                    revista.getInt("year"),
                    revista.getString("date_published"),
                    revista.getString("title"),
                    revista.getString("doi"),
                    revista.getString("cover")));
        }
         adapter=new AdaptadorEdiciones(Ediciones.this, revistas, new AdaptadorEdiciones.OnItemClickListener() {
             @Override
             public void onItemClick(int position) {
                 Intent intent= new Intent(Ediciones.this,Articulos.class);
                 intent.putExtra("opcion",String.valueOf(revistas.get(position).getId()));
                 startActivity(intent);
             }
         });
        recyclerView.setAdapter(adapter);
    }
}