package com.rathi.menuplanner.sqlitedatabase;

public class MenuItem {
	private String id;

	private String date;
	private String day;
	private String breakfast;
	private String lunch;
	private String dinner;

	public MenuItem(){

	}

	public MenuItem(String id, String day, String date,  String breakfast, String lunch, String dinner) {
		super();
		this.id = id;
		this.date = date;
		this.day = day;
		this.breakfast = breakfast;
		this.lunch=lunch;
		this.dinner=dinner;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

	public String getDinner() {
		return dinner;
	}

	public void setDinner(String dinner) {
		this.dinner = dinner;
	}




}
