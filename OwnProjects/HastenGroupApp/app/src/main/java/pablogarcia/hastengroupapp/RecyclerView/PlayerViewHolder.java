package pablogarcia.hastengroupapp.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.hastengroupapp.Model.Player;
import pablogarcia.hastengroupapp.R;


public class PlayerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.playerImage) ImageView playerImage;
    @BindView(R.id.playerName) TextView playerName;
    @BindView(R.id.playerSurname) TextView playerSurname;
    @BindView(R.id.playerDate) TextView playerDate;

    public PlayerViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

    }

    public void bind(Player player){
        Picasso.get()
                .load(player.getImage())
                .resize(60, 60)
                .placeholder(R.drawable.no_image)
                .centerCrop()
                .into(playerImage);
        this.playerName.setText(player.getName());
        this.playerSurname.setText(player.getSurname());
        this.playerDate.setText(player.getDate());
    }

}
