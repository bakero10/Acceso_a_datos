package sesion6_ej14_3;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "NoDocent")
public class PersonalNoDocente extends Personal {
	private String areaEspecializacion;

	public PersonalNoDocente(int sid, String snombre, String areaEspecializacion) {
		super(sid, snombre);
		this.areaEspecializacion = areaEspecializacion;
	}

	public PersonalNoDocente() {
		super();
	}

	public String getAreaEspecializacion() {
		return areaEspecializacion;
	}

	public void setAreaEspecializacion(String areaEspecializacion) {
		this.areaEspecializacion = areaEspecializacion;
	}
}
