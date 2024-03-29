package clases;
// Generated 17 nov 2022 9:58:53 by Hibernate Tools 5.4.33.Final

/**
 * Partidos generated by hbm2java
 */
public class Partidos implements java.io.Serializable {

	private int codigo;
	private Equipos equiposByEquipoVisitante;
	private Equipos equiposByEquipoLocal;
	private Integer puntosLocal;
	private Integer puntosVisitante;
	private String temporada;

	public Partidos() {
	}

	public Partidos(int codigo) {
		this.codigo = codigo;
	}

	public Partidos(int codigo, Equipos equiposByEquipoVisitante, Equipos equiposByEquipoLocal, Integer puntosLocal,
			Integer puntosVisitante, String temporada) {
		this.codigo = codigo;
		this.equiposByEquipoVisitante = equiposByEquipoVisitante;
		this.equiposByEquipoLocal = equiposByEquipoLocal;
		this.puntosLocal = puntosLocal;
		this.puntosVisitante = puntosVisitante;
		this.temporada = temporada;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Equipos getEquiposByEquipoVisitante() {
		return this.equiposByEquipoVisitante;
	}

	public void setEquiposByEquipoVisitante(Equipos equiposByEquipoVisitante) {
		this.equiposByEquipoVisitante = equiposByEquipoVisitante;
	}

	public Equipos getEquiposByEquipoLocal() {
		return this.equiposByEquipoLocal;
	}

	public void setEquiposByEquipoLocal(Equipos equiposByEquipoLocal) {
		this.equiposByEquipoLocal = equiposByEquipoLocal;
	}

	public Integer getPuntosLocal() {
		return this.puntosLocal;
	}

	public void setPuntosLocal(Integer puntosLocal) {
		this.puntosLocal = puntosLocal;
	}

	public Integer getPuntosVisitante() {
		return this.puntosVisitante;
	}

	public void setPuntosVisitante(Integer puntosVisitante) {
		this.puntosVisitante = puntosVisitante;
	}

	public String getTemporada() {
		return this.temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

}
