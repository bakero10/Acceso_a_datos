import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio04 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\DAM2Alu1\\Desktop\\Pruebas\\pares.txt"));
		
		for(int i=0; i<500;i++) {
			if(i%2 == 0) {
				bw.write(Integer.toString(i)+"\n");;
				System.out.println(i);
			}
		}
		bw.close();
	}	
}
