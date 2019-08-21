package krisanthe.exercise.numberslight.features.numbers.dagger;

import dagger.Component;
import krisanthe.exercise.numberslight.application.AppComponent;
import krisanthe.exercise.numberslight.features.numbers.NumbersActivity;

@NumbersScope
@Component(modules = {NumbersModule.class}, dependencies = {AppComponent.class})
public interface NumbersComponent {
    void inject(NumbersActivity activity);
}
