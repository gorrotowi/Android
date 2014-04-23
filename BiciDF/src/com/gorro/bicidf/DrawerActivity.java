package com.gorro.bicidf;

import java.util.ArrayList;

import com.gorro.entidades.ItemDrawer;

import adaptadores.NavigationAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@SuppressLint("Recycle")
public class DrawerActivity extends Activity {

	private String[] titulos;
	private DrawerLayout NavDrawerLayout;
	private ListView NavList;
	private ArrayList<ItemDrawer> NavItms;
	private TypedArray NavIcons;
	private ActionBarDrawerToggle mDrawerToggle;
	NavigationAdapter NavAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer);

		NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		NavList = (ListView) findViewById(R.id.listaDrawer);
		View header = getLayoutInflater().inflate(R.layout.header, null);
		NavList.addHeaderView(header);
		NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
		titulos = getResources().getStringArray(R.array.nav_options);
		NavItms = new ArrayList<ItemDrawer>();

		NavItms.add(new ItemDrawer(titulos[0], NavIcons.getResourceId(0, -1)));
		NavItms.add(new ItemDrawer(titulos[1], NavIcons.getResourceId(1, -1)));

		NavAdapter = new NavigationAdapter(this, NavItms);
		NavList.setAdapter(NavAdapter);

		mDrawerToggle = new ActionBarDrawerToggle(this, NavDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.hello_world) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				Log.e("Cerrado completo", "!!");
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				Log.e("Apertura completa", "!!");
			}
		};

		// Establecemos que mDrawerToggle declarado anteriormente sea el
		// DrawerListener
		NavDrawerLayout.setDrawerListener(mDrawerToggle);
		// Establecemos que el ActionBar muestre el Boton Home
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		NavList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				mostrarfragment(position);
			}
		});
	}

	protected void mostrarfragment(int position) {
		//Fragment fragment = null;
		switch (position) {
		case 1:
			//MainActivity main = new MainActivity();
			//fragment = (Fragment)main;
			//fragment = new 
			break;

		default:
			break;
		}
		
//		if (fragment != null){
//			noFragment(fragment, position); 
//		}
	}

//	private void noFragment(Fragment fragment, int position) {
//		{
//			FragmentManager fragmentManager = getFragmentManager();
//			fragmentManager.beginTransaction()
//					.replace(R.id.container_frame, fragment).commit();
//
//			// Actualizamos el contenido segun la opcion elegida
//			NavList.setItemChecked(position, true);
//			NavList.setSelection(position);
//			// Cambiamos el titulo en donde decia "
//			setTitle(titulos[position - 1]);
//			// Cerramos el menu deslizable
//			NavDrawerLayout.closeDrawer(NavList);
//		}
//	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			Log.e("mDrawerToggle pushed", "x");
			return true;
		}
		// Handle your other action bar items...
		return super.onOptionsItemSelected(item);
	}

}
