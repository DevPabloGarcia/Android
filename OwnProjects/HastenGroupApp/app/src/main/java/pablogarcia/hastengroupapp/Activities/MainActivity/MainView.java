package pablogarcia.hastengroupapp.Activities.MainActivity;

import java.util.ArrayList;

import pablogarcia.hastengroupapp.Model.Sport;

public interface MainView {

    void setupToolbar();

    void setupTabLayout(ArrayList<Sport> sports);
}
