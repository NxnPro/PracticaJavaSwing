package vista;

import javax.*;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

//import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
//import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdom2.input.StAXStreamWriter;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.mysql.fabric.xmlrpc.base.Array;

import modelo.Banderas;
import modelo.BaseDatos;
import modelo.Paises;
//import controlador.Banderas;
import controlador.ExportarExcel;
import controlador.ExportarSQL;
import controlador.ExportarXML;
import controlador.Principal;
import vista.*;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.ImageIcon;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import controlador.Principal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import javax.swing.JScrollPane;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.io.FileWriter;

import com.sun.*;
/**
 * Esta clase permite definir las caracteristicas, eventos y acciones que tendra nuestro JFrame
 * @author Nixon
 *
 */

public class Consultar extends JPanel {
	
	Principal principal = new Principal();
	private JTable table;
	private JComboBox cmbPaises;
	private JComboBox cmbDistritos;
	DefaultTableModel modeloTabla = new DefaultTableModel();
	

	/**
	 * Create the panel.
	 */
	BaseDatos bases = new BaseDatos();
	ButtonGroup grupo = new ButtonGroup();
	private JRadioButton rdbBoton1;
	private JRadioButton rdbBoton2;
	private JRadioButton rdbBoton3;
	private JSlider sliPoblacion;
	private JRadioButton rdbBoton4;
	private JLabel lblNewLabel;
	private JLabel lblPais;
	private JLabel lblCiudades;
	private JButton btnSql;
	private JButton btnXml;
	private JButton btnExcel;
	private JLabel lblPoblacion;
	private JTextField textNombre;
	private JComboBox cmbDistritoModificar;
	private JComboBox cmbPoblacionModificar;
	private JLabel lblValor;
	private JTable tablaPaises;
	private JButton btnBorrar;
	private JButton btnModificar;
	private JComboBox cmboDistrito2;
	private JComboBox cmbPoblacion;
	private JTextField txtCiudad;
	
	protected DocumentBuilder domFactory = null;
	protected DocumentBuilder domBuilder = null;
	
