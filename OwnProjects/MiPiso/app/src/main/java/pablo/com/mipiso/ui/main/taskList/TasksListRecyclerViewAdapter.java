package pablo.com.mipiso.ui.main.taskList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pablo.com.mipiso.R;
import pablo.com.mipiso.model.User;

public class TasksListRecyclerViewAdapter extends RecyclerView.Adapter<TasksListTaskViewHolder>{

    private ArrayList<User> users;

    @NonNull
    @Override
    public TasksListTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_user, parent, false);
        return new TasksListTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksListTaskViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        if(users != null){
            return users.size();
        }
        return 0;
    }

    public void setDataSet(ArrayList<User> users){
        this.users = users;
        notifyDataSetChanged();
    }
}
