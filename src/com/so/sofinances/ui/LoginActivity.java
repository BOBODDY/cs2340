package com.so.sofinances.ui;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jasypt.util.text.BasicTextEncryptor;

import com.db4o.ObjectSet;
import com.so.sofinances.R;
import com.so.sofinances.controllers.DBHandler;
import com.so.sofinances.controllers.LoginHandler;
import com.so.sofinances.controllers.UserHandler;
import com.so.sofinances.exceptions.InvalidInputException;
import com.so.sofinances.exceptions.PasswordMismatchException;
import com.so.sofinances.model.User;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/** the activity which facilitates login.
 * @author kodyPC
 *
 */
public class LoginActivity extends Activity {

	/**
	 * username entry.
	 */
	private EditText unText;
	/**
	 * password entry.
	 */
	private EditText pwText;
	/**
	 * textview used to display messages to the user.
	 */

	@Override
	protected void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		setContentView(R.layout.activity_login);
		unText = (EditText) findViewById(R.id.login_username);
		pwText = (EditText) findViewById(R.id.login_password);
		//display = (TextView) findViewById(R.id.invalidLoginTV);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Checks to see if the correct credentials for a User is verified or not.
	 * If it the User and Password used to login are checked and verified in the 
	 * database, then the UserHandler sets the currentUser to the User used to login,
	 * and a new Intent is made to move to the UserHomeActivity screen. If the User
	 * and Password given don't match, then an "Invalid login" message is displayed.
	 * 
	 * @param view The View of the current window
	 */
	public void onClickLogin(View view) {
		String username = unText.getText().toString();
		String password = pwText.getText().toString();
		try {
			User test = LoginHandler.checkLogin(username, password);
			UserHandler.setCurrentUser(test.getUserName().toString());
			startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
			finish();
		} catch (PasswordMismatchException e) {
			Toast t = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
			t.show();
		} catch (InvalidInputException e) {
			Toast t = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
			t.show();
		}

	}

	public void onClickLostPassword(View view) {
		String username = unText.getText().toString();
		try {
			String[] emailAndPW = LoginHandler.getLostPassword(username);
			String email = emailAndPW[0];
			String pw = emailAndPW[1];
			System.out.println("account found");
			sendMail(email, 
					"Stratton Oakmont Finances Lost Password", 
					"The password for user " + username + " is ''" + pw + "''");
		} catch (InvalidInputException e) {
			Toast t = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
			t.show();
		}
	}

	private void sendMail(String email, String subject, String body){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("SOFinances.Lost.PW","cs2340group46");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("SOFinances.Lost.PW@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(body);
			new SendMailTask().execute(message);

			System.out.println("Done");

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private class SendMailTask extends AsyncTask<Message, Void, Void> {
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait", "Sending mail", true, false);
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			progressDialog.dismiss();
		}

		@Override
		protected Void doInBackground(Message... messages) {
			try {
				Transport.send(messages[0]);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return null;
		}
	}


}
