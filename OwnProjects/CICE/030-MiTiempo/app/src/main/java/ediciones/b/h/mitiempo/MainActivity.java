package ediciones.b.h.mitiempo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PronosticoHoraAdapter adapter;

    // Esto se usa si no va a tocar nada de la interfaz grafica.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PronosticoHoraAdapter(this);
        recyclerView.setAdapter(adapter);


        new TuTiempoAsyncTask().execute("http://xml.tutiempo.net/xml/3768.xml");

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                TuTiempoParser.getTiempoFromInputStream(getApplicationContext(), "http://xml.tutiempo.net/xml/3768.xml");
            }
        }).start();

        */

    }

    private class TuTiempoAsyncTask extends AsyncTask<String, Void, Tiempo > {

        String error;
        ProgressDialog progressDialog;

        // Se ejecuta justo antes de ejecutar algo en segunda plano.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this,"Cargando","Estamos cargando los datos");
        }

        // Esto se ejecuta en segundo plano.
        @Override
        protected Tiempo doInBackground(String... params) {

            String url = params[0];

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {

                return TuTiempoParser.getTiempoFromUrl(getApplicationContext(), url);

            } catch (TuTiempoParser.TiempoParserException e) {
                e.printStackTrace();
                error = e.getMessage();
                return null;
            }
        }

        // Esto se ejecuta en el hilo principal.

        @Override
        protected void onPostExecute(Tiempo tiempo) {
            super.onPostExecute(tiempo);
            if(tiempo != null){
                // Ha ido bien.
                adapter.setDataSet(tiempo.getPronosticoHora());
                Toast.makeText(getApplicationContext(), "Bien!", Toast.LENGTH_SHORT).show();
            }else{
                // Ha ido mal.
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }
            progressDialog.hide();
        }
    }
}
