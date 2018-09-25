package pablo.com.mipiso.ui.main.taskList;

import java.util.ArrayList;

import pablo.com.mipiso.managers.dataManager.BaseCallback;
import pablo.com.mipiso.model.User;
import pablo.com.mipiso.ui.base.BaseListPresenter;
import pablo.com.mipiso.usecase.base.GetUsersUseCase;

public class TasksListPresenter extends BaseListPresenter<TasksListView> implements BaseCallback<ArrayList<User>> {

    private GetUsersUseCase getUsersUseCase;

    public TasksListPresenter(GetUsersUseCase getUsersUseCase) {
        this.getUsersUseCase = getUsersUseCase;
    }

    @Override
    public void onItemClick(int position) {

    }

    public void onCreateView() {
        if(getView() != null){
            getView().showProgress();
            getUsersUseCase.getData(this);
        }
    }

    @Override
    public void onSuccess(ArrayList<User> users) {
        if(getView() != null){
            getView().hideProgress();
            getView().updateUsers(users);
        }
    }

    @Override
    public void onCancel(Exception e) {
        if(getView() != null){
            getView().showAlertMessage(e.getMessage());
        }
    }
}
