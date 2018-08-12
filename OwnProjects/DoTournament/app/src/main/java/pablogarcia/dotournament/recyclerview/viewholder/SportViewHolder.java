package pablogarcia.dotournament.recyclerview.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.model.Sport;

/**
 * Created by V on 25/3/16.
 */
public class SportViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.image_view) ImageView imageView;
    @Bind(R.id.text_view_name) TextView textViewName;
    @Bind(R.id.text_view_sumary) TextView textViewDescription;

    private Context context;
    private AsyncTask<String, Bitmap, Bitmap> task;
    private Sport sport;

    public SportViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public void bind(Sport sport){
        this.sport = sport;
        textViewName.setText(sport.getName());
        textViewDescription.setText(sport.getSumary());

        if(sport.getImageBitmap() != null){
            imageView.setImageBitmap(sport.getImageBitmap());
        }else{
            task = new ImageTask(context).execute(sport.getImage());
        }
    }

    public void cancelTask(){
        if(task != null)
            task.cancel(true);
        imageView.setImageBitmap(null);
    }

    private class ImageTask extends AsyncTask<String, Bitmap, Bitmap> {

        Context context;

        public ImageTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                return Glide.with(context).load(params[0]).asBitmap().placeholder(R.drawable.placeholder).into(300,300).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if ((bitmap!=null)&& !isCancelled()){
                imageView.setImageBitmap(bitmap);
                sport.setImageBitmap(bitmap);
            }
        }

    }
}
