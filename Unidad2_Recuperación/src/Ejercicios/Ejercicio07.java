package Ejercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ejercicio07 {
	// NOSE PORQUE COÑO NO ME FUNCIONA ESTA MIERDA
	
	public static void main(String[] args) throws IOException {
		
		File f = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\puntuacion.dat");
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
			for (int i = 0; i < 20; i++) {
				int num = 0;
				num = (int) Math.round((Math.random() * 4) + 1);
				dos.writeInt(num);
			}
			dos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		//SEGUNDA PARTE 
		DataInputStream datosEntrada = new DataInputStream(new FileInputStream(f));
		ArrayList<Integer> listaEnteros = new ArrayList<>();
		int contadorUnos = 0;
		int contadorDoses = 0;
		int contadorTreses = 0;
		int contadorCuatros = 0;
		int contadorCincos = 0;
		try {
			while(true) {
				listaEnteros.add(datosEntrada.readInt());
			}			
		} catch (Exception e) {
		}
		for (Integer integer : listaEnteros) {
			System.out.print(integer);
			if(integer == 1) {
				contadorUnos++;
			}
			if(integer == 2) {
				contadorDoses++;
			}
			if(integer == 3) {
				contadorTreses++;
			}
			if(integer == 4) {
				contadorCuatros++;
			}
			if(integer == 5) {
				contadorCincos++;
			}
		}
		System.out.println();
		System.out.println("El numero 1 aparece "+contadorUnos+" veces.");
		System.out.println("El numero 2 aparece "+contadorDoses+" veces.");
		System.out.println("El numero 3 aparece "+contadorTreses+" veces.");
		System.out.println("El numero 4 aparece "+contadorCuatros+" veces.");
		System.out.println("El numero 5 aparece "+contadorCincos+" veces.");
		
	}

}
