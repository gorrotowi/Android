package com.gorro.bicidf;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import controladores.MapController;
import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class MapRowActivity extends android.support.v4.app.FragmentActivity {

	GoogleMap mapa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_row);
		
		ActionBar bar = getActionBar();
		int morado = Color.rgb(56, 11, 97);
		bar.setBackgroundDrawable(new ColorDrawable(morado));
		
		Bundle parametros = getIntent().getExtras();
		String estacion = parametros.getString("estacion");
		String bikes = parametros.getString("bikes");
		String free = parametros.getString("free");
		double lat = parametros.getDouble("latitud");
		double lon = parametros.getDouble("longitud");
		

		mapa = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.mapRowItem)).getMap();
		
		//CargarMapa(lat, lon);
		MapController.cargarMapaItem(mapa, lat, lon);
		MapController.crearMarcadorItem(mapa,estacion, bikes, free, lat, lon, true);

	}
	
	
	
}
