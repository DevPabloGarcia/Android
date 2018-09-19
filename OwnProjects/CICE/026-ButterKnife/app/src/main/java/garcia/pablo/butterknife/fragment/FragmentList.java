package garcia.pablo.butterknife.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import garcia.pablo.butterknife.R;
import garcia.pablo.butterknife.model.Player;
import garcia.pablo.butterknife.recycler.RecyclerViewAdapater;

/**
 * Created by V on 23/1/16.
 */
public class FragmentList extends Fragment {

    @Bind(R.id.recycler_view)RecyclerView recyclerView;
    RecyclerViewAdapater recyclerViewAdapater;

    public static FragmentList newInstance() {
        return new FragmentList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        ButterKnife.bind(this,view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Player player1 = new Player("Pablo","García Núñez", "Bela", "Gil de Biedma nº101","pablogarcia.sg@gmail.com","70255453k",25,1900);
        Player player2 = new Player("Carlos", "García Núñez", "Mati Diaz", "Gil de Biedma nº101","garcianunez.carlos@gail.com", "52122331q",31,1910);
        Player player3 = new Player("Felix", "García Martín", "23123123j",56);
        Player player4 = new Player("Vitoria","Núñez Cantero", "75121311l",57);
        Player player5 = new Player("Fernando", "Belasteguin", "12312451k",35);
        Player player6 = new Player("Miguel", "Lamperti", "12314512g",37);

        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);

        recyclerViewAdapater = new RecyclerViewAdapater(getContext(),players);
        recyclerView.setAdapter(recyclerViewAdapater);

        return view;
    }
}
