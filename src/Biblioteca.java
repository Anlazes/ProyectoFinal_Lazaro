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
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;


public class Biblioteca extends JFrame {

	private JPanel contentPane;

	//Constructor de la ventana Biblioteca
	public Biblioteca() {
		setBackground(Color.WHITE);
		
		
		setTitle("Biblioteca Multimedia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 388);
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
		musicaBtn.setBounds(310, 133, 176, 23);
		contentPane.add(musicaBtn);
		
		JButton juegosBtn = new JButton("Peliculas");
		juegosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		juegosBtn.setBounds(310, 299, 176, 23);
		contentPane.add(juegosBtn);
		
		JButton librosBtn = new JButton("Libros");
		librosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//Abre la ventana Libros
					abrirLibros();
			}
		});
		librosBtn.setBounds(47, 299, 176, 23);
		contentPane.add(librosBtn);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Biblioteca.class.getResource("/Imagenes/libros.jpg")));
		lblNewLabel.setBounds(0, -3, 268, 325);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Biblioteca.class.getResource("/Imagenes/musica.jpg")));
		lblNewLabel_1.setBounds(238, -3, 288, 188);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(Biblioteca.class.getResource("/Imagenes/pelicula.jpg")));
		lblNewLabel_2.setBounds(298, 202, 257, 169);
		contentPane.add(lblNewLabel_2);
	}
	
	//Método para abrir la ventana Libros
	public void abrirLibros() {
		VentanaLibros frameLibros = new VentanaLibros();
		frameLibros.setVisible(true);
		frameLibros.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	//Método para abrir la ventana Musica
	public void abrirMusica() {
		VentanaMusica frameMusica = new VentanaMusica();
		frameMusica.setVisible(true);
		frameMusica.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
