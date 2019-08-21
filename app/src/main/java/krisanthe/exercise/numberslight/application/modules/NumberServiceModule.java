package krisanthe.exercise.numberslight.application.modules;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import krisanthe.exercise.numberslight.api.NumberApi;
import krisanthe.exercise.numberslight.application.AppScope;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NumberServiceModule {

	private static final String BASE_URL = "http://dev.tapptic.com/";

	@AppScope
	@Provides
	NumberApi provideNumberApi(OkHttpClient client, GsonConverterFactory gson, RxJava2CallAdapterFactory adapterFactory) {
		Retrofit retrofit = new Retrofit.Builder()
			.client(client)
			.baseUrl(BASE_URL)
			.addConverterFactory(gson)
			.addCallAdapterFactory(adapterFactory)
			.build();

		return retrofit.create(NumberApi.class);
	}



}
