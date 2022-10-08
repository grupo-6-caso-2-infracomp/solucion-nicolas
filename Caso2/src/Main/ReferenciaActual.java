package Main;

public class ReferenciaActual 
{
	private Integer referenciaActual;

	// si es la última referencia de la secuencia de referencias ya no se necesita continuar más y se vuelve falso
	private boolean continuar;

	public ReferenciaActual(Integer pReferenciaActual)
	{
		referenciaActual = pReferenciaActual;
		continuar = true;
	}

	public Integer getReferenciaActual() {
		return referenciaActual;
	}

	public void setReferenciaActual(Integer referenciaActual) {
		this.referenciaActual = referenciaActual;
	}

	public boolean isContinuar() {
		return continuar;
	}

	public void setContinuar(boolean continuar) {
		this.continuar = continuar;
	}

}
