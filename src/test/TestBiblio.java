package test;

import static org.junit.Assert.*;

import javax.swing.JComboBox;

import org.junit.Before;
import org.junit.Test;

import codigo.ControladorDB;
import codigo.VentanaLibros;
import codigo.Biblioteca;

public class TestBiblio {
	
	ControladorDB conect=new ControladorDB();
	Biblioteca biblio=new Biblioteca();
	VentanaLibros libros=new VentanaLibros();
	private JComboBox<String> listaLibros;

	@Before
	public void setUp() throws Exception {
		biblio.abrirLibros();
		libros.iniciarVentana();
		conect.crearConexion();
	}
	

	@Test
	public void test() {
		libros.consultarLibros();
	}	

}
