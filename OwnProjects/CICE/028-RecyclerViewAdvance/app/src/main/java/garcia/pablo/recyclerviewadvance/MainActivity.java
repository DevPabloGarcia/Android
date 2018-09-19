package garcia.pablo.recyclerviewadvance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

import garcia.pablo.recyclerviewadvance.model.Boy;
import garcia.pablo.recyclerviewadvance.model.Girl;
import garcia.pablo.recyclerviewadvance.model.Person;

public class MainActivity extends AppCompatActivity implements SwipeCallBack.OnSwipedListener{

    RecyclerView recyclerView;
    PersonRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        SwipeCallBack swipeCallBack = new SwipeCallBack(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        setupRecycler();
    }

    public void setupRecycler(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PersonRecyclerAdapter(getPersons(),this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Person> getPersons(){
        ArrayList<Person> persons = new ArrayList<>();

        for(int i=0; i<20; i++){
            if(i%2==0)
                persons.add(new Boy("Pablo " + i,true));
            if(i%2==1)
                persons.add(new Girl("Maria " + i,true));
        }
        return persons;
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder) {
        int position = viewHolder.getAdapterPosition();
        adapter.remove(position);
    }
}
