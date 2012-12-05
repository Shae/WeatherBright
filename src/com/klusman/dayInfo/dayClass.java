package com.klusman.dayInfo;

public class dayClass implements dayInterface {

	String day;
	String high;
	String low;
	String windSpeed;
	
	
	public dayClass(String day, String high, String low, String windSpeed){
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
	public boolean setTempHigh(String intHigh) {
		this.high = intHigh;
		return true;
	}

	@Override
	public boolean setTempLow(String intLow) {
		this.low = intLow;
		return true;
	}

	@Override
	public boolean setWindSpeed(String intSpeed) {
		this.windSpeed = intSpeed;
		return true;
	}

	@Override
	public String getDay() {
		return this.day;
	}

	@Override
	public String getTempHigh() {
		return this.high;
	}

	@Override
	public String getTempLow() {
		return this.low;
	}

	@Override
	public String getWindSpeed() {
		return this.windSpeed;
	}

}
