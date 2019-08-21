package krisanthe.exercise.numberslight.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Number implements Serializable {

	@Expose
	String name;

	@Expose
	String text;

	@Expose
	String image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
