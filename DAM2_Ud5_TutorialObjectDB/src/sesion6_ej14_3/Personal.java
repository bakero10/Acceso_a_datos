package sesion6_ej14_3;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//Esto lo usamos para poder usar un discriminador, en la clase padre le damos el nombre al discriminador y el las hijas le damos valor
//con lo cual luego fisicamente en la base de datos se creara una colmna nueva llamada tipo que ira que tendra el valor docent o nodocent, segun la clase hija que
//construlla dicho registro. Esto es util si luego utilizamos clausualas where para poder discriminar y que te muestre un resultado u otro segun el tipo
@DiscriminatorColumn(name = "tipo")
public class Personal implements Serializable { //nos fijamos que las relaciones tamnbien incluyen la herencia
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sid;
	private String snombre;

	public Personal(int sid, String snombre) {
		super();
		this.sid = sid;
		this.snombre = snombre;
	}

	public Personal() {
		super();
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSnombre() {
		return snombre;
	}

	public void setSnombre(String snombre) {
		this.snombre = snombre;
	}
}
