package Sesion_1_Manejo_de_conectores_DB;

public class main {

	public static void main(String[] args) {
		AccesoBdatos abd = new AccesoBdatos();
		
		abd.conectar("baloncesto");
		
		abd.crearArchivoJson();
		
		abd.desconecta();
		

	}

}
