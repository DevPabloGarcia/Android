package garcia.pablo.recyclerviewadvance;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import garcia.pablo.recyclerviewadvance.model.Person;

/**
 * Created by V on 29/1/16.
 */
public class PersonViewHolder extends RecyclerView.ViewHolder{

    TextView textView;

    public PersonViewHolder(View itemView) {

        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text_view_name);
    }

    public void bind(Person person){
        textView.setText(person.getName());
    }
}
