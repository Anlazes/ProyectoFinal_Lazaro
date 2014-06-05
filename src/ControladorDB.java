import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JComboBox;


public class ControladorDB {
	
	private String nombre;
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
	
	//Método para consultar base de datos
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
	
	
	
	//Método para insertar un nuevo libro en la base de datos
	public void insertarLibro(String titulo, String autor, String genero, String editorial, JComboBox<String> listaLibros) {
		//Realiza la consulta
		try {
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		String slq_ins="INSERT INTO libros(titulo, autor, genero, editorial) VALUES ('"+titulo+"','"+autor+"','"+genero+"','"+editorial+"')";
		instruccion.executeUpdate(slq_ins);
		//Actualización del combobox
		listaLibros.removeAllItems();
		leerLibros(listaLibros);
		
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
	


	//Método para eliminar un libro de la DB
	public void borrarLibro(JComboBox<String> listaLibros) {
		//Realiza la consulta
		try {
		//crea objeto Statement para consultar la base de datos	
		instruccion = (Statement) conexion.createStatement();
		int id = listaLibros.getSelectedIndex();
		String slq_ins="DELETE FROM libros WHERE titulo="+id;	
		instruccion.executeUpdate(slq_ins);
		//Actualización del combobox
		listaLibros.removeItemAt(id);
		leerLibros(listaLibros);
		
		} catch (SQLException slqex) {
			slqex.printStackTrace();
		}
	}
		
	public String toString() {
		return nombre;
	}
}
		

	


