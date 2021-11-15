package menus;

public enum OpcoesMenu {
	CADASTRAR_FRETE(1, "1 - Cadastrar Frete"),
	PESQUISAR_FRETE(2, "2 - Pesquisar Frete usando a origem e o destino"),
	PESQUISAR_CLIENTE_FRETE(3, "3 - Pesquisar se um cliente possui algum frete cadastrado"),
	PESQUISAR_FRETE_SIT(4, "4 - Pesquisar Frete usando a situacao"),
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


