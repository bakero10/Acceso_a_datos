package Ejercicio03;

public class MainPruebas {
	public static void main(String[] args) {
		
		AccesoBdatos abd = new AccesoBdatos();
		abd.conectar();
	
		abd.modificarAltura(1, 200);
		abd.desconectar();
	}
}
