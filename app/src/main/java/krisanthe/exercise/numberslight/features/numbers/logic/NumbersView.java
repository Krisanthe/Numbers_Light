package krisanthe.exercise.numberslight.features.numbers.logic;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import krisanthe.exercise.numberslight.R;
import krisanthe.exercise.numberslight.features.numbers.NumbersActivity;
import krisanthe.exercise.numberslight.features.numbers.logic.list.NumbersAdapter;
import krisanthe.exercise.numberslight.models.Number;

public class NumbersView {

    @BindView(R.id.number_list)
    RecyclerView numberListView;

    View view;
    NumbersAdapter adapter;
    NumbersActivity context;
    PublishSubject<Boolean> retryObservable = PublishSubject.create();


    public NumbersView(NumbersActivity context) {
        this.context = context;
        FrameLayout parent = new FrameLayout(context.getActivity());
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context.getActivity()).inflate(R.layout.a_number_list, parent, true);
        ButterKnife.bind(this, view);

        setupAdapter(context.getActivity());
    }

    private void setupAdapter(Context context) {
        adapter = new NumbersAdapter();
        numberListView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        numberListView.setLayoutManager(mLayoutManager);
    }

    public View view() {
        return view;
    }

    public void refreshAdapter(ArrayList<Number> numbers) {

        adapter.refrashAdapter(numbers);
    }

    public void showNoConnectionDialog() {
        new AlertDialog.Builder(context.getActivity())
                .setTitle("Error")
                .setMessage("No internet connection")
                .setPositiveButton("Retry", (d, w) -> {
                    retryObservable.onNext(true);
                    d.dismiss();
                })
                .create()
                .show();
    }

    Observable<Boolean> getRetryObservable() {
        return retryObservable;
    }

    Observable<Integer> getClickObservable() {
        return adapter.getClickObservable();
    }
}
