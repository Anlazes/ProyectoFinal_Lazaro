import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Musica extends JFrame {

	private JPanel contentPane;
	private JTextField textoGrupo;
	private JTextField textoTitulo;
	private JTextField textoAnyo;
	private JTextField textoGenero;
	private JComboBox<String> comboBox;
	
	private ControladorDB conect=new ControladorDB();

	//Constructor de la ventana Musica
	public Musica() {
		setTitle("Biblioteca M\u00FAsica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 294);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(26, 38, 380, 20);
		contentPane.add(comboBox);
		
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
	}

}
