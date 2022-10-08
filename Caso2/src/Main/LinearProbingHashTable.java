package Main;

public class LinearProbingHashTable 
{
	private int tamanoActual, tamanoMaximo;
	private Integer[] keys;
	private Integer[] vals;

	public LinearProbingHashTable(int capacity)
	{
		tamanoActual = 0;
		tamanoMaximo = capacity;
		keys = new Integer[tamanoMaximo];
		vals = new Integer[tamanoMaximo];

		for(int i= 0; i<tamanoMaximo; i++){
			vals[i] = 0;
		}
	}

	public int getSize() { 
		return tamanoActual;
	}

	public int getMaxSize(){
		return tamanoMaximo;
	}

	public boolean isFull()
	{
		return tamanoActual == tamanoMaximo;
	}

	public boolean isEmpty() { 
		return getSize() == 0; 
	}

	public boolean contains(Integer key)
	{
		return get(key) != null;
	}

	private int hash(Integer key)
	{
		return key.hashCode() % tamanoMaximo;
	}

	public void insert(Integer key, Integer val)
	{
		int tmp = hash(key);
		int i = tmp;

		// bucle Do-while
		do {
			if (keys[i] == null) {
				keys[i] = key;
				vals[i] = val;
				tamanoActual++;
				return;
			}

			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}

			i = (i + 1) % tamanoMaximo;

		}
		
		while (i != tmp);
	}

	public Integer get(Integer key)
	{
		int i = hash(key);
		int conteo = 0;
		while (keys[i] != null && conteo < tamanoMaximo) {
			if (keys[i].equals(key)){
				return vals[i]; 
			}
			i = (i + 1) % tamanoMaximo;
			conteo++;
		}

		return null;
	}

	public void remove(Integer key)
	{
		if (!contains(key))
			return;
		
		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i + 1) % tamanoMaximo;
		keys[i] = vals[i] = null;

		for (i = (i + 1) % tamanoMaximo; keys[i] != null;
				i = (i + 1) % tamanoMaximo) {
			Integer tmp1 = keys[i], tmp2 = vals[i];
			keys[i] = vals[i] = null;
			tamanoActual--;
			insert(tmp1, tmp2);
		}
		tamanoActual--;
	}

	public Integer darValor(Integer pos){

		return vals[pos];
	}

	public void cambiarValor(Integer pos, Integer val){

		vals[pos] = val;
	}

	public Integer encontrarPaginaASacarDeMemoria(){

		Integer posMinima = 0;
		Integer valorMinimo = vals[posMinima];
		if(valorMinimo==0){return keys[posMinima];}

		for(int i= 1; i <tamanoMaximo; i++){

			if(vals[i] < valorMinimo){
				posMinima = i;
				valorMinimo = vals[i];
				if(valorMinimo==0){return keys[i];}
			}
		}

		return keys[posMinima];
	}
}
