package fretes;

public class ItemFrete implements util.Validador {
	private String descricao;
	private Double peso;

	public ItemFrete() {}
	public ItemFrete(String descricao, Double peso) {
		super();
		this.descricao = descricao;
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "ItemFrete [descricao=" + descricao + ", peso=" + peso + "]";
	}

}
