package krisanthe.exercise.numberslight.features.details.logic;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import krisanthe.exercise.numberslight.R;
import krisanthe.exercise.numberslight.features.details.DetailsActivity;
import krisanthe.exercise.numberslight.models.Number;

public class DetailsView {

    @BindView(R.id.iv_number_image)
    ImageView numberImage;

    @BindView(R.id.tv_number_name)
    TextView numberName;

    @BindView(R.id.tv_number_text)
    TextView numberText;

    View view;

    public DetailsView(DetailsActivity context, Number number) {
        FrameLayout layout = new FrameLayout(context.getActivity());
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        view = LayoutInflater.from(context.getActivity()).inflate(R.layout.a_details,layout,true);
        ButterKnife.bind(this, view);

        Picasso.get().load(number.getImage()).into(numberImage);
        numberName.setText(TextUtils.isEmpty(number.getName()) ? "no name" : number.getName());
        numberText.setText(TextUtils.isEmpty(number.getText()) ? "no text" : number.getText());
    }

    public View view()
    {
        return view;
    }
}
