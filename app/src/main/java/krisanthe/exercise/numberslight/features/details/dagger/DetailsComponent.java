package krisanthe.exercise.numberslight.features.details.dagger;

import dagger.Component;
import krisanthe.exercise.numberslight.application.AppComponent;
import krisanthe.exercise.numberslight.features.details.DetailsActivity;

@Component(modules = {DetailsModule.class})
public interface DetailsComponent {
    void inject(DetailsActivity activity);
}
