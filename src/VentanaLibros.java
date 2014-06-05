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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class VentanaLibros extends JFrame {

	private JPanel contentPane;
	private	JTextField textoTitulo;
	private JTextField textoAutor;
	private JTextField textoGenero;
	private JTextField textoEd;
	private JButton guardarBtn;
	private JButton eliminarBtn;
	private JButton consultarBtn;
	private JComboBox<String> listaLibros;
	private ArrayList<Libro> misLibros;
	private Libro miLibro;
	private String nombre;
	
	private ControladorDB conect=new ControladorDB();
	Connection conexion = null; //maneja la conexión
	Statement instruccion = null; //instrucción de consulta
	ResultSet resultados = null; //maneja los resultados
	


	//Constructor de la clase Libros
	public VentanaLibros() {
		
		iniciarVentana();
		conect.leerLibros(miLibro, listaLibros);				
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

		
		guardarBtn = new JButton("Guardar");
		guardarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Llamada al método de insertar libros pasandole lo escrito en los campos de texto
				conect.insertarLibro(listaLibros.getSelectedIndex(),textoTitulo.getText(), textoAutor.getText(), textoGenero.getText(), textoEd.getText(), listaLibros);	
			}
		});
		guardarBtn.setBounds(37, 248, 94, 23);
		contentPane.add(guardarBtn);
		
		eliminarBtn = new JButton("Eliminar");
		eliminarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				conect.borrarLibro(listaLibros);
			}
		});
		eliminarBtn.setBounds(288, 248, 108, 23);
		contentPane.add(eliminarBtn);
		
		consultarBtn = new JButton("Consultar");
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				consultarLibros();
			}
		});
		consultarBtn.setBounds(164, 248, 89, 23);
		contentPane.add(consultarBtn);
	  	
	}
	
	public void consultarLibros() {
		textoTitulo.setText("");
		textoAutor.setText("");
		textoGenero.setText("");
		textoEd.setText("");
		int id = listaLibros.getSelectedIndex()-1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
			Statement comando=conexion.createStatement();
			ResultSet registro = comando.executeQuery("SELECT * FROM libros WHERE idLibro="+id);
			if (registro.next()==true) {
				textoTitulo.setText(registro.getString("titulo"));
				textoAutor.setText(registro.getString("autor"));
				textoGenero.setText(registro.getString("genero"));
				textoEd.setText(registro.getString("editorial"));
			}
			conexion.close();
		} catch(SQLException | ClassNotFoundException  ex){
			setTitle(ex.toString());
		}
			
	}
	
	public String toString() {
		return nombre;
	}
	
}

