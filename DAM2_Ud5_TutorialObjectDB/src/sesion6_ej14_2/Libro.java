package sesion6_ej14_2;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class Libro {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    
    //La anotación @JoinTable no es obligatoria en sí, ya que en caso de no definirse JPA asumirá el nombre de la tabla, columnas, 
    //longitud, etc. Para no quedar a merced de la implementación de JPA, siempre es recomendable definirla, así, tenemos el control total sobre ella.
    /**
     * Hemos definidos las siguientes propiedades de la anotación JoinTable
		name: Nombre de la tabla que será creada físicamente en la base de datos.
		joinColumns: Corresponde al nombre para el ID de la Entidad Book.
		inverseJoinColumns: Corresponde al nombre para el ID de la Entidad Author
    **/
    @JoinTable(
        name = "rel_libros_autores",
        joinColumns = @JoinColumn(name = "FK_LIBRO", nullable = false),
        inverseJoinColumns = @JoinColumn(name="FK_AUTOR", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Autor> autores;
   
    public void addAuthor(Autor autor){
        if(this.autores == null){
            this.autores = new ArrayList<>();
        }
        
        this.autores.add(autor);
    }

    /** GET and SET */
    public void setNombre(String nombre) {
    	this.nombre=nombre;
    }
    public List<Autor> getAutores() {
    	return autores;
    }
    
    @Override
    public String toString() {
    	return "Nombre libro: " + nombre + ", ID: "+id;
    }
  
}
