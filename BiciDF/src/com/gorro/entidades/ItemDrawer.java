package com.gorro.entidades;

public class ItemDrawer {

	String titulo;
	int icono;
	public ItemDrawer(String titulo, int icono) {
		this.titulo = titulo;
		this.icono = icono;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getIcono() {
		return icono;
	}
	public void setIcono(int icono) {
		this.icono = icono;
	}
}
