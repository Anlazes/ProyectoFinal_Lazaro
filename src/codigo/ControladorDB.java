package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JComboBox;


public class ControladorDB {
	
	private String nombre;

	
	//Base de datos
	Connection conexion = null; //maneja la conexi�n
	Statement instruccion = null; //instrucci�n de consulta
	ResultSet resultados = null; //maneja los resultados
		

	public ControladorDB() {
		//Nos conectamos a la base de datos
		crearConexion();
	}
	
	//M�todo para conectar a la base de datos
	public void crearConexion(){	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//establece la conexi�n a la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
		} catch( SQLException excepcionSql ){
			excepcionSql.printStackTrace();
		}
		  catch( ClassNotFoundException noEncontroClase ) {
			noEncontroClase.printStackTrace();
		}
	}
	
	//M�todo para consultar la tabla libro de la DB
	public void leerLibros(JComboBox<String> listaLibros) {
		try {		
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		//consulta la base de datos
		resultados = instruccion.executeQuery("SELECT titulo FROM libros");		
			while (resultados.next()) { 		
			this.nombre=(String)resultados.getObject("titulo");
		
			listaLibros.addItem(nombre);
			}
			
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
	
	//M�todo para consultar la tabla musica de la DB
	public void leerMusica(JComboBox<String> listaMusica) {
		try {		
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		//consulta la base de datos
		resultados = instruccion.executeQuery("SELECT titulo FROM musica");		
			while (resultados.next()) { 		
			this.nombre=(String)resultados.getObject("titulo");
		
			listaMusica.addItem(nombre);
			}
			
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
		
	
	//M�todo para insertar un nuevo libro en la base de datos
	public void insertarLibro(int idLibro, String titulo, String autor, String genero, String editorial, JComboBox<String> listaLibros) {
		//Realiza la consulta
		try {
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		String slq_ins="INSERT INTO libros(idLibro, titulo, autor, genero, editorial)VALUES("+idLibro+",'"+titulo+"','"+autor+"','"+genero+"','"+editorial+"')";
		instruccion.executeUpdate(slq_ins);	
		
		//Actualizaci�n de comboBox
		listaLibros.removeAllItems();
		leerLibros(listaLibros);
		
		
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
	
	public void insertarDisco(int idDisco, String grupo, String titulo, String anyo, String genero, JComboBox<String> listaMusica) {
		//Realiza la consulta
		try {
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		String slq_ins="INSERT INTO musica(idDisco, grupo, titulo, genero, anyo)VALUES("+idDisco+",'"+grupo+"','"+titulo+"','"+anyo+"','"+genero+"')";
		instruccion.executeUpdate(slq_ins);	
		
		//Actualizaci�n de comboBox
		listaMusica.removeAllItems();
		leerMusica(listaMusica);
		
		
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
	
	


	//M�todo para eliminar un libro de la DB
	public void borrarLibro(JComboBox<String> listaLibros) {
		int id = listaLibros.getSelectedIndex()-1;
		
		try {
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		String slq="DELETE FROM libros WHERE idLibro="+id;	
		//Elimina entrada de comboBox
		listaLibros.removeItemAt(id);
		//Elimina libro
		instruccion.execute(slq);
		
		//Actualizaci�n del combobox
		listaLibros.removeAllItems();
		leerLibros(listaLibros);
		
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
	
	//M�todo para eliminar un disco de la DB
		public void borrarDisco(JComboBox<String> listaMusica) {
			int id = listaMusica.getSelectedIndex()-1;
			
			try {
			//crea objeto Statement para consultar la base de datos	
			instruccion = (Statement) conexion.createStatement();
			String slq="DELETE FROM musica WHERE idDisco="+id;	
			//Elimina entrada de comboBox
			listaMusica.removeItemAt(id);
			//Elimina disco
			instruccion.execute(slq);
			
			//Actualizaci�n del combobox
			listaMusica.removeAllItems();
			leerMusica(listaMusica);
			
			} catch (SQLException slqex) {
				slqex.printStackTrace();
			}
		}
	
	
	
	public String toString() {
		return nombre;
	}
}
		

	


