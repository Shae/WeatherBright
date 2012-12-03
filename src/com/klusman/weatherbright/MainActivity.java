package com.klusman.weatherbright;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class MainActivity extends Activity {
	
	
	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	String [] myArray = {"1-day Forecast","2-day forecast","3-day forecast","4-day forecast","5-day forecast"};
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    	ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        ll.setBackgroundColor(0xFF00FF00);
        
        
        LinearLayout spinnerLayout = com.klusman.formthings.Spinners.daysSpinner(this, myArray);
        spinnerLayout.findViewById(1).setBackgroundColor(0xFFFFFFFF);
        Spinner spin = (Spinner) spinnerLayout.findViewById(1);
        
        spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// Get the item Selected and Print out in log
				String arrayItem = myArray[arg2];
				Log.i("ITEM SELECTED", arrayItem);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
       
	
        
        
        
        LinearLayout lineAndBtn = com.klusman.formthings.EditFieldPlusBtn.entryLinePlusButton(this, "Enter Zip Code", "Go");	
        Button btn = (Button) lineAndBtn.findViewById(2);
        btn.setBackgroundColor(0xFFFFFFFF);
        EditText et = (EditText) lineAndBtn.findViewById(1);
        et.setBackgroundColor(0xFFFFFF00);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText areaCode = (EditText) v.getTag();
				
				if(areaCode.getText().toString().compareTo("") == 0){   // Testing to see if "areaCode" is empty. (0 = empty, 1= not empty)
						Log.i("Button Clicked :", "Add something to your text field!");  //test line if EMPTY
					}else{
						Log.i("Button Clicked :",areaCode.getText().toString() );  //Text field HAS data!
					}
				// Make the keyboard go away on button click!
	        	InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);   
			}
		});
        
        ll.addView(spinnerLayout);
        ll.addView(lineAndBtn);
        
        setContentView(ll);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
