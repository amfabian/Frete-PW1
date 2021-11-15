package util;

public enum Situacao {
	EM_ANDAMENTO(1, "1 - Em Andamento"),
	CANCELADO(2, "2 - Cancelado"),
	ENCERRADO(3, "3 - Encerrado");

	private final int identificador;
	private final String descricao;

	Situacao(final int identificador, String descricao) {
		this.identificador = identificador;
		this.descricao = descricao;
	}

	public int getIdentificador() {
		return identificador;
	}

	public String getDescricao() {
		return descricao;
	}



}
