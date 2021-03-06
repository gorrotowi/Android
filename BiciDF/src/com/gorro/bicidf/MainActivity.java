package com.gorro.bicidf;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.gorro.entidades.ItemListaBicis;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import controladores.MapController;

import adaptadores.ListaAdapter;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends android.support.v4.app.FragmentActivity
		implements LocationListener {

	ArrayList<ItemListaBicis> arrayListItem;
	MainActivity parent = this;
	double miLat;
	double miLon;
	GoogleMap map;

	private LocationManager locationManager;
	private String provider;

	private JSONObject jsonO;
	private JSONArray json;
	ListView lista;
	LinearLayout btnCall;

	DecimalFormat df;

	String UrlBici = "http://api.citybik.es/ecobici.json";
	String UrlAire = "http://datos.labplc.mx/aire.json";

	TextView txtClima;
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar bar = getActionBar();
		int morado = Color.rgb(56, 11, 97);
		bar.setBackgroundDrawable(new ColorDrawable(morado));
		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		arrayListItem = new ArrayList<ItemListaBicis>();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		lista = (ListView) findViewById(R.id.listaBicis);
		btnCall = (LinearLayout) findViewById(R.id.btnCall);
		txtClima = (TextView) findViewById(R.id.txtClima);

		progress = new ProgressDialog(MainActivity.this);
		progress.setMessage("Obteniendo datos... ;)");

		df = new DecimalFormat("#.##");

		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		try {
			Location location = locationManager.getLastKnownLocation(provider);
			if (location != null) {
				onLocationChanged(location);
			} else {
				// TODO crear un metodo unico que obtenga la ultima locacion
				// si este llega a fallar, volver a llamar este metodo.
				// locacion lo disponible
			}
		} catch (Exception e) {
			Log.w("Error Provider", e.toString());
		}

		MapController.cargarMapa(map, miLat, miLon);
		ConseguirDatos();

		btnCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(parent, "pulsando boton call",
				// Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(parent, EmergencyNumbers.class);
				startActivity(intent);
			}
		});

		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker marker) {
				String idmarker = (String) marker.getId();
				Log.e("Mi marker", idmarker);
				if (idmarker.equals("m275")) {
					Log.e("pisando mi marcador", idmarker);
				} else {
					MapController.goMarkerMap(MainActivity.this,
							MapRowActivity.class, json, idmarker, miLat, miLon);
				}
			}
		});

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MapController.goMarkerMap(MainActivity.this,
						MapRowActivity.class, json, position, miLat, miLon);
			}
		});

	}

	public void ConseguirDatos() {

		progress.show();
		AsyncHttpClient cliente = new AsyncHttpClient();
		cliente.get("http://api.citybik.es/ecobici.json",
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String response) {
						// Log.e("JSON", response);
						parseoDeDatosBici(response);
						progress.dismiss();
					}

					@Override
					public void onRetry() {
						Log.w("request", "retry bitch!");
					}

					@Override
					public void onFailure(Throwable error) {

						progress.dismiss();
						Toast.makeText(
								getApplicationContext(),
								"No pudimos recolectar los datos, intenta de nuevo",
								Toast.LENGTH_LONG).show();
						Log.e("Error", error.toString());
					}

				});
		aire();

	}

	public void aire() {
		Log.e("empezando aire", "asdf");
		AsyncHttpClient cliente = new AsyncHttpClient();
		cliente.get(UrlAire, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {

				if (response.equals("Demasiadas solicitudes")) {
					Toast.makeText(MainActivity.this,
							"No pudimos obtener el aire, intena de nuevo",
							Toast.LENGTH_SHORT).show();
				} else {

					try {
						JSONObject objJson = new JSONObject(response);
						JSONObject consulta = objJson.getJSONObject("consulta");
						JSONObject clima = consulta.getJSONObject("clima");
						String climaString = clima.getString("condicion");
						txtClima.setText(climaString);

					} catch (JSONException e) {
						// e.printStackTrace();
						Log.e("error aire", e.toString());
					}
				}
			}
		});
	}

	protected void parseoDeDatosBici(String response) {
		try {
			json = new JSONArray(response);
			arrayListItem.clear();
			map.clear();
			for (int i = 0; i < json.length(); i++) {
				jsonO = json.getJSONObject(i);
				// Log.w("JSON", jsonO.toString());
				String name = jsonO.getString("name");
				String bicis = jsonO.getString("bikes");
				String freePlace = jsonO.getString("free");
				double lat = Integer.parseInt(jsonO.getString("lat")) / 1E6; // dividimos
																				// entre
																				// 1E6
																				// para
																				// poder
																				// convertir
																				// el
																				// numero
																				// y
																				// obtener
																				// el
																				// punto
																				// decimal
				double lon = Integer.parseInt(jsonO.getString("lng")) / 1E6;
				double dist = calcularDistancia(lat, lon, miLat, miLon);
				//if (dist <= 1500) {
					String distancia = "Distancia: " + df.format(dist)
							+ " Metros";// calcularDistancia(lat, lon, miLat,
										// miLon))+" Metros";
					Log.e("item", "si hay dentro del radio");
					arrayListItem.add(new ItemListaBicis(name, bicis,
							freePlace, distancia));
					MapController.crearMarcador(map, name, lat, lon, true);
				//}

			}

			if (arrayListItem.size() <= 0) {
				progress.dismiss();
				Toast.makeText(
						MainActivity.this,
						"No pudimos encontrar bicicletas cerca de tu ubicacion...intenta mas tarde",
						Toast.LENGTH_LONG).show();
			}

			ListaAdapter adapter = new ListaAdapter(parent, arrayListItem);
			lista.setAdapter(adapter);
			// Log.e("Carga", json.toString());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 6000, 1, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		miLat = (double) (location.getLatitude());
		miLon = (double) (location.getLongitude());
		MapController.crearMarcador(map, "Estas aqui", miLat, miLon, false);
		MapController.cargarMapa(map, miLat, miLon);
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.w("ProviderChange", "Provider desarctivado " + provider);
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.w("ProviderChange", "Provider arctivado " + provider);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.refreshData:
			ConseguirDatos();
			break;
		case R.id.userData:
			Intent intent = new Intent(parent, UserActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public double calcularDistancia(double latIni, double lonIni,
			double latFin, double lonFin) {
		double desLat = Math.toRadians(latFin - latIni);
		double desLon = Math.toRadians(lonFin - lonIni);
		double a = Math.sin(desLat / 2) * Math.sin(desLat / 2)
				+ Math.cos(Math.toRadians(latIni))
				* Math.cos(Math.toRadians(latFin)) * Math.sin(desLon / 2)
				* Math.sin(desLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double result = (c * 6378.1) * 1000;
		// result = Double.valueOf(df.format(result));
		return result;
	}

}
