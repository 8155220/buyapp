package uagrm.buyapp.presentation.presenters;

import android.content.Context;

import uagrm.buyapp.domain.models.UserModel;
import uagrm.buyapp.presentation.ui.base.BaseMvpView;


/**
 * Created by remychantenay on 08/05/2016.
 */
public interface LoginMVP {

    interface View extends BaseMvpView {
        void setErrorEmailField();
        void setErrorPasswordField();
        void onSuccess();
        void onFailure();
    }

    interface Presenter {
        void login(String email, String password);
        void onLoginSuccess(UserModel userModel);
        void onLoginFailure(Throwable e);

        void onLoginSuccessTracking(UserModel userModel);
        void onLoginFailureTracking(Throwable e);

        void goToBucket(Context context);
    }
}
