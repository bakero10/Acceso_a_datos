package sesion6_ej14_3;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Docent")
public class PersonalDocente extends Personal {
	private String cualificacion;
	private String experienciaMateria;

	public PersonalDocente(int sid, String snombre, String cualificacion, String experienciaMateria) {
		super(sid, snombre);
		this.cualificacion = cualificacion;
		this.experienciaMateria = experienciaMateria;
	}

	public PersonalDocente() {
		super();
	}

	public String getCualification() {
		return cualificacion;
	}

	public void setCualification(String cualification) {
		this.cualificacion = cualification;
	}

	public String getExperienciaMateria() {
		return experienciaMateria;
	}

	public void setExperienciaMateria(String experienciaMateria) {
		this.experienciaMateria = experienciaMateria;
	}
}
