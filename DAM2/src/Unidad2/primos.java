package Unidad2;

import java.util.Scanner;

public class primos {
public static void main(String[] args) {
	Scanner s = new Scanner(System.in);

	int numero;
	System.out.print("¿Cuantos numeros primos quieres?");
	numero = s.nextInt();
	int contador = 0;
	int resultado =0;
	int [] numeros =new int [numero];
		for(int i=2; i<numeros.length+1;i++) {
			for(int j=2;j<i;j++) {
				resultado = i/j;
				System.out.println(i + "/ " + j +"--> " + resultado);
			}
		}
	}
}
	