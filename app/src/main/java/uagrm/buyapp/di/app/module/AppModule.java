package uagrm.buyapp.di.app.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import uagrm.buyapp.App;
import uagrm.buyapp.di.app.component.AppComponent;
import uagrm.buyapp.di.scope.ApplicationScope;
import uagrm.buyapp.external.AnalyticsInterface;
import uagrm.buyapp.external.ConfigInterface;
import uagrm.buyapp.external.TaskReminderInterface;
import uagrm.buyapp.firebase.FirebaseAnalyticsHelper;
import uagrm.buyapp.firebase.FirebaseJobDispatcherHelper;
import uagrm.buyapp.firebase.FirebaseRemoteConfigHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * This module is in charge of providing dependencies to the {@link AppComponent}
 * Created by remychantenay on 23/05/2016.
 */
@Module
public class AppModule {
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @ApplicationScope
    public App provideApplication() {
        return app;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return app;
    }

    @Provides
    @ApplicationScope
    public Gson provideGSON() {
        return new Gson();
    }

    @Provides
    @ApplicationScope
    public FirebaseDatabase provideFirebaseDatabase() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        return firebaseDatabase;
    }

    @Provides
    @ApplicationScope
    public FirebaseAuth provideFirebaseAuth() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth;
    }

    @Provides
    @ApplicationScope
    public AnalyticsInterface provideAnalyticsHelper(Context context) {
        AnalyticsInterface AnalyticsInterface = new FirebaseAnalyticsHelper(FirebaseAnalytics.getInstance(context));
        return AnalyticsInterface;
    }

    @Provides
    @ApplicationScope
    public ConfigInterface provideConfigHelper() {
        ConfigInterface configInterface = new FirebaseRemoteConfigHelper(FirebaseRemoteConfig.getInstance());
        return configInterface;
    }

    @Provides
    @ApplicationScope
    public TaskReminderInterface provideTaskReminderHelper() {
        TaskReminderInterface taskReminderInterface = new FirebaseJobDispatcherHelper();
        return taskReminderInterface;
    }

    @Provides
    @ApplicationScope
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
