package com.example.examensegundocortepoo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examensegundocortepoo.Adaptadores.AdaptadorRevistas;
import com.example.examensegundocortepoo.Clases.RevistasCientificas;
import com.example.examensegundocortepoo.WebServices.Asynchtask;
import com.example.examensegundocortepoo.WebServices.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    RecyclerView recyclerView;
    ArrayList<RevistasCientificas> revistas;
    AdaptadorRevistas adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerViewProducts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        handleSSLHandshake();
        revistas=new ArrayList<>();
        traer_categorias();
    }
    private void traer_categorias() {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/journals.php", datos, this, this);
        ws.execute("GET");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray JSONlista=new JSONArray(result);
        for (int i=0; i<JSONlista.length();i++){
            JSONObject revista=JSONlista.getJSONObject(i);
            revistas.add(new RevistasCientificas(
                    revista.getInt("journal_id"),
                    revista.getString("portada"),
                    revista.getString("abbreviation"),
                    revista.getString("description"),
                    revista.getString("name")));
        }
        adapter=new AdaptadorRevistas(revistas,MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdaptadorRevistas.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent= new Intent(MainActivity.this,Ediciones.class);
                intent.putExtra("opcion",String.valueOf(revistas.get(position).getId()));
                startActivity(intent);
            }
        });
    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}