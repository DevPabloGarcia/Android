package garcia.pablo.butterknife.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import garcia.pablo.butterknife.R;
import garcia.pablo.butterknife.model.Player;

/**
 * Created by V on 23/1/16.
 */
public class PlayerViewHolder extends RecyclerView.ViewHolder {

    TextView name, surname, address, email, ranking, age;

    public PlayerViewHolder(View itemView) {
        super(itemView);
        this.bindings();
    }

    public void bind(Player player){
        this.name.setText(player.getName());
        this.surname.setText(player.getSurname());
        this.address.setText(player.getAddress());
        this.email.setText(player.getEmail());
        this.ranking.setText(String.valueOf(player.getRanking()));
        this.age.setText(String.valueOf(player.getAge()));
    }

    public void bindings(){
        name = (TextView) itemView.findViewById(R.id.row_name);
        surname = (TextView) itemView.findViewById(R.id.row_surname);
        address = (TextView) itemView.findViewById(R.id.row_address);
        email = (TextView) itemView.findViewById(R.id.row_email);
        ranking = (TextView) itemView.findViewById(R.id.row_ranking);
        age = (TextView) itemView.findViewById(R.id.row_age);
    }

}
