package pablo.com.mipiso.managers.dataManager;

public interface BaseCallback<T> {

    void onSuccess(T t);

    void onCancel(Exception e);

}
