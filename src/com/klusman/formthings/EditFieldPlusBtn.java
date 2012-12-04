package com.klusman.formthings;

import android.content.Context;
import android.content.res.Configuration;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class EditFieldPlusBtn {
	
	//  BUILD EditText and Button inLine Template.
	public static LinearLayout entryLinePlusButton(Context context, String hint, String buttonText){
		LinearLayout ll = new LinearLayout(context);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(lp);
		
		EditText et = new EditText(context);
		lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		et.setHint(hint);
		et.setLayoutParams(lp);
		et.setTextSize(25);


		// Set EditText Keyboard Type - Number Pad
		et.setRawInputType(Configuration.KEYBOARD_12KEY);
		
		// Set MAX entry length!
		int maxLength = 5;
		InputFilter[] FilterArray = new InputFilter[1];
		FilterArray[0] = new InputFilter.LengthFilter(maxLength);
		et.setFilters(FilterArray);
		// Set edit Text ID
		et.setId(1);
		
		Button btn = new Button(context);
		btn.setText(buttonText);
		btn.setTextSize(24);
		btn.setId(2);
		btn.setTag(et);
		
		ll.addView(et);
		ll.addView(btn);
		
		return ll;
		
	}
	
	// BUILD TextView template
	public static LinearLayout lineOfText(Context context, String stringIn){
		LinearLayout ll = new LinearLayout(context);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(lp);
		
		TextView tv = new TextView(context);
		lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f);
		tv.setText(stringIn);
		
		ll.addView(tv);
		
		return ll;
	}
	

}
