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
	private Libro libro;
	//Base de datos
	Connection conexion = null; //maneja la conexión
	Statement instruccion = null; //instrucción de consulta
	ResultSet resultados = null; //maneja los resultados
		

	public ControladorDB() {
		//Nos conectamos a la base de datos
		crearConexion();
	}
		
	public void crearConexion(){
		//Conectamos a la base de datos
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//establece la conexión a la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
		}catch( SQLException excepcionSql ){
			excepcionSql.printStackTrace();
		}// fin de catch
		catch( ClassNotFoundException noEncontroClase ) {
			noEncontroClase.printStackTrace();
		}
	}
	
	//Método para consultar la tabla libro de la DB
	public void leerLibros(Libro libro, JComboBox<String> listaLibros) {
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
	
	//Método para consultar la tabla musica de la DB
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
	
	
	
	//Método para insertar un nuevo libro en la base de datos
	public void insertarLibro(int idLibro, String titulo, String autor, String genero, String editorial, JComboBox<String> listaLibros) {
		//Realiza la consulta
		try {
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		String slq_ins="INSERT INTO libros(idLibro, titulo, autor, genero, editorial)VALUES("+idLibro+",'"+titulo+"','"+autor+"','"+genero+"','"+editorial+"')";
		instruccion.executeUpdate(slq_ins);	
		
		//Actualización de comboBox
		listaLibros.removeAllItems();
		leerLibros(libro, listaLibros);
		
		
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
		
		//Actualización de comboBox
		listaMusica.removeAllItems();
		leerMusica(listaMusica);
		
		
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
	
	


	//Método para eliminar un libro de la DB
	public void borrarLibro(JComboBox<String> listaLibros) {
		int id = listaLibros.getSelectedIndex();
		
		try {
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		String slq="DELETE FROM libros WHERE idLibro="+id;	
		//Elimina entrada de comboBox
		listaLibros.removeItemAt(id);
		//Elimina libro
		instruccion.executeUpdate(slq);
		
		//Actualización del combobox
		listaLibros.removeAllItems();
		leerLibros(libro,listaLibros);
		
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
		
	public String toString() {
		return nombre;
	}
}
		

	


