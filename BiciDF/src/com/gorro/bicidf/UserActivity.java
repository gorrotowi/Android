package com.gorro.bicidf;

import controladores.UserSingleton;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class UserActivity extends Activity {

	TextView txtUsername, txtDomicilio, txtEdad, txtSangre, txtUserNum;
	ImageView userimg;
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

		userimg = (ImageView) findViewById(R.id.UserImg);

		if (UserSingleton.getNombre() != null) {
			txtUsername.setText(UserSingleton.getNombre());
			txtDomicilio.setText(UserSingleton.getDomicilio());
			txtEdad.setText(UserSingleton.getEdad());
			txtSangre.setText(UserSingleton.getSangre());
			txtUserNum.setText(UserSingleton.getNumEmer());
		}

		userimg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				abrirCamara();
			}
		});

	}

	protected void abrirCamara() {
		Intent intent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 0);
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		try {
			Log.d("dime que tiene data", data.toString());
			Bitmap bm = (Bitmap) data.getExtras().get("data");
			Log.d("data",data.getDataString());
			userimg.setImageBitmap(bm);
			userimg.getLayoutParams().height = 100;
			userimg.getLayoutParams().width = 100;
			
		} catch (Exception e) {
			Log.e("errorcamera", e.toString());
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
