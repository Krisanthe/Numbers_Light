package krisanthe.exercise.numberslight.features.numbers.logic;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import krisanthe.exercise.numberslight.models.Number;
import krisanthe.exercise.numberslight.utils.AppSchedulers;
import timber.log.Timber;

public class NumbersPresenter {

    NumbersModel model;
    NumbersView view;
    AppSchedulers schedulers;
    CompositeDisposable subscriptions;

    ArrayList<Number> numbers = new ArrayList<>();


    public NumbersPresenter(NumbersModel model, NumbersView view, AppSchedulers schedulers, CompositeDisposable subscriptions) {
        this.model = model;
        this.view = view;
        this.schedulers = schedulers;
        this.subscriptions = subscriptions;

    }

    public void onCreate() {
        subscriptions.add(getNumberList());
        subscriptions.add(clickItemEvent());
        subscriptions.add(getRetryObservable());
    }

    public void onDestroy() {
        subscriptions.clear();
    }

    private Disposable clickItemEvent() {
        return view.getClickObservable().subscribe(index -> subscriptions.add(getNumberDetails(numbers.get(index))));
    }

    private Disposable getRetryObservable() {
        return view.getRetryObservable().subscribe(s -> subscriptions.add(getNumberList()));
    }

    private Disposable getNumberList() {
        return model.isNetworkAvailable().doOnNext(isNetworkAvailable -> {
            if(!isNetworkAvailable) {
                Timber.e("No internet connection");
            }
        })

        .filter(isNetworkAvailable -> true)
        .flatMap(isAvailable -> model.provideNumberList())
        .subscribeOn(schedulers.internetScheduler())
        .observeOn(schedulers.androidThreadScheduler())
        .subscribe(numbers -> {
            this.numbers = (ArrayList<Number>) numbers;
            view.refreshAdapter(this.numbers);
        }, throwable -> {
            view.showNoConnectionDialog();
            Timber.e(throwable, throwable.getMessage());
        });
    }

    private Disposable getNumberDetails(Number number) {
        return model.isNetworkAvailable().doOnNext(isNetworkAvailable -> {
            if(!isNetworkAvailable) {
                Timber.e( "No internet connection");
            }
        })
                .filter(isNetworkAvailable -> true)
                .flatMap(isAvailable -> model.provideNumberDetailsList(number.getName()))
                .observeOn(schedulers.androidThreadScheduler())
                .subscribeOn(schedulers.internetScheduler())
                .subscribe(num -> model.goToDetails(num), throwable -> {
                    view.showNoConnectionDialog();
                    Timber.e(throwable, throwable.getMessage());
                });

    }
}
