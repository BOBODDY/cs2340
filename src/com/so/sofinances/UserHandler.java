package com.so.sofinances;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.db4o.ObjectSet;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class UserHandler {
	private static String username;
	
	public static User getCU(){
		User temp = new User();
		temp.setUserName(username);
		ObjectSet result = DBHandler.db().queryByExample(temp);
		return (User) result.next();
	}
	
	public static String getCurrentUsername(){
		return username;
	}
	
	public static void setCurrentUsername(String uN){
		username = uN;
	}
}
