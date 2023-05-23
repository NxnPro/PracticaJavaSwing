package controlador;



import java.util.ArrayList;



import modelo.BaseDatos;
import modelo.Paises;
import vista.Ventana;
import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermode.XSSFWorkbook;
import java.io.*;
/**
 * Clase principal que contiene el metodo main o principal para iniciar la aplicacion
 * @author Nixon
 *
 */

public class Principal {
	/**
	 * Con este metodo iniciamos la aplicacion el cual primero debemos crear una instancia de la base de datos llamada bases
	 * y luego con bases llamar al metodo conectar para comprobar la conexion a la base de datos, asi mismo crear otra instancia
	 * llamada ventana que pasamos por parametro que llama al JFrame de nuestra aplicacion
	 * @param args -- valor del metetodo principal
	 */
	
	
	public static void main(String[] args) {
		BaseDatos bases = new BaseDatos();
		
		bases.conectar();
		Ventana ventana = new Ventana();
		ventana.Inicio(ventana);
	
	}
	
}


