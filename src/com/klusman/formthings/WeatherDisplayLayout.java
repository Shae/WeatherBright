package com.klusman.formthings;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;

public class WeatherDisplayLayout extends GridLayout{
	
	TextView _date;
	TextView _high;
	TextView _low;
	TextView _windspeed;
	Context _context;
	
	public WeatherDisplayLayout(Context context){
		super(context);
		
		_context = context;
		
		this.setColumnCount(2);
		
		TextView theDate = new TextView(_context);
		theDate.setText("Date: ");
		_date = new TextView(_context);
		
		TextView theHigh = new TextView(_context);
		theHigh.setText("Temp(High): ");
		_high = new TextView(_context);
		
		TextView theLow = new TextView(_context);
		theLow.setText("Temp(LOW): ");
		_low = new TextView(_context);
		
		TextView theWindspeed = new TextView(_context);
		theWindspeed.setText("Windspeed: ");
		_windspeed = new TextView(_context);
		
		this.addView(theDate);
		this.addView(_date);

		this.addView(theHigh);
		this.addView(_high);
		
		this.addView(theLow);
		this.addView(_low);
		
		this.addView(theWindspeed);
		this.addView(_windspeed);
		
		
	}
}
