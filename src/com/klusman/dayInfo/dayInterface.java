package com.klusman.dayInfo;

public interface dayInterface {
	
	// Set Day
	public boolean setDay(String date);
	// Set Temp High
	public boolean setTempHigh(int intHigh);
	// Set Temp Low
	public boolean setTempLow(int intLow);
	// Set Wind Speed
	public boolean setWindSpeed(int intSpeed);
	
	// Get Day
	public String getDay();
	// Get Temp High
	public int getTempHigh();
	// Get Temp Low
	public int getTempLow();
	// Get Wind Speed
	public int getWindSpeed();

}
