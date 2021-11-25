package pessoas;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable {
	private String endereco;
	private String telefone;
	private String cpf;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}
	public Cliente(String nome, String endereco, String telefone, String cpf) {
		super(nome);
		this.endereco = endereco;
		this.telefone = telefone;
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@Override
	public String toString() {
		return "Cliente [" + super.toString() + ", Endereco: " + endereco + ", Telefone: " + telefone + ", CPF: " + cpf + "]\n";
	} //arrumar para entregar o nome também.





}
