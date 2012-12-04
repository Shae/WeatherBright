package com.klusman.formthings;

import com.klusman.weatherbright.R;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherDisplayLayoutLL extends LinearLayout{
	
		ImageView _image;
		TextView _weatherCond;
		TextView _date;
		TextView _high;
		TextView _low;
		TextView _windspeed;
		Context _context;
		LayoutParams lp;
		
		public WeatherDisplayLayoutLL(Context context){
			super(context);
			
			LinearLayout dayInfoLayout = new LinearLayout(_context);
			dayInfoLayout.setOrientation(LinearLayout.VERTICAL);
			lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			_context = context;	
			
			 
			ImageView theImage = new ImageView(_context);
			theImage.setImageResource(R.drawable.ic_launcher);
			_weatherCond = new TextView(_context);
			_weatherCond.setId(1);
			
			TextView theDate = new TextView(_context);
			theDate.setText("Date: ");
			_date = new TextView(_context);
			_date.setId(2);
			
			TextView theHigh = new TextView(_context);
			theHigh.setText("Temp(High): ");
			_high = new TextView(_context);
			_high.setId(3);
			
			TextView theLow = new TextView(_context);
			theLow.setText("Temp(LOW): ");
			_low = new TextView(_context);
			_low.setId(4);
			
			TextView theWindspeed = new TextView(_context);
			theWindspeed.setText("Windspeed: ");
			_windspeed = new TextView(_context);
			_windspeed.setId(5);
			
			
			// Setting up layouts and data lines
			LinearLayout wthr = new LinearLayout(_context);
			wthr.setLayoutParams(lp);
			//wthr.setOrientation(LinearLayout.HORIZONTAL);
			wthr.addView(theImage);
			wthr.addView(_weatherCond);			
			this.addView(wthr);
			
			LinearLayout date = new LinearLayout(_context);
			date.setLayoutParams(lp);
			//date.setOrientation(LinearLayout.HORIZONTAL);
			date.addView(theDate);
			date.addView(_date);
			this.addView(date);

			LinearLayout high = new LinearLayout(_context);
			high.setLayoutParams(lp);
			//high.setOrientation(LinearLayout.HORIZONTAL);
			high.addView(theHigh);
			high.addView(_high);
			this.addView(high);
			
			LinearLayout low = new LinearLayout(_context);
			low.setLayoutParams(lp);
			//low.setOrientation(LinearLayout.HORIZONTAL);
			low.addView(theLow);
			low.addView(_low);
			this.addView(low);
			
			LinearLayout wind = new LinearLayout(_context);
			wind.setLayoutParams(lp);
			wind.setOrientation(LinearLayout.HORIZONTAL);
			wind.addView(theWindspeed);
			wind.addView(_windspeed);
			this.addView(wind);
			

			
			
			

			//LinearLayout ll = com.klusman.formthings.BlankLineBorder.blankLine(_context);
			//dayInfoLayout.addView(ll);
			//this.addView(dayInfoLayout);
			
		}

}
