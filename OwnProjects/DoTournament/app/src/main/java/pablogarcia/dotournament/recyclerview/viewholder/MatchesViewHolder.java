package pablogarcia.dotournament.recyclerview.viewholder;

import android.content.Context;
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
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.model.Match;
import pablogarcia.dotournament.recyclerview.clickrowinterface.OnClickMatchRow;

/**
 * Created by V on 25/3/16.
 */
public class MatchesViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.text_view_date) TextView textViewDate;
    @Bind(R.id.text_view_init_time) TextView textViewInitTime;
    @Bind(R.id.text_view_end_time) TextView textViewEndTime;
    @Bind(R.id.text_view_sport) TextView textViewSport;
    @Bind(R.id.text_view_available_sites) TextView textViewAvailableSites;
    @Bind(R.id.image_view) ImageView imageView;
    @Bind(R.id.text_view_address_name) TextView textViewAddressName;
    @Bind(R.id.text_view_address) TextView textViewAddress;

    private Context context;
    private ApiManager apiManager;
    private AsyncTask<String, Bitmap, Bitmap> task;
    private Match match;
    private OnClickMatchRow mListener;
    private View row;

    public MatchesViewHolder(View itemView, OnClickMatchRow listener) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        apiManager = ApiManager.getInstance(context);
        row = itemView;
        mListener = listener;
    }

    public void bind(final Match match){

        this.match = match;

        textViewSport.setText(match.getSport().getName());
        textViewDate.setText(apiManager.convertDateInString(match.getDate()));
        textViewInitTime.setText(apiManager.convertTimeInString(match.getInitTime()));
        textViewEndTime.setText(apiManager.convertTimeInString(match.getEndTime()));
        textViewAvailableSites.setText(String.valueOf(match.getAvailableSites()));
        textViewAddressName.setText(match.getPlace().getName());
        textViewAddress.setText(match.getPlace().getAddress());

        if(match.getSport().getImageBitmap() != null){
            imageView.setImageBitmap(match.getSport().getImageBitmap());
        }else{
            task = new ImageTask(context).execute(match.getSport().getImage());
        }

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickMatchRow(match);
            }
        });
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
                return Glide.with(context).load(params[0]).asBitmap().into(R.dimen.image_view_small,R.dimen.image_view_small).get();
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
                match.getSport().setImageBitmap(bitmap);
            }
        }

    }
}
