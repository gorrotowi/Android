package com.gorro.entidades;

public class ItemListaBicis {

	String NombreEst;
	String disponibles;
	String lugaresLib;
	String distancia;

	public ItemListaBicis(String nombreEst, String disponibles,
			String lugaresLib, String distancia) {
		super();
		this.NombreEst = nombreEst;
		this.disponibles = disponibles;
		this.lugaresLib = lugaresLib;
		this.setDistancia(distancia);
	}

	public String getNombreEst() {
		return NombreEst;
	}

	public void setNombreEst(String nombreEst) {
		NombreEst = nombreEst;
	}

	public String getDisponibles() {
		return disponibles;
	}

	public void setDisponibles(String disponibles) {
		this.disponibles = disponibles;
	}

	public String getLugaresLib() {
		return lugaresLib;
	}

	public void setLugaresLib(String lugaresLib) {
		this.lugaresLib = lugaresLib;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

}
