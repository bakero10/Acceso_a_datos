package Unidad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejericicio09Carlos {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try {
			File f = new File("C:\\Users\\Bakero\\Desktop\\Pruebas\\Bakero\\ProfesFPSierraGuara.dat");
			RandomAccessFile file = new RandomAccessFile(f, "rw");
			int id = s.nextInt();
			int pos = (id - 1) * 56;//formula para calcular la posición
			if ((pos > f.length() - 56)||(id<1)){//calculo la posición del último registro si "pos" es superior estoy fuera
				System.err.println("No existe un profesor con ese ID");
				System.exit(-1);
			}
			file.seek(pos);
			if (file.readInt() == 0) {//leo el ID (avanza una posicion)
				System.err.println("El profesor con ese ID ya ha sido borrado\n");
			}
			else{
				file.seek(file.getFilePointer()-1);//retrocedo una posicion para situarme en el ID de nuevo
				file.writeInt(0); //borro el ID
			}
			int dept;
			double anti;
			char profes[]=new char[20];
			file.seek(0);//me coloco al pricipio del todo
			System.out.println("ID\tNombre\t\tDeparatmento\tAntiguedad");
			for (int j = 0; j < file.length()/56; j++) {
				id=file.readInt();
				for (int i = 0; i <profes.length ; i++) {//recojo los 20 caracteres en un array
					profes[i]=file.readChar();
				}
				dept=file.readInt();
				anti=file.readDouble();
				String Profe=new String(profes);//paso el array a String para imprimirlo
				System.out.println(id+"\t"+Profe+"\t"+dept+"\t"+anti);
			}
		} catch (FileNotFoundException e) {
			System.err.println("no se encuentra el archivo");
		} catch (IOException e) {
			System.err.println("ERROR IOException");
		}
	}
}
