package pablogarcia.dotournament.recyclerview.viewholder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.activity.detail.DetailUserActivity;
import pablogarcia.dotournament.model.User;
import pablogarcia.dotournament.utils.Consts;

/**
 * Created by pablo.garcia on 6/4/16.
 */
public class PlayerViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.image_view) ImageView imageView;
    @Bind(R.id.text_view_name) TextView textViewName;

    protected Context context;
    protected View row;
    protected User user;
    protected ImageTask task;

    public PlayerViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.row = itemView;
        this.context = itemView.getContext();
    }

    public void bind(User user){

        this.user = user;

        textViewName.setText(user.getName());

        if(user.getImageBitmap() != null){
            imageView.setImageBitmap(user.getImageBitmap());
        }else{
            task = new ImageTask(context);
            task.execute(user.getImage());
        }
    }

    public void cancelTask(){
        if(task!=null)
            task.cancel(true);
        imageView.setImageBitmap(null);
    }


    protected class ImageTask extends AsyncTask<String, Bitmap, Bitmap> {

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
                user.setImageBitmap(bitmap);
            }
        }
    }

}
