package com.gorro.bicidf;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class UserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		
		ActionBar bar = getActionBar();
		int morado = Color.rgb(56, 11, 97);
		bar.setBackgroundDrawable(new ColorDrawable(morado));
	
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
			Toast.makeText(getApplicationContext(), "Editar datos", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

}
