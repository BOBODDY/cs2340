package com.so.sofinances.activities;

import com.so.sofinances.R;
import com.so.sofinances.R.id;
import com.so.sofinances.R.layout;
import com.so.sofinances.R.menu;
import com.so.sofinances.handler.LoginHandler;
import com.so.sofinances.handler.UserHandler;
import com.so.sofinances.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

    EditText unText, pwText;
    TextView display;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unText = (EditText)findViewById(R.id.login_username);
        pwText = (EditText)findViewById(R.id.login_password);
        display = (TextView) findViewById(R.id.invalidLoginTV);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
    public void onClickLogin(View view) {
        String username = unText.getText().toString();
        String password = pwText.getText().toString();
        
        User test = LoginHandler.checkLogin(username, password);
        if (test != null) {
            UserHandler.setCurrentUser(test.getUserName().toString());
            startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
            finish();
        } else {
            display.setText("Invalid login");
        }
    }
}