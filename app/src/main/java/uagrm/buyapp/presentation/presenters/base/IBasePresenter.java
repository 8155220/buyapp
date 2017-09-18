package uagrm.buyapp.presentation.presenters.base;


import uagrm.buyapp.presentation.ui.base.BaseMvpView;

/**
 * This interface must be inherited from  {@link BasePresenter}
 * Created by remychantenay on 26/04/2016.
 */
public interface IBasePresenter<V extends BaseMvpView> {

    void attachView(V view);
    void detachView();
    boolean isViewAttached();
    void checkViewAttached();
}