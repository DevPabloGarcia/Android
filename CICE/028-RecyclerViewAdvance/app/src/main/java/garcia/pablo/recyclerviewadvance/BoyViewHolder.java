package garcia.pablo.recyclerviewadvance;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import garcia.pablo.recyclerviewadvance.model.Boy;
/**
 * Created by V on 29/1/16.
 */
public class BoyViewHolder extends RecyclerView.ViewHolder {

    TextView textViewName,textViewBearded;

    public BoyViewHolder(View itemView) {

        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.text_view_name);
        textViewBearded = (TextView) itemView.findViewById(R.id.text_view_beard);
    }

    public void bind(Boy boy){
        textViewName.setText(boy.getName());
        if(boy.getBearded()){
            textViewBearded.setText("Barbudo");
        }else{
            textViewBearded.setText("No Barbudo");
        }
    }
}
