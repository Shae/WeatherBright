package com.klusman.weatherbright;


import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
//import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.klusman.dayInfo.ReadStuff;
import com.klusman.dayInfo.SaveStuff;
import com.klusman.formthings.WeatherDisplayLayoutLL;
import com.klusman.webStuff.WebConnections;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.JsonReader;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ScrollView sv;
	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	String [] myArray = {"1-Day Forecast","2-Day forecast","3-Day forecast","4-Day forecast","5-Day forecast"};
	Boolean connected = false;
	int arrayPosition = 0;
	String finalAreaCode = "98524";
	TextView tv;
	WeatherDisplayLayoutLL _weatherLayout1;
	LinearLayout W;
	JSONArray resultsArrayW;
	Context _context = this;
	JSONObject newObj;
	LinearLayout listHold;
	HashMap<String,	String> _history;

	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sv = new ScrollView(this);
		ll = new LinearLayout(this);
		_history = getStoredData();
		Log.i("HISTORY READ", _history.toString());
		
		ll.setOrientation(LinearLayout.VERTICAL);
		lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		ll.setLayoutParams(lp);

		ll.setBackgroundColor(0xFF00FF00);

		tv = new TextView(this);
		tv.setText("Weather Forecast");
		tv.setTextSize(30);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);

		
		connected = com.klusman.webStuff.WebConnections.getConnectionStatus(this);
		// Detect network connection

		if (connected  == true){
			Log.i("NETWORK STATUS", "CONNECTED TO WEB");
			Log.i("NETWORK STATUS", com.klusman.webStuff.WebConnections.getConnectionType(this));


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
					tv.setText((arg2 + 1) + "-Day Weather Forecast");
					arrayPosition = arg2;
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					Log.i("ITEM SELECTED", "NO ITEM SELECTED");

				}
			});

			LinearLayout lineAndBtn = com.klusman.formthings.EditFieldPlusBtn.entryLinePlusButton(this, "Enter Zip Code", "Go");	
			Button btn = (Button) lineAndBtn.findViewById(2);
			btn.setBackgroundColor(0xFFFFFFFF);
			EditText et = (EditText) lineAndBtn.findViewById(1);
			et.setBackgroundColor(0xFFFFFF00);
			lineAndBtn.setGravity(Gravity.CENTER_HORIZONTAL);

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

						//FIND A VIEW BY TAG and REMOVE IT before the next Import of DATA
						if(ll.findViewById(40) != null){
							ll.removeView(ll.findViewById(40));
							ll.removeView(ll.findViewById(40));
							ll.removeView(ll.findViewById(40));
							ll.removeView(ll.findViewById(40));
							ll.removeView(ll.findViewById(40));
						}


					}
					// Make the keyboard go away on button click!
					InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);   
					getDays((arrayPosition + 1));
				}
			});
			
			LinearLayout b1 = com.klusman.formthings.BlankLineBorder.blankLine(this);
			LinearLayout b3 = com.klusman.formthings.BlankLineBorder.blankLine(this);
			ll.addView(spinnerLayout); // day spinner
			ll.addView(b1); //blank line
			ll.addView(lineAndBtn); // zip code and Btn
			ll.addView(b3);//blank line
			ll.addView(tv); // forecast day title

			
