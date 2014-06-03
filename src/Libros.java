import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Libros extends JFrame {

	private JPanel contentPane;
	private JTextField textoTitulo;
	private JTextField textoAutor;
	private JTextField textoGenero;
	private JTextField textoEd;
	private JComboBox<String> listaLibros;
	
	private ControladorDB conect=new ControladorDB();
	private JButton guardarBtn;
	private JButton eliminarBtn;

	//Constructor de la clase Libros
	public Libros() {
		
		iniciarVentana();
		conect.leerLibros(listaLibros);
	}	
	
	//Constructor de la ventana Libros
	public void iniciarVentana() {
		
		setTitle("Biblioteca Libros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listaLibros = new JComboBox<String>();
		listaLibros.setBounds(37, 29, 359, 20);
		contentPane.add(listaLibros);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(37, 122, 46, 14);
		contentPane.add(lblAutor);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setBounds(37, 85, 46, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setBounds(37, 201, 46, 14);
		contentPane.add(lblEditorial);
		
		JLabel lblGenero = new JLabel("G\u00E9nero");
		lblGenero.setBounds(37, 160, 46, 14);
		contentPane.add(lblGenero);
		
		textoTitulo = new JTextField();
		textoTitulo.setBounds(111, 82, 285, 20);
		contentPane.add(textoTitulo);
		textoTitulo.setColumns(10);
		
		textoAutor = new JTextField();
		textoAutor.setBounds(111, 119, 285, 20);
		contentPane.add(textoAutor);
		textoAutor.setColumns(10);
		
		textoGenero = new JTextField();
		textoGenero.setBounds(111, 157, 285, 20);
		contentPane.add(textoGenero);
		textoGenero.setColumns(10);
		
		textoEd = new JTextField();
		textoEd.setBounds(111, 198, 285, 20);
		contentPane.add(textoEd);
		textoEd.setColumns(10);
		
		guardarBtn = new JButton("Guardar Libro");
		guardarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Llamada al método de insertar libros pasandole lo escrito en los campos de texto
				conect.insertarLibro(textoTitulo.getText(), textoAutor.getText(), textoGenero.getText(), textoEd.getText(), listaLibros);			
			}
		});
		guardarBtn.setBounds(37, 248, 117, 23);
		contentPane.add(guardarBtn);
		
		eliminarBtn = new JButton("Eliminar Libro");
		eliminarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		eliminarBtn.setBounds(270, 248, 126, 23);
		contentPane.add(eliminarBtn);
	}
}
