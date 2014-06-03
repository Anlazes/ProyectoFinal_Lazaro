import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Principal extends JFrame {

	private JPanel contentPane;

	//Lanzador de la aplicación
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Constructor de la ventana Principal
	public Principal() {
		setTitle("Biblioteca Multimedia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel imagen = new JLabel("New label");
		imagen.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/multimedia-icons.jpg")));
		imagen.setBounds(10, 0, 503, 194);
		contentPane.add(imagen);
		
		JButton gestionBt = new JButton("Mi Biblioteca");
		gestionBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirBiblio();  //Abre la ventana Biblioteca al pulsar el botón
			}
		});
		gestionBt.setBounds(106, 236, 304, 44);
		contentPane.add(gestionBt);
	}
	
	//Método para abrir la Ventana Biblioteca
	public void abrirBiblio() {
		Biblioteca frameBiblio = new Biblioteca();
		frameBiblio.setVisible(true);
		this.dispose();
	}
}
