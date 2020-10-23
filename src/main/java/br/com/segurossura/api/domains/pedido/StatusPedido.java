package br.com.segurossura.api.domains.pedido;

public enum StatusPedido {
	
	ORCAMENTO("Orçamento"),
	EMITIDO("Emitido"),
	CANCELADO("Cancelado");
	
	private String descricao;

	private StatusPedido(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Acesso de leitura para o campo descricao
	 * @return a descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
