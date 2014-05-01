package com.gorro.bicidf;

import controladores.UserSingleton;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UserActivity extends Activity {
	
	TextView txtUsername, txtDomicilio, txtEdad, txtSangre, txtUserNum;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		
		ActionBar bar = getActionBar();
		int morado = Color.rgb(56, 11, 97);
		bar.setBackgroundDrawable(new ColorDrawable(morado));
		
		txtUsername = (TextView) findViewById(R.id.txtNombreUsr);
		txtDomicilio = (TextView) findViewById(R.id.txtDomicilio);
		txtEdad = (TextView) findViewById(R.id.txtEdad);
		txtSangre = (TextView) findViewById(R.id.txtSangre);
		txtUserNum = (TextView) findViewById(R.id.txtNumEmerg);
		
		if (UserSingleton.getNombre() != null) {
			txtUsername.setText(UserSingleton.getNombre());
			txtDomicilio.setText(UserSingleton.getDomicilio());
			txtEdad.setText(UserSingleton.getEdad());
			txtSangre.setText(UserSingleton.getSangre());
			txtUserNum.setText(UserSingleton.getNumEmer());
		}
	
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (UserSingleton.getNombre() != null) {
			txtUsername.setText(UserSingleton.getNombre());
			txtDomicilio.setText(UserSingleton.getDomicilio());
			txtEdad.setText(UserSingleton.getEdad());
			txtSangre.setText(UserSingleton.getSangre());
			txtUserNum.setText(UserSingleton.getNumEmer());
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_edit:
			Intent intent = new Intent(context, EditUser.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

}
