package com.klusman.dayInfo;

public enum temperature {
	FAHRENHEIT("¼F"),
	CELSIUS("¼C");
	
	private String nameAsString;
	
	private temperature(String nameAsString){
		this.nameAsString = nameAsString;
	}
	
	@Override
	public String toString() {
		return this.nameAsString;
	}
}
