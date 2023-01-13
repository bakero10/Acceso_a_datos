package ejemplo1;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

//
//Alberto Carrera Mart�n - Abril 2020
//
//@ManyToOne Relacion de varios a uno (empleados --> departamento)
//@OneToMany Relation
//@OneToOne Relation
//@ManyToMany Relation
@Entity
public class EmpleadoEntity {
	@Id
	private int empnoId;
	private String nombre;
	private String oficio;
	private EmpleadoEntity dirId;
	private Date alta;
	private Integer salario;
	private Integer comision;
	@ManyToOne
		private DepartamentoEntity departamento;
	//
	public EmpleadoEntity(int empnoId, String nombre, String oficio, EmpleadoEntity dirId, Date alta, Integer salario,
			Integer comision, DepartamentoEntity departamento) {
		
		this.empnoId = empnoId;
		this.nombre = nombre;
		this.oficio = oficio;
		this.dirId = dirId;
		this.alta = alta;
		this.salario = salario;
		this.comision = comision;
		this.departamento = departamento;
	}
	//
	public int getEmpnoId() {
		return empnoId;
	}
	public void setEmpnoId(int empnoId) {
		this.empnoId = empnoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOficio() {
		return oficio;
	}
	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
	public EmpleadoEntity getDirId() {
		return dirId;
	}
	public void setDirId(EmpleadoEntity dirId) {
		this.dirId = dirId;
	}
	public Date getAlta() {
		return alta;
	}
	public void setAlta(Date alta) {
		this.alta = alta;
	}
	public Integer getSalario() {
		return salario;
	}
	public void setSalario(Integer salario) {
		this.salario = salario;
	}
	public Integer getComision() {
		return comision;
	}
	public void setComision(Integer comision) {
		this.comision = comision;
	}
	public DepartamentoEntity getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoEntity departamento) {
		this.departamento = departamento;
	}
	
}
//@Entity: La clase es una entidad que se va a mapear con una tabla de la base de datos. Los campos de la clase se mapearán con columnas de la base de datos. Por defecto el nombre de la tabla será el nombre de la clase Java. Se puede modificar usando la anotación @Table.
//2	@Id: indica que el campo anotado (en nuestro caso Long id) va a ser el identificador de la entidad. La columna con la que se mapea en la base de datos es la clave primaria de la tabla.
//3	@GeneratedValue: El identificador se genera automáticamente por parte de la base de datos cuando la entidad se hace persistente.
//4	@Column: Sirve para indicar características del esquema de la columna en la que se mapea el campo. El elemento name sirve para indicar el nombre de la columna en el mapeo. Si no estuviera, con un una columna con el nombre del atributo de la clase Java.
//5	En este caso, obligamos a que no la columna no sea null. Veremos que la columna correo de la tabla tendrá el modificador NOT NULL.
//6	Todos los atributos se mapean con campos de la tabla. En el caso de no utilizar la anotación @Column se mapea con un campo con el mismo nombre que el atributo.
//7	@OneToMany: Sirve para definir una relación uno-a-muchos entre Autor y Mensaje. La anotación cascade indica que las acciones de borrado, persist y merge se propagan en cascada a los mensajes. La anotación @mappedBy indica el atributo que define la clave ajena en el otro lado de la relación. Y la anotación EAGER indica que traeremos a memoria todos los mensajes con los que está relacionado el autor. Los veremos más adelante.
//8	@Version: Un atributo versión que usa JPA para implementar la gestión optimista de la concurrencia. Lo veremos en la última sesión.
//9	Constructor vacío, necesario para JPA.
//10 Métodos equals y hashCode basados en el identificador autogenerado.
