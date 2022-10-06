package Unidad2;
	
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
public class Ejercicio07 {
	/*
	 * Realiza una clase UD2_7 que guarde 20 numeros enteros aleatorios comprendidos
	 * entre 1 y 5 en el fichero puntuacion.dat. Completa el codigo abriendo el
	 * fichero para visualizarlos todos por la consola indicando al final cuantas
	 * veces se repiten cada uno de ellos. Incluye tambien tratamiento de
	 * excepciones.
	 */
	public static void main(String[] args) throws IOException {
		// Parte uno --> Generamos el DataOutput para sacar datos al fichero f
		File f = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\puntuacion.dat");
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
		// Segunda Parte --> Metemos los datos del fichero f

		DataInputStream dis = new DataInputStream(new FileInputStream(f));
		ArrayList<Integer> lista = new ArrayList<>();
		try {
			while (true) {
				lista.add(dis.readInt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		int contador;
		int num;
		for (int i = 1; i <= 5; i++) {
			contador = 0;
			for(Integer nume : lista){
				if(nume == i) {
					contador++;
				}
			}
			System.out.println("El numero "+i+" aparece "+contador+" veces.");
		}
	}
}
