package controladores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gorro.bicidf.R;

public enum MapController {
	INSTANCE;

	public static void cargarMapaItem(GoogleMap map, double lat, double lon) {

		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		LatLng locacion = new LatLng(lat, lon);
		CameraPosition camPos = new CameraPosition.Builder().target(locacion)
				.zoom(16).build();
		CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);
		map.animateCamera(camUpd);
	}

	public static void cargarMapa(GoogleMap map) {

		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		LatLng locacion = new LatLng(19.432675, -99.1330443);
		CameraPosition camPos = new CameraPosition.Builder().target(locacion)
				.zoom(14).build();
		CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);
		map.animateCamera(camUpd);

	}

	public static void crearMarcadorItem(GoogleMap map, String nombre,
			String bike, String free, double lat, double lon, Boolean icono) {
		if (icono == true) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(lat, lon))
					.title(nombre)
					.snippet("Disponibles: " + bike + " Libres: " + free)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.cycling)));
		} else {
			map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
					.title(nombre));
		}

	}

	public static void crearMarcador(GoogleMap map, String nombre, double lat,
			double lon, Boolean icono) {
		if (icono == true) {
			map.addMarker(new MarkerOptions()
					.position(new LatLng(lat, lon))
					.title(nombre)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.cycling)));
		} else {
			map.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
					.title(nombre));
		}

	}

	public static void goMarkerMap(Context context, Class<?> cls,
			JSONArray json, int i) {
		try {
			JSONObject jsonO = json.getJSONObject(i);
			double lat = Integer.parseInt(jsonO.getString("lat")) / 1E6;
			double lon = Integer.parseInt(jsonO.getString("lng")) / 1E6;
			Intent intent = new Intent(context, cls);
			intent.putExtra("estacion", jsonO.getString("name"));
			intent.putExtra("bikes", jsonO.getString("bikes"));
			intent.putExtra("free", jsonO.getString("free"));
			intent.putExtra("latitud", lat);
			intent.putExtra("longitud", lon);
			context.startActivity(intent);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void goMarkerMap(Context context, Class<?> cls,
			JSONArray json, String id) {
		Intent intent = new Intent(context, cls);
		JSONObject jsonO;
		try {
			for (int i = 0; i < json.length(); i++) {
				jsonO = json.getJSONObject(i);
				String idx = jsonO.getString("idx");
				if (id.equals("m" + idx)) {
					double lat = Integer.parseInt(jsonO.getString("lat")) / 1E6;
					double lon = Integer.parseInt(jsonO.getString("lng")) / 1E6;

					intent.putExtra("estacion", jsonO.getString("name"));
					intent.putExtra("bikes", jsonO.getString("bikes"));
					intent.putExtra("free", jsonO.getString("free"));
					intent.putExtra("latitud", lat);
					intent.putExtra("longitud", lon);
				}

			}
			context.startActivity(intent);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
