package br.com.segurossura.api.domains.pedido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

import br.com.segurossura.api.domains.cliente.Cliente;
import br.com.segurossura.api.domains.produto.Produto;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Column(nullable = true, length = 20)
	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = sessao;
	}

	private Long codigo;
	private Date dataCriacao;
	

	private StatusPedido statusPedido;
	
	private String sessao;
	
	private List<ItemPedido> itens = new ArrayList<>();

	/* cliente */
	private Cliente cliente;
	
	public Pedido() {
		super();
		statusPedido = StatusPedido.ORCAMENTO;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status_pedido", nullable = false, length = 20)
	public StatusPedido getStatusPedido() {
		return this.statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_codigo", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_to_cliente"))
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Transient
	public ItemPedido getNovoItem() {
		if (this.isOrcamento()) {
			return this.getItens().get(0);
		}
		return null;
	}

	@Transient
	public boolean isNovo() {
		return getCodigo() == null;
	}

	@Transient
	public boolean isExistente() {
		return !isNovo();
	}
	
	@Transient
	public boolean isOrcamento() {
		return this.getStatusPedido() == StatusPedido.ORCAMENTO;
	}
	
	@Transient
	public boolean isEmitido() {
		return this.getStatusPedido() == StatusPedido.EMITIDO;
	}
	
	@Transient
	public boolean isEmissivel() {
		return this.isExistente() && this.isOrcamento();
	}

	@Transient
	public boolean isNaoEmissivel() {
		return !this.isEmissivel();
	}

	@Transient
	public boolean isCancelavel() {
		return this.isExistente() && !this.isCancelado();
	}

	@Transient
	public boolean isNaoCancelavel() {
		return !this.isCancelavel();
	}

	@Transient
	private boolean isCancelado() {
		return this.getStatusPedido() == StatusPedido.CANCELADO;
	}

	@Transient
	public boolean isAlteravel() {
		return this.isOrcamento();
	}

	@Transient
	public boolean isNaoAlteravel() {
		return !this.isAlteravel();
	}
	
	public void novoItem() {
		if (this.isOrcamento()) {
			Produto produto = new Produto();

			ItemPedido item = new ItemPedido();
			item.setProduto(produto);
			item.setPedido(this);
			item.setQuantidade(1);

			this.getItens().add(0, item);
			// this.recalculaValorTotal();
		}
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
		if (!(obj instanceof Pedido))
			return false;
		Pedido other = (Pedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
