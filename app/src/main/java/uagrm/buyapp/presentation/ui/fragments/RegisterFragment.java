package uagrm.buyapp.presentation.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import uagrm.buyapp.App;
import uagrm.buyapp.R;
//import uagrm.buyapp.di.user.DaggerUserComponent;
import uagrm.buyapp.di.user.DaggerUserComponent;
import uagrm.buyapp.di.user.UserComponent;
import uagrm.buyapp.di.user.UserModule;
import uagrm.buyapp.presentation.presenters.impl.RegisterPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uagrm.buyapp.presentation.presenters.RegisterMVP;
import uagrm.buyapp.presentation.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance()} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment   extends BaseFragment
      implements RegisterMVP.View {

    public static final String TAG = RegisterFragment.class.getName();

    OnShowMessageListener onShowMessageListener;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.text_input_layout_email)
    TextInputLayout textInputLayoutEmail;
    @BindView(R.id.text_input_layout_password)
    TextInputLayout textInputLayoutPassword;
    @BindView(R.id.cta_register)
    Button ctaRegister;

    @OnClick(R.id.cta_register)
    public void clickCtaRegister() {
        presenter.register(textInputLayoutEmail.getEditText().getText().toString(),
                textInputLayoutPassword.getEditText().getText().toString());
    }

    @Inject
    RegisterPresenter presenter;
    UserComponent component;

    @Override
    public void injectDependencies() {
        if (component == null) {
            component = DaggerUserComponent.builder()
                    .appComponent(App.get(getContext()).getComponent())
                    .userModule(new UserModule(this))
                    .build();
            component.inject(this);
        }
    }

    @Override
    public void attachToPresenter() {
        this.presenter.attachView(this);
    }

    @Override
    public void detachFromPresenter() {
        this.presenter.detachView();
    }

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment TransactionsFragment.
     */
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getArgs(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        this.injectDependencies();
        this.attachToPresenter();
        super.onAttach(context);
        if (context instanceof OnShowMessageListener) {
            onShowMessageListener = (OnShowMessageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnShowMessageListener");
        }
    }

    @Override
    public void onDetach() {
        detachFromPresenter();
        super.onDetach();
    }

    @Override
    public void getArgs(Bundle _bundle) {
        // Empty intentionally.
    }

    @Override
    public void onLandscape() {
        // Empty intentionally.
    }

    @Override
    public void onPortrait() {
        // Empty intentionally.
    }

    @Override
    public void showLoading() {
        textInputLayoutEmail.setEnabled(false);
        textInputLayoutPassword.setEnabled(false);
        ctaRegister.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        textInputLayoutEmail.setEnabled(false);
        textInputLayoutPassword.setEnabled(false);
        ctaRegister.setEnabled(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        if (onShowMessageListener != null) {
            onShowMessageListener.onShowMessage(message);
        }
    }

    @Override
    public void showNoNetwork() {
        // Empty intentionally.
    }

    @Override
    public void setErrorEmailField() {
        textInputLayoutEmail.setError(getResources().getString(R.string.error_login_invalid_email));
        textInputLayoutEmail.setErrorEnabled(true);
    }

    @Override
    public void setErrorPasswordField() {
        textInputLayoutPassword.setError(getResources().getString(R.string.error_login_invalid_password));
        textInputLayoutPassword.setErrorEnabled(true);
    }

    @Override
    public void onSuccess() {
        presenter.goToBucket(getContext());
        getActivity().finish();
    }

    @Override
    public void onFailure() {
        showMessage(getResources().getString(R.string.error_firebase_auth_register));
        textInputLayoutPassword.getEditText().setText("");
    }
}