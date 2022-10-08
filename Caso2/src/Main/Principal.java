package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Principal 
{
	/**
	 * Configuraci�n para la opci�n 1
	 * @param br
	 * @throws IOException
	 */
	public static void config1(BufferedReader br) throws IOException 
	{
		System.out.println("Ingrese el tama�o de una p�gina: \n");
		Integer tamPag = Integer.parseInt(br.readLine());
		System.out.println("Ingrese el tama�o de un n�mero entero: \n");
		Integer tamInt = Integer.parseInt(br.readLine());
		System.out.println("Ingrese el n�mero de filas de las matrices: \n");
		Integer numFilas = Integer.parseInt(br.readLine());
		System.out.println("Ingrese el n�mero de columnas: \n");
		Integer numCols = Integer.parseInt(br.readLine());
		System.out.println("Ingr�se el nombre del archivo a generar");
		String nombreArchivo = br.readLine();
		Opcion1 op1 = new Opcion1(numFilas, numCols, tamPag, tamInt);
		op1.escribirArchivo(nombreArchivo);
		config2(br, op1);
	}


	/**
	 * Configuraci�n para la opci�n 2
	 * @param br
	 * @param pOp1
	 * @throws IOException
	 */
	public static void config2(BufferedReader br, Opcion1 pOp1) throws IOException 
	{
		Opcion1 op1 = pOp1;
		System.out.println("Ingrese el nombre y extensi�n(si la especific� en la opci�n 1) del archivo: \n");
		String nombreArch = br.readLine();
		System.out.println("Ingrese el n�mero de marcos de p�gina");
		Integer numMarcosPagina = Integer.parseInt(br.readLine());
		File archivo = new File("O:\\Workspace\\Casos\\Caso2\\docs\\"+op1.getNombreArchivo());
		FileReader reader = new FileReader(archivo);
		BufferedReader br2 = new BufferedReader(reader);
		
		LinearProbingHashTable tablaMarcosPaginaRam= new LinearProbingHashTable(numMarcosPagina);
		
		boolean[] tablaPaginasProceso = new boolean[op1.getNumPags()];
		
		Arrays.fill(tablaPaginasProceso, false);
		
		ArrayList<Integer> secuenciaReferenciasProceso = op1.referencias2(op1.getNumCols(), op1.getNumFilas());
		
		ReferenciaActual referenciaActual = new ReferenciaActual(null);
		//Hilo que se encarga de la tabla de referencias
		HiloOp2 t1 = new HiloOp2(1, tablaMarcosPaginaRam, tablaPaginasProceso, secuenciaReferenciasProceso, referenciaActual);
		//Hilo que ejecuta el algoritmo de envejecimiento
		HiloOp2 t2 = new HiloOp2(2, tablaMarcosPaginaRam, referenciaActual);
		
		t1.start();
		t2.start();
		br2.close();
		reader.close();
	}

	public static void main(String args[])
	{
		try 
		{
			//Lectura de la consola
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(in);
				config1(br);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Revisa la ruta del archivo o el nombre");
		}
	}
}
