package krisanthe.exercise.numberslight.utils;

import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AppSchedulers {

	public Scheduler internetScheduler() {
		return Schedulers.from(Executors.newCachedThreadPool());
	}

	public Scheduler backgroudScheduler() {
		return Schedulers.from(Executors.newCachedThreadPool());
	}

	public Scheduler ioScheduler() {
		return Schedulers.io();
	}

	public Scheduler computationScheduler() {
		return Schedulers.computation();
	}

	public Scheduler androidThreadScheduler() {
		return AndroidSchedulers.mainThread();
	}
}

