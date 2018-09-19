package ediciones.b.h.mitiempo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by V on 4/2/16.
 */


public class PronosticoHoraViewHolder extends RecyclerView.ViewHolder {

    TextView textViewTexto, textViewTemperatura, textViewFecha,textViewHora;
    ImageView imageView;

    AsyncTask<String,Void,Bitmap> task;

    PronosticoHora pronosticoHora;

    public PronosticoHoraViewHolder(View itemView) {

        super(itemView);

        textViewTexto = (TextView) itemView.findViewById(R.id.text_view_texto);
        imageView = (ImageView) itemView.findViewById(R.id.image_view_icono);
        textViewTemperatura = (TextView) itemView.findViewById(R.id.text_view_temperatura);
        textViewFecha = (TextView) itemView.findViewById(R.id.text_view_fecha);
        textViewHora = (TextView) itemView.findViewById(R.id.text_view_hora);
    }

    public void bind(PronosticoHora pronosticoHora){

        this.pronosticoHora = pronosticoHora;

        textViewTexto.setText(pronosticoHora.getTexto());
        textViewTemperatura.setText(pronosticoHora.getTemperatura());
        textViewFecha.setText(pronosticoHora.getFecha());
        textViewHora.setText(pronosticoHora.getHoradatos());

        if(pronosticoHora.getIcono()!= null)
            imageView.setImageBitmap(pronosticoHora.getIcono());
        else
            task = new ImageTask(itemView.getContext()).execute(pronosticoHora.getIconoUrl());

        // El ya hace la gestión de llamarlo de forma asyncrona.
        /*Picasso picasso = Picasso.with(itemView.getContext());
        picasso.setIndicatorsEnabled(true);
        picasso.load(pronosticoHora.getIconoUrl()).into(imageView);*/
    }

    public void cancelTask(){
        task.cancel(true);
        imageView.setImageBitmap(null);
    }

    private class ImageTask extends AsyncTask<String,Void,Bitmap> {

        Context context;

        public ImageTask(Context context) {
            this.context = context;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];

            Picasso picasso = Picasso.with(context);
            try {
                // el 50 50 son pixeles por tanto hay que llamar a la funcion dpTopx();
                return picasso.load(url).resize(50,50).centerCrop().get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if ((bitmap!=null) && !isCancelled()){
                imageView.setImageBitmap(bitmap);
                pronosticoHora.setIcono(bitmap);
            }
        }
    }
}
