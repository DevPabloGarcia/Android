package pablogarcia.meetup.Modules.Fragments.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pablogarcia.meetup.Managers.RecyclerViewManager.OnClickMeetRow;
import pablogarcia.meetup.Model.Meet;

public class RecyclerViewPresenter {

    private RecyclerViewView recyclerViewView;
    private RecyclerViewInteractor recyclerViewInteractor;

    public RecyclerViewPresenter(RecyclerViewView recyclerViewView, RecyclerViewInteractor recyclerViewInteractor) {
        this.recyclerViewView = recyclerViewView;
        this.recyclerViewInteractor = recyclerViewInteractor;
    }

    public void onCreateView(RecyclerView recyclerView, Context context, boolean currentMeets, OnClickMeetRow listener){
        this.recyclerViewInteractor.onCreateView(recyclerView, context, currentMeets, listener);
    }
}
