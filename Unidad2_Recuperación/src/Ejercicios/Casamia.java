package Ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Casamia {

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		
		File f =new File ("Ficheros/micasa.dat");
		RandomAccessFile file = new RandomAccessFile(f, "rw");
		
		int idPersona = 0;
		System.out.println("Introduce el id de la persona: ");
		idPersona = s.nextInt();
		
		int Posicion = (idPersona-1)*48;
		if(Posicion > f.length() || Posicion == 0) {
			System.out.println("La persona introducida no existe!");
			System.exit(-1);
		}
		file.seek(Posicion);
		if(file.readInt() == 0) {
			System.out.println("La persona ya ha sido borrada");
			System.exit(-1);
		}
		file.seek(Posicion);	//nos colocamos en posicion
		file.writeInt(0);		//escribimos el id a 0 osease lo borramos
		file.seek(0);			//nos colocamos en la posicion 0 para imprimir
		
		System.out.println("ID\tPERSONA\tEDAD");
		
		char [] nombre=new char[20];
		int edad;
		for (int i = 0; i < file.length(); i++) {
			int id = file.readInt();
			for (int j = 0; j < nombre.length; j++) {
				nombre[j]=file.readChar();
			}
			edad = file.readInt();
			String nombres =  new String(nombre);
			System.out.println(id+"\t"+nombres+"\t\t"+edad);
		}
		
		
		
	}

}
