package ediciones.b.h.mitiempo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by V on 4/2/16.
 */
public class PronosticoHoraAdapter extends RecyclerView.Adapter<PronosticoHoraViewHolder> {

    ArrayList<PronosticoHora> pronosticoHoras;
    Context context;

    public PronosticoHoraAdapter(Context context) {
        this.context = context;
        this.pronosticoHoras = new ArrayList<>();
    }

    public PronosticoHoraAdapter(ArrayList<PronosticoHora> pronosticoHoras, Context context) {
        this.pronosticoHoras = pronosticoHoras;
        this.context = context;
    }

    @Override
    public PronosticoHoraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pronostico_hora,parent,false);
        return new PronosticoHoraViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(PronosticoHoraViewHolder holder, int position) {
        holder.bind(pronosticoHoras.get(position));
    }

    @Override
    public int getItemCount() {
        return pronosticoHoras.size();
    }

    public void setDataSet(ArrayList<PronosticoHora> pronosticoHoras){
        this.pronosticoHoras = pronosticoHoras;
        notifyDataSetChanged();
    }

    @Override
    public void onViewRecycled(PronosticoHoraViewHolder holder) {
        holder.cancelTask();
        super.onViewRecycled(holder);
    }
}
