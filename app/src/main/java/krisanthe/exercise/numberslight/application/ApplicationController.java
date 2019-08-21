package krisanthe.exercise.numberslight.application;

import android.app.Application;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import krisanthe.exercise.numberslight.BuildConfig;
import krisanthe.exercise.numberslight.application.modules.ContextModule;
import timber.log.Timber;

public class ApplicationController extends Application {

    private static AppComponent appComponent;
    private static ApplicationController applicationController;
    private Scheduler scheduler;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationController = this;

        initializeLogger();
        initAppComponent();

    }

    public static synchronized ApplicationController getInstance() {
        return applicationController;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }

    public void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private void initializeLogger() {
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO Realise version
                }
            });
        }
    }

}
