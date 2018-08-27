package pablogarcia.meetup.Modules.Fragments.RecyclerView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pablogarcia.meetup.Managers.RecyclerViewManager.OnClickMeetRow;
import pablogarcia.meetup.Managers.RecyclerViewManager.RecyclerViewAdapter;
import pablogarcia.meetup.Model.Meet;

public class RecyclerViewInteractor {

    public interface OnRecyclerViewCallback{

    }

    private RecyclerViewAdapter recyclerViewAdapter;

    public void onCreateView(RecyclerView recyclerView, Context context, ArrayList<Meet> meets, OnClickMeetRow listener){
        this.recyclerViewAdapter  = new RecyclerViewAdapter(listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setDataSet(meets);
    }

}
