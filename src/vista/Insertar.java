package vista;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import modelo.Banderas;
import modelo.BaseDatos;
import modelo.Paises;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;

/**
 * Esta clase permite definir caracteristicas, eventos y acciones para un JPanel llamado Insertar
 * @author Nixon
 *
 */

public class Insertar extends JPanel {
	private JTextField textNombre;
	private JSlider sliPoblacion;
	private JComboBox cmbDistrito;
	private JComboBox cmbPais;
	private JLabel lblPoblacionCantidad;

	/**
	 * Create the panel.
	 */
	public Insertar() {
		setBackground(new Color(112, 128, 144));
		setLayout(null);
		BaseDatos bases = new BaseDatos();
		
		ArrayList<String> arrLDistrito = new ArrayList<>();
		
		
		textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ArrayList<String> arrLNombre = new ArrayList<>();
				String nombre;
				nombre = textNombre.getText().toString();
			}
		});
		textNombre.setBounds(292, 109, 236, 31);
		add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("INSERTAR NUEVA CIUDAD");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(175, 23, 422, 39);
		add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(146, 109, 104, 31);
		add(lblNombre);
		
		
		/**
		 * Cargamos el comboBox llamado cmbPais
		 * Primero creamos un ArrayList de tipo objeto  Banderas llamado nombrePaises
		 * Segundo almacenamos ese ArrayList nombrePaises llamando al metodo cargaPaises que esta en la  clase base de datos
		 * Tercero usando un bucle for rellenamos todo el comboBox cmbPais
		 */
		
		cmbPais = new JComboBox();
		cmbPais.addItem("PAIS");
		
		ArrayList<Banderas> nombrePaises = new ArrayList<>();
		nombrePaises = bases.cargaPaises();
		
		for(int i = 0; i<nombrePaises.size();i++) {
			cmbPais.addItem(nombrePaises.get(i).getNombre());
		}
		
		
		
		cmbPais.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cmbDistrito.addItem("DISTRITO");
				cmbDistrito.removeAllItems();
				ArrayList<String> nombreDistrito = new ArrayList<>();
				String aux;
								
				aux = cmbPais.getSelectedItem().toString();
				nombreDistrito=bases.cargaDistritos(aux);
				
				for(int i = 0; i<nombreDistrito.size();i++) {
					cmbDistrito.addItem(nombreDistrito.get(i));
				}
				
			}
		});
			
		
		cmbPais.setBounds(292, 172, 236, 31);
		add(cmbPais);
		
		
		JLabel lblPais = new JLabel("PAIS");
		lblPais.setHorizontalAlignment(SwingConstants.CENTER);
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPais.setBounds(146, 172, 104, 31);
		add(lblPais);
		
		
		/**
		 * Cargamos el comboBox llamado cmbDistrito
		 * Primero creamos un ArrayList de tipo String   llamado nombreCiudades
		 * Segundo almacenamos ese ArrayList nombreCiudades llamando al metodo cargaDistrito que esta en la  clase base de datos
		 * Tercero usando un bucle for rellenamos todo el comboBox cmbDistritos
		 */
		
		cmbDistrito = new JComboBox();
		cmbDistrito.addItem("DISTRITO");
		
		ArrayList<String> nombreCiudades = new ArrayList<>();
		
		nombreCiudades = bases.cargaDistrito();
		
		for(int i= 0; i<nombreCiudades.size();i++) {
			cmbDistrito.addItem(nombreCiudades.get(i));
		}
		cmbDistrito.setBounds(292, 231, 236, 31);
		add(cmbDistrito);
		
		
		
		JLabel lblDistrito = new JLabel("DISTRITO");
		lblDistrito.setHorizontalAlignment(SwingConstants.CENTER);
		lblDistrito.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDistrito.setBounds(146, 228, 104, 31);
		add(lblDistrito);
		
		
		/**
		 * Slider para cambiar la poblacion de las ciudad que deseamos agregar
		  * creamos una accion de tipo change (cambio) dentro de este creamos un ArrayList de tipo String llamado arrLPoblacion
		 * y una variable de tipo integer llamada poblacion.
		 * la variable poblacion contendra la cantidad de poblacion en el rango que se encuentre el slider,
		 * por ultimo rellenamos el arrLPoblacion llamando al metodo cargaPoblacion que esta en la clase Base de datos
		 */
		
		sliPoblacion = new JSlider(0,2000000,0);
		sliPoblacion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblPoblacionCantidad.setText("Cantidad: " + sliPoblacion.getValue());
				ArrayList<String> arrLPoblacion = new ArrayList<>();
				int poblacion;
				poblacion = sliPoblacion.getValue();
				arrLPoblacion=bases.cargaPoblacion();
			}
		});
		sliPoblacion.setMinorTickSpacing(50000);
		sliPoblacion.setMajorTickSpacing(500000);
		sliPoblacion.setPaintTicks(true);
		sliPoblacion.setPaintLabels(true);
		sliPoblacion.setSnapToTicks(true);
		
		sliPoblacion.setBounds(292, 322, 341, 48);
		add(sliPoblacion);
		
		/**
		 * label llamado poblacion
		 */
		
		JLabel lblPoblacion = new JLabel("POBLACION");
		lblPoblacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoblacion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPoblacion.setBounds(121, 328, 129, 31);
		add(lblPoblacion);
		
		/**
		 * Boton de insertar nueva ciudad
		 * creamos tres variables de tipo String y una variable de tipo int ciudad,pais,distrito luego poblacion respectivamente
		 * en ciudad almacenamos lo que contenga el textField
		 * en pais almacenamos lo que tiene el comboBox cmbPais de tipo String
		 * en distrito almacenamos lo que tiene el comboBox cmbDistrito de tipo String
		 * poblacion almacenamos lo que tiene el comboBox cmbPoblacion de tipo String
		 * luego pasamos por parametro las variables a un metodo que esta en la clase base de datos llamado insertarFilaBaseDeDatos
		 */
		
		JButton btnNewButton = new JButton("INSERTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ciudad,pais,distrito;
				int poblacion;
				
				ciudad = textNombre.getText().toString();
				pais=cmbPais.getSelectedItem().toString();
				distrito = cmbDistrito.getSelectedItem().toString();
				poblacion = sliPoblacion.getValue();
				bases.insertarFilaBaseDeDatos(ciudad, pais, distrito, poblacion);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(317, 423, 191, 39);
		add(btnNewButton);
		
		lblPoblacionCantidad = new JLabel("");
		lblPoblacionCantidad.setBounds(654, 328, 146, 31);
		add(lblPoblacionCantidad);

	}
}
