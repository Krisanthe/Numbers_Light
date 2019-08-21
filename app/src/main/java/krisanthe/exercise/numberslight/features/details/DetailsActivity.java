package krisanthe.exercise.numberslight.features.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import krisanthe.exercise.numberslight.features.details.dagger.DaggerDetailsComponent;
import krisanthe.exercise.numberslight.features.details.dagger.DetailsModule;
import krisanthe.exercise.numberslight.features.details.logic.DetailsView;
import krisanthe.exercise.numberslight.models.Number;

public class DetailsActivity extends Fragment {

    @Inject
    DetailsView view;

    @Override
    public void onCreate(Bundle saveInstantState) {
        super.onCreate(saveInstantState);

        Bundle bundle = getArguments();
        Number number= (Number) bundle.getSerializable("number");

        DaggerDetailsComponent.builder()
                .detailsModule(new DetailsModule(this, number))
                .build().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view.view();
    }
}
