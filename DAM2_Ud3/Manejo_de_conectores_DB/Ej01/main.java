package Ej01;

public class main {

	public static void main(String[] args) {
		AccesoBdatos abd = new AccesoBdatos();
		
		abd.conectar("baloncesto");
		
		abd.crearArchivoJson();
		
		abd.desconecta();
		

	}

}
