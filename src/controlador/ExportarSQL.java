package controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * Esta clase permite definir un metodo capas de exportar un archivo de tipo SQL
 * @author Nixon
 *
 */

public class ExportarSQL {
	/**
	 * Metodo que sirve para poder obtener los Datos del JTable llamado tablaPaises de la clase Consultar que pasa por parametro
	 * el cual se encarga de convertirlo en un ficgero de extension SQL.
	 * @param tablaPaises -- valor que pasa por parametro 
	 */
	
	public void ExportarSQL(JTable tablaPaises) {
	    String nombreArchivo = "Registro_SQL.sql";
	    File archivo = new File(nombreArchivo);
	    try {
	        FileWriter writer = new FileWriter(archivo, true); // "true" indica que se añadirá al final del archivo existente
	        if (archivo.exists() && archivo.length() > 0) { // Si el archivo existe y no está vacío, no se escriben las líneas de creación de la tabla
	            // No se escribe nada
	        } else { // Si el archivo no existe o está vacío, se escriben las líneas de creación de la tabla
	        	writer.write("DROP TABLE IF EXISTS tabla_generada;\n");
	            writer.write("CREATE TABLE tabla_generada (\n");
	            writer.write("Continente varchar(255),\n");
	            writer.write("Pais varchar(255),\n");
	            writer.write("IdiomaOficial varchar(255),\n");
	            writer.write("Distrito varchar(255),\n");
	            writer.write("Ciudad varchar(255),\n");
	            writer.write("Poblacion int\n");
	            writer.write(");\n");
	        }
	        for (int i = 0; i < tablaPaises.getRowCount(); i++) {
	            String continente = (String) tablaPaises.getValueAt(i, 0);
	            String pais = (String) tablaPaises.getValueAt(i, 1);
	            String idiomaOficial = (String) tablaPaises.getValueAt(i, 2);
	            String distrito = (String) tablaPaises.getValueAt(i, 3);
	            String ciudad = (String) tablaPaises.getValueAt(i, 4);
	            int poblacion = (int) tablaPaises.getValueAt(i, 5);
	            writer.write("INSERT INTO tabla_generada (Continente, Pais, IdiomaOficial, Distrito, Ciudad, Poblacion) VALUES ");
	            writer.write("('" + continente + "', '" + pais + "', '" + idiomaOficial + "', '" + distrito + "', '" + ciudad + "', " + poblacion + ");\n");
	        }
	        writer.close();
	        JOptionPane.showMessageDialog(null, "Archivo generado correctamente", "Archivo SQL", JOptionPane.INFORMATION_MESSAGE);
	        Desktop.getDesktop().open(archivo);
	    } catch (IOException ex) {
	        JOptionPane.showMessageDialog(null, "Error al generar el archivo", "Archivo SQL", JOptionPane.ERROR_MESSAGE);
	    }
	}
}
