package uagrm.buyapp.di.app.component;

import android.content.Context;
import android.content.SharedPreferences;

import uagrm.buyapp.App;
import uagrm.buyapp.di.app.module.AppModule;
import uagrm.buyapp.di.scope.ApplicationScope;
import uagrm.buyapp.external.AnalyticsInterface;
import uagrm.buyapp.external.ConfigInterface;
import uagrm.buyapp.external.TaskReminderInterface;
import uagrm.buyapp.utils.rx.RxEventBus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import dagger.Component;

/**
 * The role of the component is to inject the dependencies in the specified targets
 * Targets must ALL be added here
 */
@ApplicationScope
@Component(
        modules = {AppModule.class}
)
public interface AppComponent {

    // Allows to inject into the App
    void inject(App app);

    Context context();
    App app();
    RxEventBus<Object> rxEventBus();
    Gson gson();
    FirebaseDatabase firebaseDatabase();
    FirebaseAuth firebaseAuth();
    AnalyticsInterface analyticsHelper();
    ConfigInterface configHelper();
    //TaskReminderInterface taskReminderHelper();
    SharedPreferences sharedPreferences();
}
