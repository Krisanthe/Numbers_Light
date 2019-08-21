package krisanthe.exercise.numberslight.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import krisanthe.exercise.numberslight.R;

public class UiUtils {

    public static void launchFragment(Context context, Fragment fragmentToLaunch, int containerID) {
        FragmentManager supportFragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        supportFragmentManager
                .beginTransaction()
                .replace(containerID, fragmentToLaunch, fragmentToLaunch.getClass().getName())
                .addToBackStack(fragmentToLaunch.getClass().getName())
                .commitAllowingStateLoss();
    }

    public static void replaceFragment(Context context, Fragment fragmentToReplace, int containerID) {
        FragmentManager supportFragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        supportFragmentManager.popBackStackImmediate(fragmentToReplace.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        supportFragmentManager.executePendingTransactions();
        supportFragmentManager
                .beginTransaction()
                .remove(fragmentToReplace)
                .commit();
        launchFragment(context, fragmentToReplace, containerID);
    }

    public static boolean isPortrait(Context context) {
        return context.getResources().getBoolean(R.bool.is_portrait);
    }
}
