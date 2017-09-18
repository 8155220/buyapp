package uagrm.buyapp.di.user;

import uagrm.buyapp.data.repositories.UserRepository;
import uagrm.buyapp.data.repositories.datasource.UserDataSourceRemote;
import uagrm.buyapp.di.app.component.AppComponent;
import uagrm.buyapp.di.scope.ActivityScope;
import uagrm.buyapp.domain.interactors.user.CheckUserUseCase;
import uagrm.buyapp.domain.interactors.user.LoginUserUseCase;
import uagrm.buyapp.domain.interactors.user.LogoutUserUseCase;
import uagrm.buyapp.domain.interactors.user.RegisterUserUseCase;
import uagrm.buyapp.domain.interactors.user.WriteUserUseCase;
import uagrm.buyapp.presentation.presenters.impl.LoginPresenter;
import uagrm.buyapp.presentation.presenters.impl.OnBoardingPresenter;
import uagrm.buyapp.presentation.presenters.impl.RegisterPresenter;
import uagrm.buyapp.presentation.ui.fragments.LandingFragment;
import uagrm.buyapp.presentation.ui.fragments.LoginFragment;
import uagrm.buyapp.presentation.ui.fragments.RegisterFragment;

import dagger.Component;

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = UserModule.class
)
public interface UserComponent {

    //Fragments
    void inject(LandingFragment view);
    void inject(LoginFragment view);
    void inject(RegisterFragment view);

    // Presenters
    void inject(OnBoardingPresenter presenter);
    void inject(LoginPresenter presenter);
    void inject(RegisterPresenter presenter);

    // UseCases/Interactors
    void inject(CheckUserUseCase useCase);
    void inject(LoginUserUseCase useCase);
    void inject(LogoutUserUseCase useCase);
    void inject(RegisterUserUseCase useCase);
    void inject(WriteUserUseCase useCase);

    // Repositories
    void inject(UserRepository repository);

    // DataSources
    void inject(UserDataSourceRemote dataSource);
}