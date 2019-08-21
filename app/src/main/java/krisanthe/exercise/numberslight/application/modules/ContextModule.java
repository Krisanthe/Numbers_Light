package krisanthe.exercise.numberslight.application.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import krisanthe.exercise.numberslight.application.AppScope;

@Module
public class ContextModule {

	private final Context context;

	public ContextModule(Context context) {
		this.context = context;
	}

	@AppScope
	@Provides
	Context providesContext() {
		return context;
	}
}
