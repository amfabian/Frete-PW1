package fretes;

import java.io.Serializable;
import java.util.ArrayList;
import pessoas.Cliente;
import util.Situacao;

public class Frete implements Serializable, Comparable<Frete>{
	private Double valor;
	private String cidadeOrigem;
	private String cidadeDestino;
	private ArrayList<ItemFrete> itens;
	private Situacao situacao;
	private Cliente cliente;


	public Frete() {}
	public Frete(Double valor, String cidadeOrigem, String cidadeDestino, ArrayList<ItemFrete> itens, Situacao situacao, Cliente cliente) {
		super();
		this.valor = valor;
		this.cidadeOrigem = cidadeOrigem;
		this.cidadeDestino = cidadeDestino;
		this.itens = itens;
		this.situacao = situacao;
		this.cliente = cliente;
	}

	//Getters and Setters
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getCidadeOrigem() {
		return cidadeOrigem;
	}
	public void setCidadeOrigem(String cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}
	public String getCidadeDestino() {
		return cidadeDestino;
	}
	public void setCidadeDestino(String cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}

	public ArrayList<ItemFrete> getItens() {
		return itens;
	}
	public void setItens(ArrayList<ItemFrete> itens) {
		this.itens = itens;
	}


	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		String aux = "";
		if(itens != null) {
			for (ItemFrete item : itens) {
				if(item != null)
					aux += item.toString()+"\n"; 
			}
		}

		return "[" +  "Preço: R$" + valor + ", Cidade de Origem: " + cidadeOrigem + ", Cidade de Destino: " + cidadeDestino +  "]\n" + cliente.toString() + aux + 
				"Situação: " + situacao.getDescricao() ;
	}
	@Override
	public int compareTo(Frete o) {
		// TODO Auto-generated method stub
		if(getValor() == o.getValor())
			return 0;
		else if(getValor() < o.getValor())
			return -1;
		return 1;
	}




}
