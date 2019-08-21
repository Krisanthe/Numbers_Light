package krisanthe.exercise.numberslight.features.numbers.dagger;


import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import krisanthe.exercise.numberslight.api.NumberApi;
import krisanthe.exercise.numberslight.features.numbers.NumbersActivity;
import krisanthe.exercise.numberslight.features.numbers.logic.NumbersModel;
import krisanthe.exercise.numberslight.features.numbers.logic.NumbersPresenter;
import krisanthe.exercise.numberslight.features.numbers.logic.NumbersView;
import krisanthe.exercise.numberslight.utils.AppSchedulers;

@Module
public class NumbersModule {

    NumbersActivity context;

    public NumbersModule(NumbersActivity context) {
        this.context = context;
    }

    @NumbersScope
    @Provides
    NumbersActivity provideContext() {
        return this.context;
    }

    @NumbersScope
    @Provides
    NumbersPresenter providePresenter(NumbersModel model, NumbersView view, AppSchedulers schedulers) {
        CompositeDisposable subscriptions = new CompositeDisposable();
        return new NumbersPresenter(model, view, schedulers, subscriptions);
    }

    @NumbersScope
    @Provides
    NumbersView provideView() {
        return new NumbersView(context);
    }

    @NumbersScope
    @Provides
    NumbersModel provideModel(NumberApi api) {
        return new NumbersModel(context, api);
    }
}
