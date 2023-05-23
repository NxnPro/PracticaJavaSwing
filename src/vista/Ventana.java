package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import vista.Consultar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Este es el JFrame principal llamado Ventana que contiene las otras clases como Insertar y Consultar
 * @author Nixon
 *
 */

public class Ventana extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void Inicio(Ventana frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setBackground(new Color(220, 220, 220));
		setFont(new Font("Roboto Black", Font.PLAIN, 12));
		
		Toolkit miSistema = Toolkit.getDefaultToolkit();
		Image miIcono = miSistema.getImage("src/Imagenes/Logo.jpg");
		setIconImage(miIcono);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("OPCIONES");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("INSERTAR");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insertar c = new Insertar();
				nuevoPanel(c);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("CONSULTAR");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consultar c = new Consultar();
				nuevoPanel(c);
			}
		});
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("|");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("ACERCA DE...");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombre, apellido, curso, profesor, mensaje;
				int anio;
				
				nombre = "Nixon Dinner";
				apellido = "Cruzado Vasquez";
				curso = "Programacion";
				profesor ="Gonzalo de Tomás";
				anio = 2023;
				
				mensaje = "Nombre: " + nombre + "\nApellido: " + apellido + "\nCurso: " + curso + "\nProfesor: " + profesor + "\nAño: " + anio; 
				
				JOptionPane.showMessageDialog(null, mensaje);
			}
		});
		mnNewMenu_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("BIENVENIDO");
		lblNewLabel.setIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/favicon.png")));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 165, 0));
		lblNewLabel.setFont(new Font("Roboto Black", Font.BOLD, 70));
		contentPane.add(lblNewLabel, "name_506023195128100");
	}
	
	public void nuevoPanel(JPanel panelActual) {
		contentPane.removeAll();
		contentPane.add(panelActual);
		contentPane.repaint();
		contentPane.revalidate();
	}
}
