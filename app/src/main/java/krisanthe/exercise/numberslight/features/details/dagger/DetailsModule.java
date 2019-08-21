package krisanthe.exercise.numberslight.features.details.dagger;

import dagger.Module;
import dagger.Provides;
import krisanthe.exercise.numberslight.features.details.DetailsActivity;
import krisanthe.exercise.numberslight.features.details.logic.DetailsView;
import krisanthe.exercise.numberslight.models.Number;

@Module
public class DetailsModule {

    DetailsActivity context;
    Number number;

    public DetailsModule(DetailsActivity context, Number number) {
        this.context = context;
        this.number = number;
    }

    @Provides
    DetailsView provideView() {
        return new DetailsView(context, number);
    }
}
