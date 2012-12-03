package com.klusman.webStuff;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WebConnections {
	static Boolean connection = false;
	static String connectionType = "Unavailable";
 
	public static String getConnectionType(Context context){
		netInfo(context);
		return connectionType;
	}
	
	public static Boolean getConnectionStatus(Context context){
		netInfo(context);
		return connection;
	}
	
	
	private static void netInfo(Context context){
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if(netInfo != null){
			if(netInfo.isConnectedOrConnecting()){
				connectionType = netInfo.getTypeName();
				connection = true;
			}
		}
	}
	
}
