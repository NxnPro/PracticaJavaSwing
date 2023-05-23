package modelo;

import java.net.ConnectException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

/**
 * Esta clase permite definir unos metodos que podemos reciclar y usarlo como una base de datos
 * haciendo que el codigo sea limpio
 * @author Nixon
 *
 */

public class BaseDatos {

	
	/**
	 * Metodo que me permite hacer una conexion a la base de datos world
	 * el cual captura con un try catch cuando no esta encendida la base de datos
	 */
	
	
	public void conectar() {
        Connection conexion = null;
        try {
          conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//          conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
        } catch (CommunicationsException err) {
        	JOptionPane.showMessageDialog(null, "La Base de datos no esta Iniciada");
            JOptionPane.showMessageDialog(null,"Encienda la base de datos");
             System. exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
	
	

	/**
	 * metodo para cargar los paises en el primer comoboBox llamado cmbPaises
	 * asi tambien como crear un ArrayList de tipo Objeto Banderas para que pueda cargar las banderas.png
	 * junto con el cmbPaises.
	 * pasandondolo a una variable llamada aux el objeto con los parametros imagen y el nombre del resulset
	 * 
	 * @return -- valor que devuelve  un ArrayList de tipo Objeto
	 */
	public ArrayList<Banderas> cargaPaises() {
		BaseDatos bases = new BaseDatos();
		Banderas aux = null;
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		ArrayList<Banderas> nombrePaises = new ArrayList<>();
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select name, code from country order by name");
			
			if (registro == null) {
				System.out.println("La base de datos está vacia.");
			}
			while (registro.next()) {
				ImageIcon imagen = new ImageIcon("Banderas/" + registro.getString("code") + ".png");
				aux = new Banderas(imagen, registro.getString("name"));
				nombrePaises.add(aux);
				// Añadimos al combo box los nombres de la base de datos de world
				// ventana.cmdPaises.addItem(registro.getString("name"));
			}

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "La Base de datos no esta Iniciada");
			error.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (CommunicationsException ex) {
				JOptionPane.showMessageDialog(null, "La Base de datos no esta Iniciada");
			} catch (SQLException e) {
				System.err.println("No se ha podido cerrar la base de datos");
			} catch (NullPointerException e) {

			}
		}
		return nombrePaises;
	}




	/**
	 * Metodo para cargar Distritos dentro del comboBox cmbDistritos el cual tiene un parametro de tipo String pais
	 * @param pais ---   valor que recibe como parametro elegido desde el comboBox cmbDistritos
	 * @return ---  valor que devuelve  el metodo como ArrayList de tipo String
	 */
	public ArrayList<String> cargaDistritos(String pais) {
		// conexión a base de datos
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		ArrayList<String> nombreDistritos = new ArrayList<>();

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select distinct city.district " + "from country, city " + "where country.Name='"
							+ pais + "' and country.Code=city.CountryCode " + "order by city.district");

			if (registro == null) {
				JOptionPane.showMessageDialog(null,"La base de datos está vacia.");
			}

