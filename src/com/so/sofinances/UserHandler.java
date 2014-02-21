package com.so.sofinances;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class UserHandler {
	
	String separator = "-_-";
	String newline = "\n";
	
	static ArrayList<User> users;
	
	Context context;

	public UserHandler(Context c) {
		context = c;
	}
	
	private void readUsers() {
		ArrayList<User> tmp = new ArrayList<User>();
		
		try {
		    BufferedReader inputReader = new BufferedReader(new InputStreamReader(context.openFileInput(Constants.USERS_FILE)));
		    String inputString;
		    StringBuffer stringBuffer = new StringBuffer();                
		    while ((inputString = inputReader.readLine()) != null) {
		        stringBuffer.append(inputString + "\n");
		    }

			Log.d(Constants.TAG, "Read file: " + stringBuffer.toString());
		    String[] contents = stringBuffer.toString().split(newline);
		    
			int size = contents.length;
			for(int i=0;i<size;i++) {
				//Each line should be a different user
				String[] parsedAgain = contents[i].split(separator);
				if(parsedAgain.length >= 2) { // Number can be increased if more information needs to be stored
					String username = parsedAgain[0];
					String password = parsedAgain[1];
					tmp.add(new User(username, password));
					Log.d(Constants.TAG, "Added User with name: " + username + " and password: " + password);
				}
			}
		} catch(IOException ioe) {
			Log.e(Constants.TAG, "weird", ioe);
			Toast.makeText(context, "Got an IOException while reading users", Toast.LENGTH_SHORT).show();
		}
		
		users = tmp;
	}
	
	private void writeUsers() {
		FileOutputStream fos;
		
		try {
			fos = context.openFileOutput(Constants.USERS_FILE, Context.MODE_PRIVATE);
			
			String contents = "";
			if(users != null) {
				int size = users.size();
				for(int i=0;i<size;i++) {
					User u = users.get(i);
					contents += u.getUsername() + separator + u.getPassword() + newline;
				}
			}
			
			fos.write(contents.getBytes());
			fos.close();
		} catch(Exception e) {
			Log.d(Constants.TAG, "this might be ok", e);
			Toast.makeText(context, "Got an Exception while writing users", Toast.LENGTH_SHORT).show();
		}
	}
	
	private User findUser(String username) {
		User u = null;
		if(users != null) {
			int size = users.size();
			for(int i=0;i<size;i++) {
				User tmp = users.get(i);
				
				if(tmp.getUsername().equals(username)) {
					u = tmp;
					break;
				}
			}
		}
		
		return u;
	}
	
	public boolean registerUser(String username, String password) {
		if ((username.length() < 1) || (password.length() < 1)) return false;
		if(findUser(username)== null) { // TODO: return some sort of code to indicate user already exists
			if(users == null) {
				Log.i(Constants.TAG, "users was null, creating a new one");
				users = new ArrayList<User>();
			}
			
			users.add(new User(username, password));
		} else {
			Log.i(Constants.TAG, "User already exists");
			return false;
		}
		
		writeUsers();
		return true;
	}
	
	public boolean checkLogin(String username, String password) {
		boolean res = false;
		
		User tmpUser = new User(username, password);
		if(tmpUser.isDefault()) {
			return true;
		}
		
		readUsers();
		
		User u = findUser(username);
		if(u != null) {
			res = u.getPassword().equals(password);
		}
		
		return res;
	}

}
