package uagrm.buyapp.domain.interactors.user;

import uagrm.buyapp.data.repositories.UserRepository;
import uagrm.buyapp.domain.interactors.Params;
import uagrm.buyapp.domain.interactors.base.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Implementation of {@link BaseUseCase} that represents a UseCase/Interactor
 */
public class LoginUserUseCase extends BaseUseCase {

  private final UserRepository userRepository;

  public final static String PARAMS_KEY_EMAIL = "param_email";
  public final static String PARAMS_KEY_PASSWORD = "param_password";

  @Inject
  public LoginUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Observable getObservable(Params params) {
    return this.userRepository.login(params.getString(PARAMS_KEY_EMAIL, null),
            params.getString(PARAMS_KEY_PASSWORD, null));
  }
}