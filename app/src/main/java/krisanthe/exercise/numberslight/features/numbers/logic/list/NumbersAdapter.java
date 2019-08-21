package krisanthe.exercise.numberslight.features.numbers.logic.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.subjects.PublishSubject;
import krisanthe.exercise.numberslight.R;
import krisanthe.exercise.numberslight.models.Number;

public class NumbersAdapter extends RecyclerView.Adapter<NumberViewHolder> {

    PublishSubject<Integer> onClickNumber = PublishSubject.create();
    ArrayList<Number> numbers = new ArrayList<>();

    @Override
    @NonNull
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int index)  {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.i_number, viewGroup, false);
        return new NumberViewHolder(view, onClickNumber);
    }

    public Observable<Integer> getClickObservable() {
        return onClickNumber;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder viewHolder, int index) {
        viewHolder.bind(numbers.get(index));
    }

    public void refrashAdapter(ArrayList<Number> newList) {
        numbers.clear();
        numbers.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (numbers != null && numbers.size() > 0) {
            return numbers.size();
        } else {
            return 0;
        }
    }
}
