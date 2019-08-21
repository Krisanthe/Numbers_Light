package krisanthe.exercise.numberslight.application.modules;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;

import krisanthe.exercise.numberslight.application.AppScope;
import krisanthe.exercise.numberslight.utils.AppSchedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class ConnectionModule {

    @AppScope
    @Provides
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
            Timber.tag("OkHttp: ");
            Timber.i(message);
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @AppScope
    @Provides
    Cache provideCache(Context context) {
        return new Cache(context.getFilesDir(), 10 * 10 * 1000);
    }

    @AppScope
    @Provides
    OkHttpClient provideHttpClient(HttpLoggingInterceptor interceptor, Cache cache) {
        return new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @AppScope
    @Provides
    GsonConverterFactory provideGsonFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    AppSchedulers provideScheduler() {
        return new AppSchedulers();
    }

    @AppScope
    @Provides
    RxJava2CallAdapterFactory provideAdapterFactory(AppSchedulers scheduler) {
        return RxJava2CallAdapterFactory.createWithScheduler(scheduler.internetScheduler());
    }
}
