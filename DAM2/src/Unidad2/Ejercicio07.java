package Unidad2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio07 {

	public static void main(String[] args) throws IOException {
		// Parte uno --> Generamos el DataOutput para sacar datos al fichero f
		int contadorUno = 0;
		int contadorDos = 0;
		int contadorTres = 0;
		int contadorCuatro = 0;
		int contadorCinco = 0;
		File f = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\puntuacion.dat");
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));

			for (int i = 0; i < 20; i++) {
				int num = 0;
				num = (int) Math.round((Math.random() * 4) + 1);
				if (num == 1)
					contadorUno++;
				if (num == 2)
					contadorDos++;
				if (num == 3)
					contadorTres++;
				if (num == 4)
					contadorCuatro++;
				if (num == 5)
					contadorCinco++;
				dos.writeInt(num);
			}
			dos.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		// Segunda Parte --> Metemos los datos del fichero f

		DataInputStream dis = new DataInputStream(new FileInputStream(f));
		try {
			while (true) {
				System.out.print(dis.readInt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("\n-------------------------------");
		System.out.println("Hay " + contadorUno + " unos.");
		System.out.println("Hay " + contadorDos + " doses.");
		System.out.println("Hay " + contadorTres + " treses.");
		System.out.println("Hay " + contadorCuatro + " cuatros.");
		System.out.println("Hay " + contadorCinco + " cincos.");

	}

}
