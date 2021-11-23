package util;

public interface OperacoesConjunto<T> {
	 public boolean cadastrar(T objeto);
	 public String listarTodos();
	 public boolean pesquisar(T objeto);
	 public boolean remover(T objeto);

}