			while (registro.next()) {
				String aux;
				aux = registro.getString("district");

				nombreDistritos.add(aux);
				// Añadimos al combo box los nombres de la base de datos de world
				// ventana.cmdCiudades.addItem(registro.getString("name"));
			}

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "La Base de datos no esta Iniciada");
			error.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (CommunicationsException ex) {
				System.err.println("La base de datos no esta encendida");
			} catch (SQLException e) {
				System.err.println("No se ha podido cerrar la base de datos");
			} catch (NullPointerException e) {

			}
		}
		return nombreDistritos;
	}


	/**
	 * Metodo para cargar el JTable con el TextField llamado txtNombre, mediante el parametro de tipo String y retornarlo 
	 * mediante un return un ArrayList de tipo Objeto para cargar la tabla segun la busqueda del usuario
	 * @param nombre --- valor de tipo String que toma como parametro 
	 * @return    --- Valor que devuelve  como ArrayList tipo Objeto
	 */
	public ArrayList<Paises> cargaTablaNombre(String nombre) {
		ArrayList<Paises> arrLNombre = new ArrayList<>();

		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//			 conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery(
					"select country.continent,country.name,countrylanguage.language,city.district,city.name,city.population"
							+ " from city,country,countrylanguage "
							+ "where country.code=city.countryCode and country.code=countrylanguage.countryCode and city.name like '%"
							+ nombre + "%';");

			Paises c = null;

			if (registro == null) {
				System.out.println("La consulta ha fallado");
			} else {
				while (registro.next()) {
					c = new Paises(null, null, null, null, 0, null);
					c.setContinente(registro.getString("continent"));
					c.setPais(registro.getString("country.name"));
					c.setIdioma(registro.getString("language"));
					c.setDistrito(registro.getString("district"));
					c.setCiudad(registro.getString("city.name"));
					c.setPoblacion(registro.getInt("population"));

					arrLNombre.add(c);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return arrLNombre;
	}


	/**
	 * Metodo para cargar el Jtable con el comboBox cmbPaises el cual pasa por parametro un String 
	 * y retorna un ArrayList tipo Objeto con todo el contenido
	 * @param pais --- valor que recibe por parametro
	 * @return --- valor que devuelve un ArrayList de tipo Objeto
	 */
	public ArrayList<Paises> cargaTablaPais(String pais) {
		ArrayList<Paises> arrLNombre = new ArrayList<>();
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");;
			consulta = conexion.createStatement();
			registro = consulta.executeQuery(
					"select country.continent,country.name,countrylanguage.language,city.district,city.name,city.population"
							+ " from city,country,countrylanguage "
							+ "where country.code=city.countryCode and country.code=countrylanguage.countryCode and country.name like '%"
							+ pais + "%';");

			Paises c = null;

			if (registro == null) {
				System.out.println("La consulta ha fallado");
			} else {
				while (registro.next()) {
					c = new Paises(null, null, null, null, 0, null);

					c.setContinente(registro.getString("continent"));
					c.setPais(registro.getString("country.name"));
					c.setIdioma(registro.getString("language"));
					c.setDistrito(registro.getString("district"));
					c.setCiudad(registro.getString("city.name"));
					c.setPoblacion(registro.getInt("population"));

					arrLNombre.add(c);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return arrLNombre;
	}




	/**
	 * Metodo para cargar el slider llamado sliPoblacion  con la el JTable 
	 * Este metodo hace que pase la poblacion para gestionarla con un if en la clase Consultar y devuelva la poblacion
	 * @return -- valor que devuelve un ArrayList de tipo String
	 */
	public ArrayList<String> cargaPoblacion() {
		ArrayList<String> arrPoblacion = new ArrayList<>();
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//			 conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select population from city order by population asc ");
			if (registro == null) {
				System.out.println("La consulta ha fallado");
			}
			while (registro.next()) {
				int aux;
				aux = registro.getInt("population");
				String poblacion = Integer.toString(aux);
				arrPoblacion.add(poblacion);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return arrPoblacion;
	}

	/**
	 * Metodo para cargar el JTable con el comboBox cmbDistritos mediante una consulta a la base de datos 
	 * el cual se pasa con un parametro de tipo String distrito
	 * @param distrito -- valor de tipo String que pasamos por parametro
	 * @return  -- valor que devuelve como Arraylist de tipo String
	 */
	public ArrayList<Paises> cargaTablaDistrito(String distrito) {
		ArrayList<Paises> arrLDistrito = new ArrayList<>();
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//			 conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			consulta = conexion.createStatement();
			registro = consulta
					.executeQuery("select continent,country.name,language,district,city.name,city.population "
							+ " from city,country,countrylanguage " + "where district='" + distrito
							+ "' and country.code=city.countryCode and  country.code=countrylanguage.countryCode "
							+ "order by city.name");
			Paises c = null;

			if (registro == null) {
				System.out.println("La consulta ha fallado");
			} else {
				while (registro.next()) {
					c = new Paises(null, null, null, null, 0, null);

					c.setContinente(registro.getString("continent"));
					c.setPais(registro.getString("country.name"));
					c.setIdioma(registro.getString("language"));
					c.setDistrito(registro.getString("district"));
					c.setCiudad(registro.getString("city.name"));
					c.setPoblacion(registro.getInt("population"));

					arrLDistrito.add(c);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return arrLDistrito;
	}


	/**
	 * Metodo para cargar el Jtable con el slider llamado sliPoblacion este es diferente al anterior slider porque este a su vez 
	 * carga todo el contenido del Jtable mediante un ArrayList de tipo Objeto
	 * @param poblacion -- valor que recibe por parametro para hacer la consulta en la base de datos
	 * @return -- valor que devuelve el ArrayList de tipo Objeto
	 */
	public ArrayList<Paises> cargaTablaPoblacion(int poblacion) {
		ArrayList<Paises> arrLPoblacion = new ArrayList<>();
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select continent,country.name,language,district,city.name,city.population"
					+ " from city,country,countrylanguage" + " where city.population >= '" + poblacion
					+ "'and country.code=city.countryCode " + "and country.code=countrylanguage.countryCode "
					+ "order by city.Population ASC;");

			Paises c = null;
			if (registro == null) {
				System.out.println("La consulta ha fallado");
			} else {
				while (registro.next()) {
					c = new Paises(null, null, null, null, 0, null);

					c.setContinente(registro.getString("continent"));
					c.setPais(registro.getString("country.name"));
					c.setIdioma(registro.getString("language"));
					c.setDistrito(registro.getString("district"));
					c.setCiudad(registro.getString("city.name"));
					c.setPoblacion(registro.getInt("population"));

					arrLPoblacion.add(c);

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return arrLPoblacion;
	}



	/**
	 * Metodo que sirve para rellenar el JTable con distritos haciendo la consulta a la base de datos 
	 * @return -- valor que devuelve con un ArrayList tipo String 
	 */
	public ArrayList<String> cargaDistrito() {
		ArrayList<String> arrDistrito = new ArrayList<>();
		Connection conexion = null;
		Statement consulta = null;
		ResultSet registro = null;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "");
//			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			consulta = conexion.createStatement();
			registro = consulta.executeQuery("select distinct district from city order by district");
			if (registro == null) {
				System.out.println("La consulta ha fallado");
			}
			while (registro.next()) {
				String aux;
				aux = registro.getString("district");
				arrDistrito.add(aux);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return arrDistrito;
	}

//Metodo para moficiar las columnas de la base de datos por ejemplo ciudad, poblacion, pais

	/**
	 * Metodo que sirve para modificar la fila de datos que estan en un JTable llamada Tabla Paises
	 * el cual pasamos mediante parametros para poder modificar campor por campor dicho proposito
	 * @param ciudadAnterior -- valor que nos indica el campo que contiene la ciudad antes de ser modificada
	 * @param distritoAnterior -- valor que nos indica el campo que contiene el distrito antes de ser modificada
	 * @param poblacionAnterior -- valor que contiene el campo poblacion antes de ser modificada 
	 * @param ciudadNueva -- valor nuevo del campo ciudad que ha sido moficado
	 * @param distritoNuevo -- valor nuevo del campo distrito que ha sido modificado
	 * @param poblacionNuevo -- valor nuevo del campo poblacion que ha sido modificado
	 */
	public void modificandoDatos(String ciudadAnterior, String distritoAnterior, int poblacionAnterior,
			String ciudadNueva, String distritoNuevo, int poblacionNuevo) {
		Connection conexion;

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/world", "root", "");
//			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			Statement consulta = conexion.createStatement();

			int valor = consulta.executeUpdate("update city set city.name = '" + ciudadNueva + "' , city.district ='"
					+ distritoNuevo + "' , city.population = " + poblacionNuevo + " where city.name ='" + ciudadAnterior
					+ "' and city.district= '" + distritoAnterior + "' and population= " + poblacionAnterior + "");

			if (valor == 1) {
				System.out.println("Valores modificados correctemente");
				JOptionPane.showMessageDialog(null, "Ciudad modificada correctamente");
			} else {
				System.out.println("No existe esa ciudad");
				JOptionPane.showMessageDialog(null, "No se ha podido modificar");
			}
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	/**
	 * Metodo que sirve para eliminar o borrar una fila del JTable llamado  tablaPaises dentro de la clase Consultar
	 * el cual pasamos por parametros para poder gestionar la consulta y realizar  el cambio de los campos que necesitamos borrar
	 * @param ciudad -- valor de tipo String que pasa como parametro
	 * @param distrito-- valor de tipo String que pasa como parametro
	 * @param poblacion -- valor de tipo String que pasa como parametro
	 */
	public void borrandoDatosTabla(String ciudad, String distrito, String poblacion) {

		try {
			Connection conexion= null;
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/world", "root", "");
//			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			Statement consulta = conexion.createStatement();

			int valor = consulta.executeUpdate("delete from city where city.name='" + ciudad + "' and city.district= '"
					+ distrito + "' and population='" + poblacion + "'");

			if (valor == 1) {
				JOptionPane.showMessageDialog(null, "Ciudad borrada correctamente");
			} else {
				System.out.println("No existe esa ciudad");
				JOptionPane.showMessageDialog(null, "No existe esa ciudad");
			}
			conexion.close();
		} catch (CommunicationsException ex) {
			System.err.println("La base de datos no esta encendida");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Metodo para insertar una nueva ciudad en el JTable llamada tablaPais el cual contiene ciudad, pais, distrio y poblacion
	 * que debemos pasar como parametro para gestionar con la base de datos
	 * @param ciudad -- valor de tipo String que pasa por parametro
	 * @param pais -- valor de tipo String que pasa por parametro
	 * @param distrito -- valor de tipo String que pasa por parametro
	 * @param poblacion -- valor de tipo Integer que pasa por parametro
	 */
	public void insertarFilaBaseDeDatos(String ciudad, String pais, String distrito, int poblacion) {

		try {
			Connection conexion= null;
			String codPaises;
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/world", "root", "");
//			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "paises2023", "paises2023");
			Statement consulta = conexion.createStatement();

			// los executeQuery siempre devuelven un dato tipo ResultSet por eso hay que
			// guradarlos en varibles tipo resultSet
			ResultSet paisSubconsulta = consulta.executeQuery("select code from country where name= '" + pais + "'");

			if (paisSubconsulta.next()) {
				codPaises = paisSubconsulta.getString("code");

				// Los executeUpdate siempre dan de resultado valores tipo enteros por eso se
				// guardan en variables de tipo entero
				int valor = consulta.executeUpdate("INSERT INTO city (Name,countrycode,District,Population) VALUES ('"
						+ ciudad + "','" + codPaises + "','" + distrito + "'," + poblacion + ")");

				if (valor == 1) {
					JOptionPane.showMessageDialog(null, "Ciudad insertadad correctamente");
				} else {
					JOptionPane.showMessageDialog(null, "se produjo un error en la insercion");
				}

			}

			conexion.close();
		} catch (CommunicationsException ex) {
			System.err.println("La base de datos no esta encendida");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}