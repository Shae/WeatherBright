package com.klusman.dayInfo;

public class dayClass implements dayInterface {

	String day;
	int high;
	int low;
	int windSpeed;
	
	
	public dayClass(String day, int high, int low, int windSpeed){
		setDay(day);
		setTempHigh(high);
		setTempLow(low);
		setWindSpeed(windSpeed);
	}
	
	
	@Override
	public boolean setDay(String date) {
		this.day = date;
		return true;
	}
	
	@Override
	public boolean setTempHigh(int intHigh) {
		this.high = intHigh;
		return true;
	}

	@Override
	public boolean setTempLow(int intLow) {
		this.low = intLow;
		return true;
	}

	@Override
	public boolean setWindSpeed(int intSpeed) {
		this.windSpeed = intSpeed;
		return true;
	}

	@Override
	public String getDay() {
		return this.day;
	}

	@Override
	public int getTempHigh() {
		return this.high;
	}

	@Override
	public int getTempLow() {
		return this.low;
	}

	@Override
	public int getWindSpeed() {
		return this.windSpeed;
	}

}
