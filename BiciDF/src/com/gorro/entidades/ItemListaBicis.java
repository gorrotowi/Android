package com.gorro.entidades;

public class ItemListaBicis {

	String NombreEst;
	String disponibles;
	String lugaresLib;

	public ItemListaBicis(String nombreEst, String disponibles,
			String lugaresLib) {
		super();
		this.NombreEst = nombreEst;
		this.disponibles = disponibles;
		this.lugaresLib = lugaresLib;
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

}
