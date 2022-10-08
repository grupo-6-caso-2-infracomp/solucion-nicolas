package Main;

import java.util.ArrayList;

public class HiloOp2 extends Thread
{
	private int id;
	private LinearProbingHashTable tablaPaginasRam;
	private ReferenciaActual referenciaActual;
	private boolean[] tablaPaginasProceso; 
	private ArrayList<Integer> secuenciaDeReferencias;
	private Integer numFallosDePag;

	
	/**
	 * Constructor para el thread de referencias
	 * @param pId
	 * @param pTablaPaginasRam
	 * @param pTablaPaginasProceso
	 * @param pSecuenciaDeReferencias
	 * @param pReferenciaActual
	 */
	public HiloOp2(int pId,LinearProbingHashTable pTablaPaginasRam, boolean[] pTablaPaginasProceso, ArrayList<Integer> pSecuenciaDeReferencias, ReferenciaActual pReferenciaActual)
	{
		id = pId;
		tablaPaginasRam = pTablaPaginasRam;
		tablaPaginasProceso = pTablaPaginasProceso;
		secuenciaDeReferencias = pSecuenciaDeReferencias;
		referenciaActual = pReferenciaActual;
		numFallosDePag = 0;
	}
	
	/**
	 * Constructor para el thread del algoritmo de envejecimiento
	 * @param pId
	 * @param pTablaPaginasRam
	 * @param pReferenciaActual
	 */
	
	public HiloOp2(int pId, LinearProbingHashTable pTablaPaginasRam, ReferenciaActual pReferenciaActual)
	{
		id = pId;
		tablaPaginasRam = pTablaPaginasRam;
		referenciaActual = pReferenciaActual;
	}

	public void run()
	{
		if(this.id==1)
			fallos();
		else
			envejecimiento();
	}

	public void fallos()
	{
		for(int i = 0; i<secuenciaDeReferencias.size(); i++)
		{
			while(referenciaActual.getReferenciaActual()!=null){
				this.currentThread().yield();
			}

			synchronized(tablaPaginasRam){

				Integer ref = secuenciaDeReferencias.get(i); 
				System.out.println("Posición: " + i);
				referenciaActual.setReferenciaActual(ref);

				if(!tablaPaginasRam.isFull()){ 

					System.out.println("No está lleno");
					if(!tablaPaginasRam.contains(ref)){

						tablaPaginasRam.insert(ref, 0);
						numFallosDePag++;
						System.out.println("Nuevo fallo");
						System.out.println("Páginas ram ocupadas: " + tablaPaginasRam.getSize());
						tablaPaginasProceso[ref] = true;


					}
				}
				else{

					if(!tablaPaginasRam.contains(ref)){
						System.out.println("Se llenó");
						Integer keyABorrar = tablaPaginasRam.encontrarPaginaASacarDeMemoria();
						System.out.println("Se eliminará la siguiente página de proceso de la ram: " + keyABorrar);
						tablaPaginasRam.remove(keyABorrar);
						tablaPaginasProceso[keyABorrar] = false;
						tablaPaginasRam.insert(ref, 0);
						tablaPaginasProceso[ref] = true;
						++numFallosDePag;
						System.out.println("nuevoFallo");
						System.out.println("Páginas ram ocupadas: " + tablaPaginasRam.getSize());

					}
					else{
						System.out.println("Se llenó, pero ya la contiene");
					}
				}
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		referenciaActual.setContinuar(false);
		System.out.println("El número de  fallos de página fue de: " + getNumFallosDePag());
	}

	public void envejecimiento()
	{
		while(referenciaActual.isContinuar()){

			synchronized(tablaPaginasRam){
				for(int i = 0; i< tablaPaginasRam.getMaxSize(); i++){

					Integer nuevoValor = tablaPaginasRam.darValor(i)/2;
					tablaPaginasRam.cambiarValor(i, nuevoValor);
				}

				Integer refActual = referenciaActual.getReferenciaActual();


				if(refActual!=null){

					Integer nuevoVal = 0;
					if(tablaPaginasRam.contains(refActual)){ 
						nuevoVal = tablaPaginasRam.get(refActual) + 536870912;
					}
					else{
						nuevoVal = 536870912;

					}
					tablaPaginasRam.insert(refActual, nuevoVal);

					referenciaActual.setReferenciaActual(null);
				}

			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public Integer getNumFallosDePag() 
	{
		return numFallosDePag;
	}
	
	
}
