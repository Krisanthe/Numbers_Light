package krisanthe.exercise.numberslight.features.numbers.logic

import io.reactivex.Observable
import krisanthe.exercise.numberslight.api.NumberApi
import krisanthe.exercise.numberslight.features.numbers.NumbersActivity
import krisanthe.exercise.numberslight.models.Number
import krisanthe.exercise.numberslight.utils.ConnectionUtils

class NumbersModel(var context: NumbersActivity, var api: NumberApi) {

    fun isNetworkAvailable(): Observable<Boolean> {
        return ConnectionUtils.isNetworkAvailableObservable(context.activity)
    }

    fun provideNumberList(): Observable<List<Number>> {
        return api.numbers
    }

    fun provideNumberDetailsList(numberName: String): Observable<Number> {
        return api.getNumberDetails(numberName)
    }

    fun goToDetails(number: Number) {
        context.goToDetails(number)
    }
}
