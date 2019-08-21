package krisanthe.exercise.numberslight.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.Observable;

public class ConnectionUtils {

	public static Observable<Boolean> isNetworkAvailableObservable(Activity context) {
		return Observable.just(isNetworkAvailable(context));
	}

	private static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager =
			(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
