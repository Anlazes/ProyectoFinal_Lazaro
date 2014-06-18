import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



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
	private String nombre;
	
	
	private ControladorDB conect=new ControladorDB();
	Connection conexion = null; //maneja la conexi�n
	Statement instruccion = null; //instrucci�n de consulta
	ResultSet resultados = null; //maneja los resultados



	//Constructor de la clase Libros
	public VentanaLibros() {
		
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
		listaLibros.setSelectedIndex(-1);
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
				
				//Seleccionamos el �ltimo indice de comboBox para asignarlo al idLibro
				int id= listaLibros.getItemCount()-1; 
				
				//Llamada al m�todo de insertar libros pasandole lo escrito en los campos de texto				
				conect.insertarLibro(id, textoTitulo.getText(), textoAutor.getText(), textoGenero.getText(), textoEd.getText(), listaLibros);
				
				//Limpia los campos de texto una vez guardado el nuevo libro
				textoTitulo.setText("");
				textoAutor.setText("");
				textoGenero.setText("");
				textoEd.setText("");
				
				
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
				//Llamada al m�todo para consultar la informaci�n del libro seleccionado en combobox
				consultarLibros();
			}
		});
		consultarBtn.setBounds(164, 248, 89, 23);
		contentPane.add(consultarBtn);
	  	
	}
	
	//M�todo para consultar la informaci�n del libro seleccionado
	public void consultarLibros() {
		//Ponemos los campos de texto en blanco
		textoTitulo.setText("");
		textoAutor.setText("");
		textoGenero.setText("");
		textoEd.setText("");
		int id=listaLibros.getSelectedIndex()-1; //variable donde guardamos el indice seleccionado en comboBox
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			//Conectamos a la base de datos
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
			Statement comando=conexion.createStatement();
			//Realizamos la consulta
			ResultSet res = comando.executeQuery("SELECT * FROM libros WHERE idLibro="+id);
			//Si hay resultado positivo a la consulta llenamos los campos de texto con el resultado
			if (res.next()==true) {
				textoTitulo.setText(res.getString("titulo"));
				textoAutor.setText(res.getString("autor"));
				textoGenero.setText(res.getString("genero"));
				textoEd.setText(res.getString("editorial"));
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

