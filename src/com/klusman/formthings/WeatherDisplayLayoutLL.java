package com.klusman.formthings;


import com.klusman.dayInfo.dayInterface;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherDisplayLayoutLL extends LinearLayout implements dayInterface{
	
		ImageView _image;
		TextView _date;
		TextView _high;
		TextView _low;
		TextView _windspeed;
		Context _context;
		String aDate = "xx-xx-xxxx";
		String aHigh = "00";
		String aLow = "00";
		String aWind = "NONE";
		LayoutParams lp;
		
		public WeatherDisplayLayoutLL(Context context){
			super(context);
			
			//LinearLayout dayInfoLayout = new LinearLayout(_context);
			this.setOrientation(LinearLayout.VERTICAL);
			lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			this.setLayoutParams(lp);
			_context = context;	
			
/*
			// IMAGE

			_image.setImageResource(R.drawable.ic_launcher);
			_image.setId(1);
			this.addView(_image);
*/	
			// DATE
			_date = new TextView(_context);
			_date.setId(2);
			_date.setText("Date: " + aDate);
			this.addView(_date);
			

			_high = new TextView(_context);
			_high.setId(3);
			_high.setText("Temp(High): " + aHigh);
			this.addView(_high);
			

			_low = new TextView(_context);
			_low.setId(4);
			_low.setText("Temp(Low): " + aLow);
			this.addView(_low);
		

			_windspeed = new TextView(_context);
			_windspeed.setId(5);
			_windspeed.setText("WindSpeed: " + aWind);
			this.addView(_windspeed);
			


			LinearLayout ll = com.klusman.formthings.BlankLineBorder.blankLine(_context);
			this.addView(ll);
			
		}

		@Override
		public boolean setDay(String date) {
			this.aDate = date;
			return true;
		}
		
		@Override
		public boolean setTempHigh(String intHigh) {
			this.aHigh = intHigh;
			return true;
		}

		@Override
		public boolean setTempLow(String intLow) {
			this.aLow = intLow;
			return true;
		}

		@Override
		public boolean setWindSpeed(String intSpeed) {
			this.aWind = intSpeed;
			return true;
		}

		@Override
		public String getDay() {
			return this.aDate;
		}

		@Override
		public String getTempHigh() {
			return this.aHigh;
		}

		@Override
		public String getTempLow() {
			return this.aLow;
		}

		@Override
		public String getWindSpeed() {
			return this.aWind;
		}

}
