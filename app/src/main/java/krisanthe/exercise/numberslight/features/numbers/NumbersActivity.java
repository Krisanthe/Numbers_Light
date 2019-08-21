package krisanthe.exercise.numberslight.features.numbers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import krisanthe.exercise.numberslight.R;
import krisanthe.exercise.numberslight.application.ApplicationController;
import krisanthe.exercise.numberslight.features.details.DetailsActivity;
import krisanthe.exercise.numberslight.features.numbers.dagger.DaggerNumbersComponent;
import krisanthe.exercise.numberslight.features.numbers.dagger.NumbersModule;
import krisanthe.exercise.numberslight.features.numbers.logic.NumbersPresenter;
import krisanthe.exercise.numberslight.features.numbers.logic.NumbersView;
import krisanthe.exercise.numberslight.models.Number;
import krisanthe.exercise.numberslight.utils.UiUtils;


public class NumbersActivity extends Fragment {

    @Inject
    NumbersPresenter presenter;

    @Inject
    NumbersView view;

    @Override
    public void onCreate(Bundle saveInstantState) {
        super.onCreate(saveInstantState);

        DaggerNumbersComponent.builder()
                .appComponent(ApplicationController.getAppComponent())
                .numbersModule(new NumbersModule(this))
                .build()
                .inject(this);

        presenter.onCreate();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view.view();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void goToDetails(Number number) {
        DetailsActivity detailsActivity = new DetailsActivity();
        Bundle bundle = new Bundle();
        bundle.putSerializable("number", number);
        detailsActivity.setArguments(bundle);

        ViewGroup detailsContainer = getActivity().findViewById(R.id.details_container);
        if(detailsContainer != null) {
            UiUtils.launchFragment(getActivity(), detailsActivity, R.id.details_container);
        } else {
            UiUtils.launchFragment(getActivity(), detailsActivity, R.id.container);
        }
    }
}
