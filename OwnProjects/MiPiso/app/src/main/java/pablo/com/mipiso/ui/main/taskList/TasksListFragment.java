package pablo.com.mipiso.ui.main.taskList;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablo.com.mipiso.R;
import pablo.com.mipiso.managers.dataManager.BaseCallback;
import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BaseActivity;
import pablo.com.mipiso.ui.base.BaseFragmentList;
import pablo.com.mipiso.ui.main.MainActivity;

public class TasksListFragment extends BaseFragmentList implements TasksListView {

    @Inject
    TasksListPresenter mPresenter;

    @BindView(R.id.userlist_recyclerview)
    RecyclerView recyclerView;

    TasksListRecyclerViewAdapter tasksListRecyclerViewAdapter;

    @Override
    public void initializerDagger() {
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    }

    @Override
    public void onCreatePresenter() {
        mPresenter.setView(this);
    }

    public static TasksListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TasksListFragment fragment = new TasksListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);
        ButterKnife.bind(this, view);

        initializeRecyclerView();

        mPresenter.onCreateView();

        return view;
    }

    @Override
    public void updateUsers(ArrayList<User> objects) {
        tasksListRecyclerViewAdapter.setDataSet(objects);
    }

    @Override
    public void initializeRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tasksListRecyclerViewAdapter = new TasksListRecyclerViewAdapter();
        recyclerView.setAdapter(tasksListRecyclerViewAdapter);
    }

    @Override
    public void showProgress() {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity)activity).showProgress();
        }
    }

    @Override
    public void hideProgress() {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity)activity).hideProgress();
        }
    }
}
