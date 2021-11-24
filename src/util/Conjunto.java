package util;

import java.util.TreeSet;

public class Conjunto<T> implements OperacoesConjunto<T> {

	private TreeSet<T> conjunto = new TreeSet<T>();

	public Conjunto() {
		this.conjunto = new TreeSet();
	}


	@Override
	public boolean cadastrar(T objeto) {
		return conjunto.add(objeto);
	}

	@Override
	public String listarTodos() {
	    String aux = "";
        if(conjunto!=null) {
	         for (T e : conjunto) {
				if(e!=null)
					aux += e.toString() + "\n";
			}
        }
	     return aux;
	}

	@Override
	public boolean pesquisar(T objeto) {
		return conjunto.contains(objeto);
	}

	@Override
	public boolean remover(T objeto) {
		return conjunto.remove(objeto);
	}

}
