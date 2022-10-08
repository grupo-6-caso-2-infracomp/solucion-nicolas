package Main;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Opcion1 
{
	private int numFilas;
	public int getNumFilas() {
		return numFilas;
	}

	public void setNumFilas(int numFilas) {
		this.numFilas = numFilas;
	}

	public int getNumCols() {
		return numCols;
	}

	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	public int getTamPag() {
		return tamPag;
	}

	public void setTamPag(int tamPag) {
		this.tamPag = tamPag;
	}

	public int getTamInt() {
		return tamInt;
	}

	public void setTamInt(int tamInt) {
		this.tamInt = tamInt;
	}

	public int getNumPags() {
		return numPags;
	}

	public void setNumPags(int numPags) {
		this.numPags = numPags;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	private int numCols;
	private int tamPag;
	private int tamInt;
	private int numPags;
	private String nombreArchivo;
	
	public Opcion1(int pNumFilas, int pNumCols, int pTamPag, int pTamInt)
	{
		numFilas = pNumFilas;
		numCols = pNumCols;
		tamPag = pTamPag;
		tamInt = pTamInt;
	}
	
	public String recorrido1() 
	{
			String datos = "";
			int recor = 0;
			int numPagActual = 0;
			int numFallos = 0;
			for (int i = 0; i < numFilas ; i++) 
			{
				for (int j = 0; j < numCols; j++) 
				{
					datos += "A: ["+i+"-"+j+"],"+recor+","+numPagActual+"\n";
					recor += numCols;
					datos += "B: ["+i+"-"+j+"],"+recor+","+numPagActual+"\n";
					recor += numCols;
					datos += "C: ["+i+"-"+j+"],"+recor+","+numPagActual+"\n";
					numPags+= tamInt;
					numPagActual += tamInt;
					numFallos+=3;
				}
				recor = 0;
			}
			datos += "Total de fallos:"+ numFallos;
			return datos;
	}
	
	public void escribirArchivo(String pNombreArchivo)
	{
		try 
		{
			File archivo = new File("O:\\Workspace\\Casos\\Caso2\\docs\\"+pNombreArchivo);
			FileWriter wr = new FileWriter(archivo);
			wr.write("TP ="+ tamPag+" \n");
			wr.write("TE = "+ tamInt+" \n");
			wr.write("NF = "+ numFilas+" \n");
			wr.write("NC = "+ numCols+" \n");
			wr.write("TR = 1 \n");
			wr.write("NP = "+numPags+" \n");
			wr.write("NR = "+(3*(numCols*numFilas))+" \n");
			wr.write(recorrido1());
			wr.close();
			System.out.println("Archivo"+ pNombreArchivo+ "creado \n");
			nombreArchivo = pNombreArchivo;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Algo salió mal al tratar de escribir el archivo");
		}
	}
	
	public String getNombreArchivo()
	{
		return nombreArchivo;
	}
	
	public ArrayList<Integer> referencias2(int numCols, int numFilas) 
	{
		ArrayList<Integer> referencias = new ArrayList<>();
		for (int i = 0; i < numCols*numFilas; i++) 
		{
			referencias.add((int)Math.random());// equiv mat1[i][j]
			referencias.add((int)Math.random());// equiv mat2[i][j]
			referencias.add((int)Math.random());// equiv mat3[i][j]
		}
		return referencias;
	}
}
