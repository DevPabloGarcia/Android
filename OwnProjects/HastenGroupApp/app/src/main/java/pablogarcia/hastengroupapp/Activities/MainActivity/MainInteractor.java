package pablogarcia.hastengroupapp.Activities.MainActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import pablogarcia.hastengroupapp.ApiManager;
import pablogarcia.hastengroupapp.Model.Player;
import pablogarcia.hastengroupapp.Model.Sport;
import pablogarcia.hastengroupapp.Activities.Fragments.RecyclerViewFragment;
import pablogarcia.hastengroupapp.RetrofitManager.ApiService;
import pablogarcia.hastengroupapp.Utils.Consts;
import pablogarcia.hastengroupapp.ViewPager.ViewPagerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInteractor {

    interface OnGetSportListener {

        void onSuccess(ArrayList<Sport> sports);

        void onFailure();

    }

    private ApiService mApiService;

    public void getSports(final OnGetSportListener listener){

        mApiService = new ApiManager().getApiService(Consts.SPORT_URL);
        mApiService.sportList().enqueue(new Callback<ArrayList<Sport>>() {
            @Override
            public void onResponse(Call<ArrayList<Sport>> call, Response<ArrayList<Sport>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        listener.onSuccess(response.body());
                    }
                }else{
                    listener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sport>> call, Throwable t) {
                listener.onFailure();
            }


        });
    }

    public void setupViewPager(ViewPager viewPager, ArrayList<Sport> sports, FragmentManager fm){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(fm);
        // Foreach sport
        for (int i = 0; i<sports.size(); i++){
            // Convert players List into ArrayList
            ArrayList<Player> players = new ArrayList<>();
            players.addAll(sports.get(i).getPlayers());
            // Create new fragment with players
            RecyclerViewFragment fragment = RecyclerViewFragment.newInstance(players);
            // Add fragment to the view pager adapter
            viewPagerAdapter.addFragment(fragment, sports.get(i).getTitle());
        }
        // Set adapter to the view pager
        viewPager.setAdapter(viewPagerAdapter);
    }

}
