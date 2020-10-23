package br.com.segurossura.api.domains.produto;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Entity implementation class for Entity: Produto
 *
 */
@Entity
@Table(name = "produto") 
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String nome;
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	private Integer quantidadeEstoque;
	private Categoria categoria;
	
	public Produto() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	
	
	@Column(nullable = false, length = 128)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	
	
	@Column(name = "quantidade_estoque", nullable = false)
	public Integer getQuantidadeEstoque() {
		return this.quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER , optional = false)
	@JoinColumn(name = "categoria_id",  nullable = false, foreignKey = @ForeignKey(name = "fk_produto_to_categoria"))
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Produto))
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("[%s] %s", getNome());
	}

	/** verificar NegocioException
	public void baixarEstoque(Integer quantidade) {
		int novaQuantidade = this.getQuantidadeEstoque() - quantidade;
		if (novaQuantidade < 0) {
			String exceptionMessage = String.format("Não há disponibilidade no estoque de %d itens do produto %s - %s",
					quantidade,  this.getNome());
			throw new NegocioException(exceptionMessage);
		}
		this.setQuantidadeEstoque(novaQuantidade);
	}
	**/
	public void adicionarEstoque(Integer quantidade) {
		Integer novaQuantidadeEstoque = this.getQuantidadeEstoque() + quantidade;
		this.setQuantidadeEstoque(novaQuantidadeEstoque);
	}
	

}
