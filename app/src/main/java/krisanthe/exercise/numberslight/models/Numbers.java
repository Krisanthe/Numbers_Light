package krisanthe.exercise.numberslight.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Numbers {

	@Expose
	private List<Number> numbers;

	public List<Number> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Number> numbers) {
		this.numbers = numbers;
	}
}
