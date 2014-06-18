package codigo;



public class Libro {
	
	private int idLibro;
	private String titulo;
	private String autor;
	private String genero;
	private String editorial;

	public Libro() {
		
		idLibro=0;
		titulo="";
		autor="";
		genero="";
		editorial="";				
	}

	public Libro(int idLibro, String titulo, String autor, String genero, String editorial) {
		
		this.idLibro=idLibro;
		this.titulo=titulo;
		this.autor=autor;
		this.genero=genero;
		this.editorial=editorial;
	}
	
	public void setIdLibro(int idLibro) {
		this.idLibro=idLibro;
	}
	
	public void setTitulo(String titulo) {
		this.titulo=titulo;
	}
	
	public void setAutor(String autor) {
		this.autor=autor;
	}
	
	public void setGenero(String genero) {
		this.genero=genero;
	}
	public void setEditorial(String editorial) {
		this.editorial=editorial;
	}
	
	public int getIdLibro() {
		return idLibro;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public String getEditorial() {
		return editorial;
	}
	
	public String toString() {
		return titulo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 }
