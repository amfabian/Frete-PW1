package util;

public interface OperacoesConjunto<T> {
	 public boolean cadastrar(T objeto);
	 public String listarTodos();
	 public String pesquisar(String nome);
	 public boolean remover(T objeto);

}
