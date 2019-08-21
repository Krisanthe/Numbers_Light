package krisanthe.exercise.numberslight.api;

import java.util.List;

import io.reactivex.Observable;
import krisanthe.exercise.numberslight.models.Number;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NumberApi {

	String JSON_URL = "test/json.php";

	@GET(JSON_URL)
	Observable<List<Number>> getNumbers();

	@GET(JSON_URL)
	Observable<Number> getNumberDetails(@Query("name") String numberName);
}
