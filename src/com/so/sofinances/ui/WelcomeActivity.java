package com.so.sofinances.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.so.sofinances.R;
import com.so.sofinances.controllers.DBHandler;

/** displays a login and register button.
 * @author kodyPC
 *
 */
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_welcome);
        DBHandler.setPath(this.getDir("data", 0) + "");
        
        /* The DBHandler.delete() was already commented out. The only thing left
         * were 2 print statements. I'll just remove this whole part
         * 
         * -Nick
         */
        //ObjectSet<User> res = DBHandler.db().query(User.class);
        //for (Object u : res) {
            //System.out.println(((User) u).getFullName());
            //System.out.println("Acc: " + ((User) u).accToString());
            //DBHandler.db().delete(u); //for clearing db on start
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }
    
    @Override
    public void onPause() {
        super.onPause();
        //System.out.println("DB Updated");
        DBHandler.update();
    }

	/**
	 * Moves to a new screen to Login.
	 * 
	 * Creates a new Intent for the Login Activity and starts 
	 * up the Activity to move to Login Screen
	 * @param view The view of the screen
	 */
    public void loginResponse(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
    
	/**
	 * Moves to a new screen to Register.
	 * 
	 * Creates a new Intent for the Register Activity and starts up
	 * the Activity to move to the Register Screen
	 * @param view The view of the screen
	 */
    public void registerResponse(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}