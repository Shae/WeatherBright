package com.klusman.dayInfo;

public interface dayInterface {
	
	// Set Day
	public boolean setDay(String date);
	// Set Temp High
	public boolean setTempHigh(String intHigh);
	// Set Temp Low
	public boolean setTempLow(String intLow);
	// Set Wind Speed
	public boolean setWindSpeed(String intSpeed);
	
	// Get Day
	public String getDay();
	// Get Temp High
	public String getTempHigh();
	// Get Temp Low
	public String getTempLow();
	// Get Wind Speed
	public String getWindSpeed();

}
