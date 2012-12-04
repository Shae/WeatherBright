package com.klusman.formthings;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
//import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class BlankLineBorder {
	public static LinearLayout blankLine(Context context){
		
		LinearLayout ll = new LinearLayout(context);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 20);
		ll.setLayoutParams(lp);
		ll.setBackgroundColor(Color.BLACK);
		
		//TextView tv = new TextView(context);
		
		//ll.addView(tv);
		
		return ll;
	}
}
