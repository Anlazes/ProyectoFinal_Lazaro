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


public class VentanaMusica extends JFrame {

	private JPanel contentPane;
	private JTextField textoGrupo;
	private JTextField textoTitulo;
	private JTextField textoAnyo;
	private JTextField textoGenero;
	private JButton guardarBtn;
	private JButton eliminarBtn;
	private JButton consultarBtn;
	private JComboBox<String> listaMusica;
	
	Connection conexion = null; //maneja la conexión
	Statement instruccion = null; //instrucción de consulta
	ResultSet resultados = null; //maneja los resultados
	
	private ControladorDB conect=new ControladorDB();
	


	//Constructor de la ventana Musica
	public VentanaMusica() {
		iniciarVentana();
		conect.leerMusica(listaMusica);
	}
		
	public void iniciarVentana() {
		
		setTitle("Biblioteca M\u00FAsica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listaMusica = new JComboBox<String>();
		listaMusica.setBounds(26, 38, 380, 20);
		contentPane.add(listaMusica);
		
		JLabel lblGrupo = new JLabel("Grupo/Autor");
		lblGrupo.setBounds(26, 86, 76, 14);
		contentPane.add(lblGrupo);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setBounds(26, 126, 46, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblAnyo = new JLabel("A\u00F1o");
		lblAnyo.setBounds(26, 169, 46, 14);
		contentPane.add(lblAnyo);
		
		textoGrupo = new JTextField();
		textoGrupo.setBounds(107, 83, 299, 20);
		contentPane.add(textoGrupo);
		textoGrupo.setColumns(10);
		
		textoTitulo = new JTextField();
		textoTitulo.setBounds(107, 123, 299, 20);
		contentPane.add(textoTitulo);
		textoTitulo.setColumns(10);
		
		textoAnyo = new JTextField();
		textoAnyo.setBounds(107, 166, 299, 20);
		contentPane.add(textoAnyo);
		textoAnyo.setColumns(10);
		
		JLabel lblGenero = new JLabel("G\u00E9nero");
		lblGenero.setBounds(26, 212, 46, 14);
		contentPane.add(lblGenero);
		
		textoGenero = new JTextField();
		textoGenero.setBounds(107, 209, 299, 20);
		contentPane.add(textoGenero);
		textoGenero.setColumns(10);
		
		guardarBtn = new JButton("Guardar");
		guardarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Seleccionamos el último indice de comboBox para asignarlo al idDisco
				int id= listaMusica.getItemCount()-1; 
				
				//Llamada al método de insertar discos pasandole lo escrito en los campos de texto
				conect.insertarDisco(id, textoGrupo.getText(), textoTitulo.getText(), textoAnyo.getText(), textoGenero.getText(), listaMusica);
				
				//Limpia los campos de texto una vez guardado el nuevo disco
				textoGrupo.setText("");
				textoTitulo.setText("");
				textoAnyo.setText("");
				textoGenero.setText("");
				
			}
		});
		guardarBtn.setBounds(26, 250, 89, 23);
		contentPane.add(guardarBtn);
		
		eliminarBtn = new JButton("Eliminar");
		eliminarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		eliminarBtn.setBounds(317, 250, 89, 23);
		contentPane.add(eliminarBtn);
		
		consultarBtn = new JButton("Consultar");
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consularDisco();
			}
		});
		consultarBtn.setBounds(170, 250, 89, 23);
		contentPane.add(consultarBtn);
	}
	
	public void consularDisco () {
		textoGrupo.setText("");
		textoTitulo.setText("");
		textoGenero.setText("");
		textoAnyo.setText("");
		int id=listaMusica.getSelectedIndex()-1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/biblioteca","root","");
			Statement comando=conexion.createStatement();
			ResultSet res = comando.executeQuery("SELECT * FROM musica WHERE idDisco="+id);
			if (res.next()==true) {
				textoGrupo.setText(res.getString("grupo"));
				textoTitulo.setText(res.getString("titulo"));
				textoGenero.setText(res.getString("genero"));
				textoAnyo.setText(res.getString("anyo"));
			}
			conexion.close();
		} catch(SQLException | ClassNotFoundException  ex){
			setTitle(ex.toString());
		}
	
	}
}
