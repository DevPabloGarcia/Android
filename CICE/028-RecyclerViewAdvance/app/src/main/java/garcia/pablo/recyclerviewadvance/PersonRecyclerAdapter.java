package garcia.pablo.recyclerviewadvance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import garcia.pablo.recyclerviewadvance.model.Boy;
import garcia.pablo.recyclerviewadvance.model.Girl;
import garcia.pablo.recyclerviewadvance.model.Person;

/**
 * Created by V on 29/1/16.
 */
public class PersonRecyclerAdapter extends RecyclerView.Adapter {

    private static final int TYPE_PERSON = 0;
    private static final int TYPE_BOY = 1;
    private static final int TYPE_GIRL = 2;

    Context context;
    ArrayList<Person> persons;

    public PersonRecyclerAdapter(ArrayList<Person> persons,Context context) {
        this.persons = persons;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout;
        switch(viewType){
            case TYPE_BOY:
                layout = LayoutInflater.from(context).inflate(R.layout.row_boy,parent,false);
                return new BoyViewHolder(layout);
            case TYPE_GIRL:
                layout = LayoutInflater.from(context).inflate(R.layout.row_girl,parent,false);
                return new GirlViewHolder(layout);
            default:
                layout = LayoutInflater.from(context).inflate(R.layout.row_person,parent,false);
                return new PersonViewHolder(layout);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof BoyViewHolder){

            ((BoyViewHolder)holder).bind((Boy)persons.get(position));

        }else if(holder instanceof GirlViewHolder){

            ((GirlViewHolder)holder).bind((Girl)persons.get(position));

        }else{

        }
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public int getItemViewType(int position) {

        Person person = persons.get(position);

        if(person instanceof Boy){
            return TYPE_BOY;
        }else
        if(person instanceof Girl){
            return TYPE_GIRL;
        }else{
            return TYPE_PERSON;
        }

    }

    public void remove(int position){
        persons.remove(position);
        notifyItemRemoved(position);
    }
}
