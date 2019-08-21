package krisanthe.exercise.numberslight.features;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import krisanthe.exercise.numberslight.R;
import krisanthe.exercise.numberslight.features.details.DetailsActivity;
import krisanthe.exercise.numberslight.features.numbers.NumbersActivity;
import krisanthe.exercise.numberslight.utils.UiUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        if (savedInstanceState == null) {
            UiUtils.launchFragment(this, new NumbersActivity(), R.id.container);
        } else {
            setProperFragment();
        }
    }

    private void setProperFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        NumbersActivity numbersActivity = (NumbersActivity) supportFragmentManager.findFragmentByTag(NumbersActivity.class.getName());
        DetailsActivity detailsActivity = (DetailsActivity) supportFragmentManager.findFragmentByTag(DetailsActivity.class.getName());
        ViewGroup detailsContainer = findViewById(R.id.details_container);

        if(detailsContainer != null) {
            if (numbersActivity == null) {
                numbersActivity = new NumbersActivity();
            }
            UiUtils.replaceFragment(this, numbersActivity, R.id.container);
        }
        if (detailsActivity != null) {
            UiUtils.replaceFragment(this, detailsActivity, UiUtils.isPortrait(this) ? R.id.container: R.id.details_container);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            System.exit(1);
        }
    }
}

