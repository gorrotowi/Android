package com.gorro.bicidf;

import controladores.UserSingleton;
import android.app.Activity;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditUser extends Activity {
	
	EditText domicilio, nombre, edad, tipoSangre, numEmer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_user);
		ActionBar bar = getActionBar();
		int morado = Color.rgb(56, 11, 97);
		bar.setBackgroundDrawable(new ColorDrawable(morado));
		
		nombre = (EditText) findViewById(R.id.edtNombre);
		domicilio = (EditText) findViewById(R.id.edtDomicilio);
		edad = (EditText) findViewById(R.id.edtEdad);
		tipoSangre = (EditText) findViewById(R.id.edtSangre);
		numEmer = (EditText) findViewById(R.id.edtNumEme);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_user, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_save:
			
			guardarDatos();
			
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	private void guardarDatos() {
		UserSingleton.setNombre(nombre.getText().toString());
		UserSingleton.setDomicilio(domicilio.getText().toString());
		UserSingleton.setEdad(edad.getText().toString());
		UserSingleton.setSangre(tipoSangre.getText().toString());
		UserSingleton.setNumEmer(numEmer.getText().toString());
		finish();
	}

}
