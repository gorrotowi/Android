package com.gorro.bicidf;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class EmergencyNumbers extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergency_numbers);
		ActionBar bar = getActionBar();
		int morado = Color.rgb(56, 11, 97);
		bar.setBackgroundDrawable(new ColorDrawable(morado));

	}
	
	public void ecobici(View v) {
		Uri number = Uri.parse("tel:50052424");
		Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
		startActivity(callIntent);
	}
	
	public void bomberos(View v) {
		Uri number = Uri.parse("tel:068");
		Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
		startActivity(callIntent);
	}
	
	public void policia(View v) {
		Uri number = Uri.parse("tel:060");
		Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
		startActivity(callIntent);
	}
	
	public void CruzR(View v) {
		Uri number = Uri.parse("tel:55575757");
		Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
		startActivity(callIntent);
	}
	
	public void CruzR2(View v) {
		Uri number = Uri.parse("tel:53951111");
		Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
		startActivity(callIntent);
	}
	
	public void proteccionCiv(View v) {
		Uri number = Uri.parse("tel:56832222");
		Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
		startActivity(callIntent);
	}

}
