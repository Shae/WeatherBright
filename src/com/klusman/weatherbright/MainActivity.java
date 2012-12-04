package com.klusman.weatherbright;


import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.klusman.webStuff.WebConnections;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {


	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	String [] myArray = {"1-day Forecast","2-day forecast","3-day forecast","4-day forecast","5-day forecast"};
	Boolean connected = false;
	int arrayPosition = 0;
	String finalAreaCode = "98524";
	TextView tv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);
		ll.setBackgroundColor(0xFF00FF00);

		tv = new TextView(this);
		tv.setText("Weather Forecast");
		tv.setTextSize(30);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);

		// Detect network connection
		connected = com.klusman.webStuff.WebConnections.getConnectionStatus(this);
		if (connected){
			Log.i("NETWORK STATUS", com.klusman.webStuff.WebConnections.getConnectionType(this));
		}



		// SPINNER
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
				//tv.setText(arrayItem + " Weather Forecast");
				tv.setText((arg2 + 1) + "-Day Weather Forecast");
				arrayPosition = arg2;
				

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Log.i("ITEM SELECTED", "NO ITEM SELECTED");

			}
		});

		LinearLayout lineAndBtn = com.klusman.formthings.EditFieldPlusBtn.entryLinePlusButton(this, "Enter Zip Code", "Go");	
		Button btn = (Button) lineAndBtn.findViewById(2);
		btn.setBackgroundColor(0xFFFFFFFF);
		EditText et = (EditText) lineAndBtn.findViewById(1);
		et.setBackgroundColor(0xFFFFFF00);
		
		//  onCLICK LISTENER
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				EditText areaCode = (EditText) v.getTag();

				if(areaCode.getText().toString().compareTo("") == 0){   // Testing to see if "areaCode" is empty. (0 = empty, 1= not empty)
					Log.i("Button Clicked :", "Add something to your text field!");  //test line if EMPTY
				}else{
					Log.i("Button Clicked :",areaCode.getText().toString() );  //Text field HAS data!
					finalAreaCode = areaCode.getText().toString();
				}
				// Make the keyboard go away on button click!
				InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);   
				getDays((arrayPosition + 1));
			}
		});

		ll.addView(spinnerLayout);
		ll.addView(lineAndBtn);
		ll.addView(tv);
		setContentView(ll);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void getDays(int daySpan){
		String dayString = String.valueOf(daySpan);
		String areaCode = finalAreaCode;
				
		Log.i("DAYS TO GET", "Pull this many days: " + dayString );
		
		//String baseURL = "http://free.worldweatheronline.com/feed/weather.ashx?q=" + areaCode + "98524&format=JSON&num_of_days=" + daySpan + "&key=2a0cc91795015022122811";
		//String baseURL = "http://free.worldweatheronline.com/feed/weather.ashx";
		//String messURL = "http://free.worldweatheronline.com/feed/weather.ashx" + "?q=" + finalAreaCode + "&format=json&num_of_days=" + daySpan + "&key=2a0cc91795015022122811";
		String messURL = "http://free.worldweatheronline.com/feed/weather.ashx?q=" + areaCode + "&format=json&num_of_days=" + daySpan + "&key=2a0cc91795015022122811";
		String qs;
		
	try{
			qs = URLEncoder.encode(messURL, "UTF-8");
			Log.i("URL to CALL", qs);  // WORKS!!!!!!
		} catch(Exception e){
			Log.e("BAD URL", "Encoding Problem");
			qs = "";
		}
		
		URL finalURL;
		
	try{
			finalURL = new URL(messURL);
			QuoteRequest qr = new QuoteRequest();
			qr.execute(finalURL);

		} catch(MalformedURLException e){
			Log.e("BAD URL", "URL Problem Final");
		}
	
	}

	private class QuoteRequest extends AsyncTask<URL, Void, String>{

		@Override
		protected String doInBackground(URL... urls) {
			String response = "";
			for (URL url: urls){
				response = WebConnections.getURLStringResponse(url);
			}
			return response;
		}
		
		@Override
		protected void onPostExecute(String result){
			Log.i("URL RESPONSE:", result);
			
		}
	}
	
}
