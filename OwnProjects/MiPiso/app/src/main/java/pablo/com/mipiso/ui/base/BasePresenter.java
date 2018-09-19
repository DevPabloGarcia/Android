package pablo.com.mipiso.ui.base;

public class BasePresenter <T extends BaseView>{

    private T mView;

    public T getView() {
        return mView;
    }

    public void setView(T mView) {
        this.mView = mView;
    }
}