// If No Connection Detected			
		//}else{ 
		}else{
			Log.i("CONNECTION", "NONE FOUND! Checking for Search History");
			File file = new File("saveDataObj");
			
			
			if(file == null){
				Log.i("FILE SEARCH", "NO DATA FOUND!");
				TextView tv = new TextView(this);
				tv.setText("\r\n" + "NO Internet Connection" + "\r\n" + "and" + "\r\n" +"NO Previous History to Pull From." + "\r\n" + "\r\n" + "Please Find an Internet Connection to Establish a Search History." + "\r\n");
				tv.setGravity(Gravity.CENTER_HORIZONTAL);
				tv.setTextSize(25);
				ll.addView(tv);
				
			}else{
				TextView tv = new TextView(this);
				tv.setText("No Network Connection." + "\r\n" + "\r\n" + "\r\n");
				tv.setTextSize(30);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);
				ll.addView(tv);
				Button btn = new Button(this);
				btn.setText("Use Last Pull");
				btn.setTextSize(24);

				btn.setId(2);
				btn.setGravity(Gravity.CENTER_HORIZONTAL);
				ll.addView(btn);
				
				btn.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						try{
							
							String st = _history.get("WeatherSave");
							JSONObject js = new JSONObject(st);
							Log.i("JSON OBJECT", "WORKED!");
							resultsArrayW = js.getJSONArray("weather");
							lineBuild(_context);
						}catch(JSONException e){
							e.printStackTrace();
						}
						
						
						
					}
				});

				Log.i("FILE SEARCH", "DATA HAS BEEN FOUND!");
			
	/*			
				
				try{
					//JSONObject json = new JSONObject(myStr);
					//JSONObject results = json.getJSONObject("data");
					resultsArrayW = myStr.getJSONArray("weather");
					int arrayLength = resultsArrayW.length();
					String bla = String.valueOf(arrayLength);
					
					Log.i("TEST", "resultsArrayLength" + bla );
					
					
				}catch(JSONException e){
					Log.e("ERROR","Failed to read saved data");
				}
				*/
				//TextView tv = new TextView(this);
				//String hashed = _history.toString();
				//tv.setText(hashed + "\r\n" + "\r\n" + "   - I GOT IT THIS FAR.  MUST SAVE FOR GITHUB PUSH- ");
				
				//ll.addView(tv);
				
			}
			
			
		
		}
			
		

		LinearLayout b2 = com.klusman.formthings.BlankLineBorder.blankLine(this);

		ll.addView(b2);//blank line


		sv.addView(ll);
		setContentView(sv);

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

	private void lineBuild (Context context){

		for(int i=0;i < (resultsArrayW.length()) ;i++){
			
			LinearLayout myLL = new com.klusman.formthings.WeatherDisplayLayoutLL(_context);
			TextView myDate = (TextView) myLL.findViewById(2);
			TextView myHigh = (TextView) myLL.findViewById(3);
			TextView myLow = (TextView) myLL.findViewById(4);
			TextView myWind = (TextView) myLL.findViewById(5);


			try{
				newObj = resultsArrayW.getJSONObject(i);
				Log.i("TEAR IT UP", newObj.getString("date"));
				
				String DATE = "Date: " + newObj.getString("date");
				myDate.setText(DATE);
				
				String MAX = "Temp(High): " + newObj.getString("tempMaxF");
				myHigh.setText(MAX);
				
				String MIN = "Temp(Low): " + newObj.getString("tempMinF");
				myLow.setText(MIN);
				
				String WIND = "Windspeed: " + newObj.getString("windspeedMiles");
				myWind.setText(WIND);
				
			}catch(JSONException e){
				Log.e("onClick JSON", "failure");
			}

			myLL.setBackgroundColor(0xFF00FFFF);
			myLL.setId(40);

			ll.addView(myLL);
			Log.i("TEST", "loop");
		}

	}
	
	
	
	@SuppressWarnings("unchecked")
	private HashMap<String, String> getStoredData(){
		Object stored = ReadStuff.readObjectFile(_context, "saveDataObj", false);
		
		HashMap<String, String> myStoredData;
		if(stored == null){
			Log.i("READ DATA", "NO PAST DATA SAVED");
			myStoredData = new HashMap<String, String>();
		}else{
			myStoredData = (HashMap<String, String>) stored;
		}
		return myStoredData;
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

			try{
				JSONObject json = new JSONObject(result);
				JSONObject results = json.getJSONObject("data");
				resultsArrayW = results.getJSONArray("weather");
				int arrayLength = resultsArrayW.length();

				if(resultsArrayW == null){
					Log.i("JSON GET OBJ", "NOT VALID");
					Toast toast = Toast.makeText(_context, "GET JSON FAILED", Toast.LENGTH_SHORT);
					toast.show();

				}else{
					Toast toast = Toast.makeText(_context, "REQUEST RECEIVED!" + String.valueOf(arrayLength), Toast.LENGTH_SHORT);
					toast.show();
					lineBuild(_context);
					_history.put("WeatherSave", results.toString());
					SaveStuff.storeObjectFile(_context, "saveDataObj", _history, false);  // save local as JSON obj string
					//SaveStuff.storeStringFile(_context, "saveDataString", results.toString(), false);
				}
			}catch (JSONException e){
				Log.e("JSON ERROR", "JSON ERROR");
			}

		}
	}

}
