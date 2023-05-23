package modelo;

import javax.swing.ImageIcon;
/**
 * Clase que permite crear un Objeto llamado Banderas 
 * @author Nixon
 *
 */

public class Banderas {
	private ImageIcon imagen;
	private String nombre;
	
	public Banderas() {
		imagen = null;
		nombre = null;
	}
	
	public Banderas(ImageIcon imagen, String nombre) {
		this.imagen = imagen;
		this.nombre = nombre;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
