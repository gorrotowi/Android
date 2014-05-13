package com.gorro.bicidf;

import com.google.android.gms.internal.ei;

import controladores.UserSingleton;
import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditUser extends Activity {

	EditText domicilio, nombre, edad, tipoSangre, numEmer, dialogUser,
			dialogPass;
	Button cancelar, aceptar;
	LinearLayout btnSession;

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
		btnSession = (LinearLayout) findViewById(R.id.iniciarSesionBtn);

		btnSession.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogoSesion();
			}
		});

	}

	protected void dialogoSesion() {
		final Dialog dialogo = new Dialog(this);
		dialogo.setContentView(R.layout.logindialog);
		dialogo.setTitle("Inicio de sesion");
		dialogUser = (EditText) dialogo.findViewById(R.id.dialogUser);
		dialogPass = (EditText) dialogo.findViewById(R.id.dialogPass);
		cancelar = (Button) dialogo.findViewById(R.id.btnCancelar);
		aceptar = (Button) dialogo.findViewById(R.id.btnIniciar);
		cancelar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogo.dismiss();
			}
		});
		aceptar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (dialogUser.getText().toString().length() <= 0
						|| dialogPass.getText().toString().length() <= 0) {
					Toast.makeText(EditUser.this,
							"Ingresa un usuario y contraseña por favor",
							Toast.LENGTH_SHORT).show();
				} else {
					if (dialogUser.getText().toString().equals("usuario 1")
							&& dialogPass.getText().toString().equals("123456")) {
						UserSingleton.setNombre("Miguel Hernandez");
						UserSingleton.setDomicilio("Calle 23 colonia obrera #78");
						UserSingleton.setEdad("24");
						UserSingleton.setSangre("O+");
						UserSingleton.setNumEmer("5560908978");
						dialogo.dismiss();
						finish();
					}else if (dialogUser.getText().toString().equals("usuario 2")
							&& dialogPass.getText().toString().equals("123456")) {
						UserSingleton.setNombre("Pedro Perez");
						UserSingleton.setDomicilio("isabela 34 narvarte");
						UserSingleton.setEdad("26");
						UserSingleton.setSangre("A+");
						UserSingleton.setNumEmer("5560678970");
						dialogo.dismiss();
						finish();
					}else if (dialogUser.getText().toString().equals("Luis24")
							&& dialogPass.getText().toString().equals("123456")) {
						UserSingleton.setNombre("Luis Miguel");
						UserSingleton.setDomicilio("hidalgo 34");
						UserSingleton.setEdad("21");
						UserSingleton.setSangre("B+");
						UserSingleton.setNumEmer("5532459800");
						dialogo.dismiss();
						finish();
					}
				}
			}
		});
		dialogo.show();
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
