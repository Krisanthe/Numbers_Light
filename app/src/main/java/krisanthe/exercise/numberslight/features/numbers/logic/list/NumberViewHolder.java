package krisanthe.exercise.numberslight.features.numbers.logic.list;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;
import krisanthe.exercise.numberslight.models.Number;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import krisanthe.exercise.numberslight.R;

public class NumberViewHolder extends RecyclerView.ViewHolder {

    View view;

    @BindView(R.id.tv_number_name)
    TextView numberName;

    @BindView(R.id.iv_number_image)
    ImageView numberImage;

    public NumberViewHolder(@NonNull View item, PublishSubject<Integer> subject) {
        super(item);
        this.view = item;

        ButterKnife.bind(this, view);
        view.setOnClickListener(v -> subject.onNext(getAdapterPosition()));

    }
    void bind(Number number) {
        Picasso.get().load(number.getImage()).into(numberImage);
        numberName.setText(TextUtils.isEmpty(number.getName()) ? "No name provided" : number.getName());


    }
}
