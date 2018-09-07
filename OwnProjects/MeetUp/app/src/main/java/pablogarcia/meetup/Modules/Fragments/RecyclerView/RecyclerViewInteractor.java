package pablogarcia.meetup.Modules.Fragments.RecyclerView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pablogarcia.meetup.ApiManager;
import pablogarcia.meetup.Managers.DatabaseManager.OnDatabaseGetListener;
import pablogarcia.meetup.Managers.RecyclerViewManager.OnClickMeetRow;
import pablogarcia.meetup.Managers.RecyclerViewManager.RecyclerViewAdapter;
import pablogarcia.meetup.Model.Meet;

public class RecyclerViewInteractor implements OnDatabaseGetListener{


    public interface OnRecyclerViewCallback{

    }

    private RecyclerViewAdapter recyclerViewAdapter;

    public void onCreateView(RecyclerView recyclerView, Context context, boolean currentMeets, OnClickMeetRow listener){
        this.recyclerViewAdapter  = new RecyclerViewAdapter(listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recyclerViewAdapter);
        if(currentMeets){
            ApiManager.getInstance().getCurrentMeets(this);
        }else{
            ApiManager.getInstance().getFinishedMeets(this);
        }
    }

    @Override
    public void onGetSuccess(ArrayList<Meet> meets) {
        this.recyclerViewAdapter.setDataSet(meets);
    }


}
