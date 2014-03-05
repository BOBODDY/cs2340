package com.so.sofinances;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		DBHandler.setPath(this.getDir("data", 0) + "");
		ObjectSet res = DBHandler.db().query(User.class);
		for (Object u : res){
			System.out.println(((User)u).getFullName());
			System.out.println("Acc: " + ((User)u).accToString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}
	
	@Override
	public void onDestroy(){
		DBHandler.db().store(UserHandler.currentUser);
		System.out.println("destroying yo");
		DBHandler.db().commit();
		DBHandler.db().close();
	}

	public void loginResponse(View view) {
	    startActivity(new Intent(this, LoginActivity.class));
	}
	
	public void registerResponse(View v) {
		startActivity(new Intent(this, RegisterActivity.class));
	}
}
