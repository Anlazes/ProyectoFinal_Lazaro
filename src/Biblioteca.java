import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;


public class Biblioteca extends JFrame {

	private JPanel contentPane;

	//Constructor de la ventana Biblioteca
	public Biblioteca() {
		
		
		setTitle("Biblioteca Multimedia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton musicaBtn = new JButton("M\u00FAsica");
		musicaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Abre la ventana Musica
				abrirMusica(); 
			}
		});
		musicaBtn.setBounds(52, 37, 176, 23);
		contentPane.add(musicaBtn);
		
		JButton juegosBtn = new JButton("Juegos");
		juegosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		juegosBtn.setBounds(52, 90, 176, 23);
		contentPane.add(juegosBtn);
		
		JButton librosBtn = new JButton("Libros");
		librosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//Abre la ventana Libros
					abrirLibros();
			}
		});
		librosBtn.setBounds(52, 148, 176, 23);
		contentPane.add(librosBtn);
	}
	
	//M�todo para abrir la ventana Libros
	public void abrirLibros() {
		VentanaLibros frameLibros = new VentanaLibros();
		frameLibros.setVisible(true);
		frameLibros.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//M�todo para abrir la ventana Musica
	public void abrirMusica() {
		VentanaMusica frameMusica = new VentanaMusica();
		frameMusica.setVisible(true);
		frameMusica.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
