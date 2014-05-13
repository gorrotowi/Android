package com.gorro.bicidf;

import java.util.ArrayList;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;
import controladores.GMapV2GetRouteDirection;
import controladores.MapController;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;

public class MapRowActivity extends android.support.v4.app.FragmentActivity {

	GoogleMap mapa;
	
	Drawable drawable;
    Document document;
    GMapV2GetRouteDirection v2GetRouteDirection;
    MarkerOptions markerOptions;
    LatLng fromPosition;
    LatLng toPosition;
    
    double lat; 
	double lon; 
	double deviceLat;
	double deviceLon;
	String estacion, bikes, free;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_row);
		
		ActionBar bar = getActionBar();
		int morado = Color.rgb(56, 11, 97);
		bar.setBackgroundDrawable(new ColorDrawable(morado));
		
		v2GetRouteDirection = new GMapV2GetRouteDirection();
		
		Bundle parametros = getIntent().getExtras();
		estacion = parametros.getString("estacion");
		bikes = parametros.getString("bikes");
		free = parametros.getString("free");
		lat = parametros.getDouble("latitud");
		lon = parametros.getDouble("longitud");
		deviceLat = parametros.getDouble("latitudDevice");
		deviceLon = parametros.getDouble("longitudDevice");
		markerOptions = new MarkerOptions();
        fromPosition = new LatLng(lat, lon);
        toPosition = new LatLng(deviceLat, deviceLon);
		

		mapa = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.mapRowItem)).getMap();
		
		//CargarMapa(lat, lon);
		MapController.cargarMapaItem(mapa, lat, lon);
		MapController.crearMarcador(mapa, "Estas aqui", deviceLat, deviceLon, false);
		//MapController.crearMarcadorItem(mapa,estacion, bikes, free, lat, lon, true);
		GetRouteTask getRoute = new GetRouteTask();
        getRoute.execute();

	}
	
	private class GetRouteTask extends AsyncTask<String, Void, String> {
        
        private ProgressDialog Dialog;
        String response = "";
        @Override
        protected void onPreExecute() {
              Dialog = new ProgressDialog(MapRowActivity.this);
              Dialog.setMessage("Loading route...");
              Dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
              //Get All Route values
                    document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2GetRouteDirection.MODE_DRIVING);
                    response = "Success";
              return response;

        }

        @Override
        protected void onPostExecute(String result) {
              mapa.clear();
              if(response.equalsIgnoreCase("Success")){
              ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
              PolylineOptions rectLine = new PolylineOptions().width(10).color(
                          Color.RED);

              for (int i = 0; i < directionPoint.size(); i++) {
                    rectLine.add(directionPoint.get(i));
              }
              // Adding route on the map
              mapa.addPolyline(rectLine);
              markerOptions.position(toPosition);
              markerOptions.draggable(true);
              mapa.addMarker(markerOptions);
              MapController.crearMarcadorItem(mapa,estacion, bikes, free, lat, lon, true);

              }
             
              Dialog.dismiss();
        }
  }
  @Override
  protected void onStop() {
        super.onStop();
        finish();
  }
	
}
