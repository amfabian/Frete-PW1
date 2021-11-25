package util;

import java.io.Serializable;
import java.util.TreeSet;
import fretes.Frete;


public class Conjunto<T> implements Serializable,  OperacoesConjunto<T> {

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
		int cont = 0;
		if(conjunto!=null) {
			for (T e : conjunto) {
				if(e!=null) {
					cont++;
					aux += "Frete " + cont +  ":\n";
				}
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

	
	public Frete excluir(Situacao situacao) {
		
		if(conjunto!=null) {
			for (T e : conjunto) {
				Frete frete = (Frete) e;
				if(e!=null && situacao.equals(frete.getSituacao())) {
					return frete;					
				}
			}
			
		}
		
		
		return null;
	}
	
	
	public String procurarNome(String nome) {
		//aux para receber a String que será retornada. 
		String aux = ""; 
		//flag para devolver mensagem caso não encontre.
		boolean flag = false;
		//contador para numerar a quantidade de fretes apresentadas.
		int cont = 0;
		if(conjunto!=null) {
			for (T e : conjunto) {
				//NAO GOSTEI DISSO.
				//Eu queria poder pegar o nome do cliente através do TreeSet!!!!
				Frete frete = (Frete) e;
				if(e!=null && nome.equals(frete.getCliente().getNome())) {
					cont++;
					aux += "Frete " + cont +  ":\n";
					aux += e.toString() + "\n";
					flag = true;
				}
				System.out.println(e);
			}
		}
		if (!flag) return "Cliente não possui fretes";
		return aux;
	}

}
