package garcia.pablo.recyclerviewadvance;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import garcia.pablo.recyclerviewadvance.model.Girl;

/**
 * Created by V on 29/1/16.
 */
public class GirlViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName,textViewBeautiful;

    public GirlViewHolder(View itemView) {

        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.text_view_name);
        textViewBeautiful = (TextView) itemView.findViewById(R.id.text_view_beautiful);
    }

    public void bind(Girl girl){
        textViewName.setText(girl.getName());
        if(girl.isBeautiful()){
            textViewBeautiful.setText("Guapa");
        }else{
            textViewBeautiful.setText("Fea");
        }
    }
}
