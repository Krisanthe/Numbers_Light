package krisanthe.exercise.numberslight.application;

import dagger.Component;
import krisanthe.exercise.numberslight.api.NumberApi;
import krisanthe.exercise.numberslight.application.modules.ConnectionModule;
import krisanthe.exercise.numberslight.application.modules.ContextModule;
import krisanthe.exercise.numberslight.application.modules.NumberServiceModule;
import krisanthe.exercise.numberslight.utils.AppSchedulers;

@AppScope
@Component(modules = {ContextModule.class, ConnectionModule.class, NumberServiceModule.class})
public interface AppComponent {

    NumberApi apiService();
    AppSchedulers appSchedulers();
}
