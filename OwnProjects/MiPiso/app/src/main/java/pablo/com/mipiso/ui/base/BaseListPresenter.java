package pablo.com.mipiso.ui.base;

public abstract class BaseListPresenter<T extends BaseView> extends BasePresenter<T>{

    public abstract void onItemClick(int position);

}
