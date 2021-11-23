package menus;

public enum OpcoesMenu {
	CADASTRAR_FRETE(1, "1 - Cadastrar frete"),
	LISTAR_FRETE(2, "2 - Listar todos os fretes"),
	LISTAR_FRETE_CLIENTE(3, "3 - Listar os fretes de um cliente"),
	REMOVER_FRETE(4, "4 - Remover Frete usando a situacao"),
	SAIR(5, "5 - Sair");

	private final int identificador;
	private final String descricao;

	OpcoesMenu(final int identificador,
			final String descricao) {
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


