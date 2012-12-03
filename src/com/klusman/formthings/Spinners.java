package com.klusman.formthings;

import android.content.Context;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;

public class Spinners {

	public static LinearLayout daysSpinner(Context context, String[] myArray){
		LinearLayout ll = new LinearLayout(context);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(lp);
		ll.setGravity(Gravity.CENTER_HORIZONTAL);

		
		Spinner daySpin = new Spinner(context);
		daySpin.setId(1);
		
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, myArray);
		daySpin.setAdapter(spinnerArrayAdapter);
		//String choice = spinnerArrayAdapter.getItem((daySpin.getSelectedItemPosition());
		//String choice = spinnerArrayAdapter.getItem((daySpin.getSelectedItemPosition());
		
	
		
		//Log.i("SPINNER CHOICE: ", "TEST TEST");
		ll.addView(daySpin);
		
		return ll;
	}
	
}
