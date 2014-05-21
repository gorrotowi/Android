package com.gorro.calculadorahonorarios;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int REQUEST_RECOGNIZE = 100;
	ImageView imgMic;
	EditText importeDes;
	TextWatcher txtwatcher;
	
	TextView txtImporte, txtIVA, txtSubtotal, txtRetIVA, txtRetISR, txtTotal;
	
	int importedesInt;
	double importe,iv, iva, subtotal, retenciones, retencion_nueva, total;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar bar = getActionBar();
		int naranja = Color.parseColor("#FF8000");//rgb(56, 11, 97);
		bar.setBackgroundDrawable(new ColorDrawable(naranja));

		imgMic = (ImageView) findViewById(R.id.microfone);
		importeDes = (EditText) findViewById(R.id.importeDeseado);
		txtImporte = (TextView) findViewById(R.id.txtImporte);
		txtIVA = (TextView) findViewById(R.id.txtIva);
		txtSubtotal = (TextView) findViewById(R.id.txtSubtotal);
		txtRetIVA = (TextView) findViewById(R.id.txtRetIva);
		txtRetISR = (TextView) findViewById(R.id.txtRetISR);
		txtTotal = (TextView) findViewById(R.id.txtTotal);

		txtwatcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				calcularTodo(importeDes.getText().toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		};

		importeDes.addTextChangedListener(txtwatcher);
		
		imgMic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				iniciarReconocimiento();
			}
		});

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 if(requestCode == REQUEST_RECOGNIZE && resultCode == Activity.RESULT_OK) {
	            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	            StringBuilder sb = new StringBuilder();
	            for(String piece : matches) {
	                sb.append(piece);
	                sb.append('\n');
	            }
	            if(matches.size() !=0){
	            for(int i = 0; i<matches.size(); i++){
	            	importeDes.setText(matches.get(0));
	            }
	            }
	           
	        } else {
	            Toast.makeText(this, R.string.operacionCandelada, Toast.LENGTH_SHORT).show();
	        }
	}

	protected void iniciarReconocimiento() {
		Intent intent = new Intent(
				RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				R.string.speechCantidad);
		try {
			startActivityForResult(intent, REQUEST_RECOGNIZE);
		} catch (Exception e) {
			e.printStackTrace();
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MainActivity.this);
			builder.setTitle(R.string.noDisp);
			builder.setMessage(R.string.noSoftInstall);
			builder.setPositiveButton(R.string.si,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							Intent marketIntent = new Intent(
									Intent.ACTION_VIEW);
							marketIntent.setData(Uri
									.parse("https://play.google.com/store/apps/details?id=com.google.android.voicesearch"));
						}
					});
			builder.setNegativeButton(R.string.no, null);
			builder.create().show();
		}
	}

	public static double redondear(double numero) {
		return Math.rint(numero * 100) / 100;
	}

	public void calcularTodo(String num) {

		if (num.equals("")) {
			importedesInt = 0;
		} else {
			try {
				importedesInt = Integer.parseInt(num);
			} catch (Exception e) {
				Log.e("Texto irreconocible", e.toString());
				importedesInt = 0;
				importeDes.setText("0");
				//Toast.makeText(MainActivity.this, "Pronuncia un numero", Toast.LENGTH_SHORT).show();
			}
			
		}
		//Toast.makeText(this, importedesInt + "", Toast.LENGTH_SHORT).show();

		importe = 0;
		iv = 0.16;
		if (iv > 0.14) {
			importe = importedesInt / 0.9533333333333333334;
		} else {
			importe = importedesInt / 0.9;
		}
		iva = importe * iv;
		subtotal = importe + iva;
		retenciones = importe * .1;
		retencion_nueva = (iva / 3) * 2;
		total = subtotal - retenciones - retencion_nueva;

		Log.e("Calculado", "importe: " + importe + "\nIVA: " + redondear(iva)
				+ "\nSubtotal: " + redondear(subtotal) + "\nRetencion IVA: "
				+ redondear(retencion_nueva) + "\nRetencion ISR: "
				+ redondear(retenciones) + "\nTOTAL: " + redondear(total));
		
		txtImporte.setText(redondear(importe)+"");
		txtIVA.setText(redondear(iva)+"");
		txtSubtotal.setText(redondear(subtotal)+"");
		txtRetIVA.setText(redondear(retencion_nueva)+"");
		txtRetISR.setText(redondear(retenciones)+"");
		txtTotal.setText(redondear(total)+"");
	}

}