	/**
	 * Consultar es un cardlayout del JFrame Ventana
	 */
	
	
	public Consultar() {
	
		setBackground(new Color(211, 211, 211));
		
		setLayout(null);
		/**
		 * RadioButton para seleccionar el textField llamado txtNombre
		 * el cual al seleccionarlo desabilita los otros RadioButton
		 */
		
		rdbBoton1 = new JRadioButton("");
		rdbBoton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textNombre.setEnabled(true);
				cmbPaises.setEnabled(false);
				cmbDistritos.setEnabled(false);
				sliPoblacion.setEnabled(false);
			}
		});
		rdbBoton1.setBounds(31, 28, 26, 23);
		add(rdbBoton1);
		grupo.add(rdbBoton1);
		rdbBoton1.addItemListener(null);
		
	
		/**
		 * RadioButton para seleccionar el comboBox  llamado cmbPaises
		 * el cual al seleccionarlo desabilita los otros RadioButton
		 */
		
		rdbBoton2 = new JRadioButton("");
		rdbBoton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cmbPaises.setEnabled(true);
				textNombre.setEnabled(false);
				cmbDistritos.setEnabled(false);
				sliPoblacion.setEnabled(false);
			}
		});
		rdbBoton2.setBounds(260, 28, 26, 23);
		add(rdbBoton2);
		grupo.add(rdbBoton2);
		
		rdbBoton3 = new JRadioButton("");
		rdbBoton3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cmbDistritos.setEnabled(true);
				cmbPaises.setEnabled(false);
				textNombre.setEnabled(false);
				sliPoblacion.setEnabled(false);
			}
		});
		
		/**
		 * RadioButton para seleccionar el comboBox  llamado cmbDistritos
		 * el cual al seleccionarlo desabilita los otros RadioButton
		 */
		
		rdbBoton3.setBounds(488, 28, 26, 23);
		add(rdbBoton3);
		
		grupo.add(rdbBoton3);
		
		/**
		 * RadioButton para seleccionar el slider  llamado sliPoblacion
		 * el cual al seleccionarlo desabilita los otros RadioButton
		 */
		
		rdbBoton4 = new JRadioButton("");
		rdbBoton4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sliPoblacion.setEnabled(true);
				cmbDistritos.setEnabled(false);
				cmbPaises.setEnabled(false);
				textNombre.setEnabled(false);
			}
		});
		rdbBoton4.setBounds(31, 95, 26, 23);
		add(rdbBoton4);
		
		grupo.add(rdbBoton4);
		
		/**
		 * JLabel llamado nombre
		 */
		
		lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(63, 28, 72, 23);
		add(lblNewLabel);
		
		/**
		 * En este texFiel textNombre creamos un ArrayList dentro de un accion de tipo keyEvent
		 * el cual hace que cuando escribirmos dentro este pueda buscar automaticamente las coincidencias de la letra oprimida
		 * Primero creamos un ArrayList de tipo Objeto
		 * Segundo creamo una variable de tipo String llamada nombre que se encargara de recoger lo que esta dentro del textField 
		 * Tercero llenamos ese ArrayList con un metodo llamado desde la base de datos para cargar el nombre de todos los campos que contiene 
		 * el JTable.
		 * Por ultimo cargamos todos los campos con un bucle foreach
		 */
		
		
		textNombre = new JTextField();
		
		textNombre.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				ArrayList<Paises> arrLNombre = new ArrayList<>();
				String nombre;
				nombre = textNombre.getText().toString();
								
				modeloTabla.setRowCount(0);
				arrLNombre = bases.cargaTablaNombre(nombre);
					
				for (Paises c : arrLNombre) {
					modeloTabla.addRow(new Object[] {
							c.getContinente(),
							c.getPais(),
							c.getIdioma(),
							c.getDistrito(),
							c.getCiudad(),
							c.getPoblacion()
							
					});
				}
				
			}
		});
		textNombre.setBounds(130, 28, 106, 28);
		add(textNombre);
		textNombre.setColumns(10);
		
		/**
		 * Nombre del laber llamado PAIS
		 */
		lblPais = new JLabel("PAIS");
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPais.setBounds(292, 28, 52, 23);
		add(lblPais);
		
		/**
		 * Nombre del laber llamado DISTRITOS
		 */
		
		lblCiudades = new JLabel("DISTRITOS");
		lblCiudades.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCiudades.setBounds(520, 28, 106, 23);
		add(lblCiudades);
		
		/**
		 * Nombre del laber llamado POBLACION
		 */
		
		lblPoblacion = new JLabel("POBLACION");
		lblPoblacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPoblacion.setBounds(82, 95, 141, 23);
		add(lblPoblacion);
		
		
		/**
		 * Cargamos el comboBox llamado cmbPaises
		 * Primero creamos un ArrayList de tipo objeto  Banderas llamado nombrePaises
		 * Segundo almacenamos ese ArrayList nombrePaises llamando al metodo cargaPaises que esta en la  clase base de datos
		 * Tercero usando un bucle for rellenamos todo el comboBox cmbPaises
		 */
		
		cmbPaises = new JComboBox();
		cmbPaises.setBounds(335, 29, 135, 27);
		add(cmbPaises);
		
		ArrayList<Banderas> nombrePaises = new ArrayList<>();
		
		nombrePaises = bases.cargaPaises();
		
		for(int i = 0; i<nombrePaises.size();i++) {
			cmbPaises.addItem(nombrePaises.get(i));
		}
		
		/**
		 * Render para cargar las imagenes de las banderas
		 */
		cmbPaises.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Banderas objeto = (Banderas) value;
                label.setText(objeto.getNombre());
                label.setIcon(objeto.getImagen());
                return label;
            }
        });
		
		/**
		 * En el comboBox paises necesitamos tambien cargar las imagenes de las banderas
		 * Primero Creamos una Accion itemStateChanged el cual hace un evento de cambio en el comboBox
		 * Segundo Creamos un ArrayList de tipo Objeto llamado paises luego instanciamos la clase Banderas 
		 * con el nombre pais, para poder rellenarlo como objeto(En este metodo hice un casting porque por alguna razon
		 * no me dejaba rellenar el pais como string ya que no me convertia el objeto en string a pesar de que tenia el metodo ToString())
		 * Por ultimo rellenamos el arrLPaises con un foreach
		 * 
		 */
		
		cmbPaises.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ArrayList<Paises> arrLPaises = new ArrayList<>();
				Banderas pais = new Banderas();
				pais = (Banderas)cmbPaises.getSelectedItem();

				arrLPaises = bases.cargaTablaPais(pais.getNombre());
				modeloTabla.setRowCount(0);
				for (Paises c : arrLPaises) {
					modeloTabla.addRow(new Object [] {
						c.getContinente(),
						c.getPais(),
						c.getIdioma(),
						c.getDistrito(),
						c.getCiudad(),
						c.getPoblacion()
					});
				}
				
			}
		});
		
		/**
		 * Cargamos el comboBox llamado cmbPaises y juntamente con este cargamos el JTable llamado tablaPaises
		 * Primero creamos un ArrayList de tipo objeto  Banderas llamado arrDistritos
		 * creamos una variable distrito de tipo String para almacenar todo el contenido del cmbDistritos que lo pasamos 
		 * como String mediante el metodo toString
		 * Segundo almacenamos ese ArrayList arrDistritos llamando al metodo de la base de datos cargaTablaDistritos que esta en la  clase base de datos
		 * Tercero usando un bucle for rellenamos todo el ArrayList arrDistritos
		 */
		
		cmbDistritos = new JComboBox();
		cmbDistritos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ArrayList<Paises> arrDistrito = new ArrayList<>();
				String distrito;
				distrito = cmbDistritos.getSelectedItem().toString();
				arrDistrito = bases.cargaTablaDistrito(distrito);
				modeloTabla.setRowCount(0);
				for (Paises c : arrDistrito) {
					modeloTabla.addRow(new Object[] {
							c.getContinente(),
							c.getPais(),
							c.getIdioma(),
							c.getDistrito(),
							c.getCiudad(),
							c.getPoblacion(),

					});

				}
			}
		});
		cmbDistritos.setBounds(601, 29, 153, 28);
		add(cmbDistritos);
		
		/**
		 * Cargamos el comboBox llamado cmbDistritos
		 * Primero creamos un ArrayList de tipo String   llamado nombreCiudades
		 * Segundo almacenamos ese ArrayList nombreCiudades llamando al metodo cargaDistrito que esta en la  clase base de datos
		 * Tercero usando un bucle for rellenamos todo el comboBox cmbDistritos
		 */
		
		
		ArrayList<String> nombreCiudades = new ArrayList<>();
		
		nombreCiudades = bases.cargaDistrito();
		
		for(int i= 0; i<nombreCiudades.size();i++) {
			cmbDistritos.addItem(nombreCiudades.get(i));
		}
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 147, 28);
		add(toolBar);
		
		/**
		 * Boton con el nombre SQL que permite exportar un fichero de tipo SQL
		 * Instanciamos la clase ExportarSQL que la llamamos obj el cual pasamos como parametro el JTable
		 * llamado tablaPaises para obtener su informacion en ese momento
		 */
		
		
		btnSql = new JButton("SQL");
		btnSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportarSQL obj;
				obj = new ExportarSQL();
				obj.ExportarSQL(tablaPaises);
			}
		});
		btnSql.setFont(new Font("Tahoma", Font.BOLD, 11));
		toolBar.add(btnSql);
		
		/**
		 * Boton con el nombre XML que permite exportar un fichero de tipo XML
		 * Instanciamos la clase ExportarXML que la llamamos obj el cual pasamos como parametro el JTable
		 * llamado tablaPaises para obtener su informacion en ese momento
		 */
		
		
		btnXml = new JButton("XML");
		btnXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportarXML obj;
				obj = new ExportarXML();
				obj.CrearXml(tablaPaises, "Registro_Datos");
							
				JOptionPane.showMessageDialog(null, "Fichero XML Creado Correctamente");
			}
			
		});
		
		
		btnXml.setFont(new Font("Tahoma", Font.BOLD, 11));
		toolBar.add(btnXml);
		
		/**
		 * Boton con el nombre EXCEL que permite exportar un fichero de tipo .xls
		 * Instanciamos la clase ExportarEXCEL que la llamamos obj el cual pasamos como parametro el JTable
		 * llamado tablaPaises para obtener su informacion en ese momento
		 */
		
		btnExcel = new JButton("EXCEL");
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportarExcel obj;
				try {
					obj = new ExportarExcel();
					obj.exportarExcel(tablaPaises);
					JOptionPane.showMessageDialog(null, "DOCUMENTO EXCEL creado correctamente");
				} catch (IOException e2) {
					// TODO: handle exception
					System.out.println("Error al exportar : " + e2);
				}
			}
		});
		btnExcel.setFont(new Font("Tahoma", Font.BOLD, 11));
		toolBar.add(btnExcel);
		
		BaseDatos bd = new BaseDatos();
		ArrayList<String> arrLPaises = new ArrayList<>();
		
		/**
		 * Slider para hacer busqueda en el JTable mediante la poblacion
		 * Primero creamos el slider y le damos unos parametros para colocar el inicio, rango, fin etc
		 * creamos una accion de tipo change (cambio) dentro de este creamos un ArrayList de tipo Objeto llamado arrLPoblacion
		 * y una variable de tipo integer llamada poblacion.
		 * la variable poblacion contendra la cantidad de poblacion en el rango que se encuentre el slider,
		 * asi mismo rellenamos el ArrayList arrLPoblacion con el metodo cargaTablaPoblacion que esta en la base de datos
		 * Por ultimo rellenamos el JTable con el bucle foreach que contiene el arrLPoblacion
		 */
		
		table = new JTable();
		sliPoblacion = new JSlider(0,2000000,0);
		sliPoblacion.setMinorTickSpacing(50000);
		sliPoblacion.setPaintTicks(true);
		sliPoblacion.setPaintLabels(true);
		sliPoblacion.setMajorTickSpacing(500000);
		sliPoblacion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ArrayList<Paises> arrLPoblacion = new ArrayList<>();
				int poblacion;
				poblacion = sliPoblacion.getValue();
				arrLPoblacion=bases.cargaTablaPoblacion(poblacion);
				modeloTabla.setRowCount(0);
				
				for (Paises c : arrLPoblacion) {
					modeloTabla.addRow(new Object[] {
							c.getContinente(),
							c.getPais(),
							c.getIdioma(),
							c.getDistrito(),
							c.getCiudad(),
							c.getPoblacion()
					});
				}
			}
		});
	
		sliPoblacion.setBounds(184, 92, 611, 43);
		add(sliPoblacion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 162, 770, 276);
		add(scrollPane);
		
		/**
		 * JTable con un evento llamado mouseClicker que sirve que cuando le hacemos click nos carge dentro de un TexFiel, y dos ComboBOx
		 * la ciudad el distrito y la poblacion respectivamente
		 * Primero creamos una variable de tipo int llamada fiaTabla que almacenara el contenido de la fila en la   tabla en ese momento con un clic
		 * creamos tres variables de tipo String llamadas ciudad, distrito, poblacion para gestionar la posicion de la fila y la columna 
		 * Elimanos el contenido del  combobox distrito y  poblacion
		 * Luego Creamos un ArrayList de tipo String respectivamente en los tres casos para rellenarlo con un bucle for
		 */
		
		tablaPaises = new JTable();
		tablaPaises.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaTabla;
				String ciudad;
				String distrito;
				String poblacion;
				
				filaTabla=tablaPaises.rowAtPoint(e.getPoint());
				distrito = tablaPaises.getValueAt(filaTabla, 3).toString();
				ciudad = tablaPaises.getValueAt(filaTabla, 4).toString();
				poblacion = tablaPaises.getValueAt(filaTabla, 5).toString();
				
				cmboDistrito2.removeAllItems();
				cmbPoblacion.removeAllItems();
				
				ArrayList<String> arrDistritos = new ArrayList<>();
				arrDistritos=bases.cargaDistrito();
				
				for (int i = 0; i < arrDistritos.size(); i++) {
					cmboDistrito2.addItem(arrDistritos.get(i));
				}
				
				ArrayList<String> arrPoblacion = new ArrayList<>();
				arrPoblacion=bd.cargaPoblacion();
				
				for (int i = 0; i < arrPoblacion.size(); i++) {
					cmbPoblacion.addItem(arrPoblacion.get(i));
					
				}
				txtCiudad.setText(ciudad);
				cmboDistrito2.setSelectedItem(distrito);
				cmbPoblacion.setSelectedItem(poblacion);
 				
				
			}
		});
		scrollPane.setViewportView(tablaPaises);
		
		modeloTabla.setColumnIdentifiers(
				new Object[] { "continente", "Pais", "Idioma", "Distrito",  "Ciudad","Poblacion" });
		tablaPaises.setModel(modeloTabla);
		
		setLayout(null);
		
		/**
		 * Con el boton modificar vamos a poder modificar el contenido de la fila del JTable
		 * Primero creamos una variable de tipo int llamada filaTabla para recoger lo que se selecciona en la fila en ese momento
		 * creamos variables de tipo String ciudadNueva,distritoNuevo,ciudadAnterior,distritoAnterior y dos de tipo int llamadas
		 * poblacionNueva, poblacionAnterior.
		 * guardamos respectivamente en esas variables el contenido del JTable el cual por parametro indice (la fila en que este, la columna)
		 * y lo convierte en un String con el metodo toString();
		 * despues pasamos las variables por parametros a un metodo modifcandoDatos que se encuentra en la clase Base de datos
		 */
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaTabla;
				filaTabla=tablaPaises.getSelectedRow();
				String ciudadNueva,distritoNuevo,ciudadAnterior,distritoAnterior;
				int poblacionNueva, poblacionAnterior;
				distritoNuevo=tablaPaises.getValueAt(filaTabla,3).toString();
				ciudadNueva=tablaPaises.getValueAt(filaTabla,4).toString();
				poblacionNueva=Integer.parseInt(tablaPaises.getValueAt(filaTabla,5).toString());
				ciudadAnterior=txtCiudad.getText().toString();
				distritoAnterior=cmboDistrito2.getSelectedItem().toString();
				poblacionAnterior=Integer.parseInt(cmbPoblacion.getSelectedItem().toString());
				bd.modificandoDatos(ciudadNueva, distritoNuevo, poblacionNueva, ciudadAnterior, distritoAnterior, poblacionAnterior);				
			
			}
		});
	
		btnModificar.setBounds(31, 460, 116, 35);
		add(btnModificar);
		
		/**
		 * Con este boton llamado borrar podremos borrar una fila seleccionada en el JTable
		 * Declaramos unas variables de tipo String ciudad,distrito, poblacion
		 * almacenamos el contenido del Jlabel , cmbDistritos2, cmbPoblacion respectivamente
		 * en las variable de tipo String declaradas anteriormente luego
		 * Pasamos por parametro ciudad, distrito, poblacion con un metodo llamado borrandoDatosTabla que esta en la clase
		 * Base de datos y luego eliminamos el cmbDistrios2 
		 */
		
		btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ciudad,distrito, poblacion;
				ciudad = txtCiudad.getText().toString();
				distrito = cmboDistrito2.getSelectedItem().toString();
				poblacion = cmbPoblacion.getSelectedItem().toString();
				bases.borrandoDatosTabla(ciudad, distrito, poblacion);
				modeloTabla.setRowCount(0);
				txtCiudad.setText("");
				cmboDistrito2.removeAllItems();
				cmboDistrito2.removeAllItems();
			}
		});
		
		btnBorrar.setBounds(31, 521, 116, 35);
		add(btnBorrar);
		
		cmboDistrito2 = new JComboBox();
		cmboDistrito2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cmboDistrito2.setBounds(432, 484, 153, 35);
		add(cmboDistrito2);
		
		cmbPoblacion = new JComboBox();
		cmbPoblacion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});
		cmbPoblacion.setBounds(642, 484, 153, 35);
		add(cmbPoblacion);
		
		JLabel lblCiudad = new JLabel("CIUDAD");
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCiudad.setBounds(231, 454, 82, 23);
		add(lblCiudad);
		
		JLabel lblCiudades_1 = new JLabel("DISTRITOS");
		lblCiudades_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCiudades_1.setBounds(432, 454, 106, 23);
		add(lblCiudades_1);
		
		JLabel lblPoblacion_1 = new JLabel("POBLACION");
		lblPoblacion_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPoblacion_1.setBounds(643, 454, 82, 23);
		add(lblPoblacion_1);
		
		txtCiudad = new JTextField();
		txtCiudad.setBounds(231, 484, 153, 35);
		add(txtCiudad);
		txtCiudad.setColumns(10);
	}
	

	
	
		
	
}


	
