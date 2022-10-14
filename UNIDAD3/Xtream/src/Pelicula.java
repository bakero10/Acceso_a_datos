import java.io.Serializable;

public class Pelicula implements Serializable{

	int id;
	String titulo;
	int anyo;
	String descripcion;
	
	public Pelicula(int id, String titulo, int anyo, String descripcion) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.anyo = anyo;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", anyo=" + anyo + ", descripcion=" + descripcion + "]";
	}
}
