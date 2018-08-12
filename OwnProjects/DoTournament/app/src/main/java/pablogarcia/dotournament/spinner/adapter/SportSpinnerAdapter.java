package pablogarcia.dotournament.spinner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pablogarcia.dotournament.R;
import pablogarcia.dotournament.model.Sport;

/**
 * Created by V on 6/4/16.
 */
public class SportSpinnerAdapter extends ArrayAdapter<Sport>{

    private ArrayList<Sport> sports;
    private Context context;

    public SportSpinnerAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.sports = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return sports.size();
    }

    @Override
    public Sport getItem(int position) {
        return sports.get(position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomDropDownView(position,parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    public void setDataSet(ArrayList<Sport> sports){
        this.sports = sports;
        notifyDataSetChanged();
    }

    private View getCustomView(int position, ViewGroup parent){

        View listItem = LayoutInflater.from(context).inflate(R.layout.sport_spinner_item, parent, false);
        TextView textView = (TextView)listItem.findViewById(R.id.text_view);
        textView.setText(sports.get(position).getName());
        return textView;

    }

    private View getCustomDropDownView(int position, ViewGroup parent){

        View listItem = LayoutInflater.from(context).inflate(R.layout.sport_spinner_item_dropdown, parent, false);
        TextView textView = (TextView)listItem.findViewById(R.id.text_view);
        textView.setText(sports.get(position).getName());
        return textView;

    }
}
