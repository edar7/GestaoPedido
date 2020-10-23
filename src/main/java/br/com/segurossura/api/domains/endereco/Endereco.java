package br.com.segurossura.api.domains.endereco;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.segurossura.api.domains.cliente.Cliente;
import br.com.segurossura.api.util.Excecao;



@Entity
@Table(name="endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Cidade cidade;
	private String logradouro;
	private String complemento;
	private String cep;
	private Integer numero;
	private Cliente cliente;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	// @OneToOne(cascade=CascadeType.PERSIST, optional = false)
	@ManyToOne(cascade = CascadeType.PERSIST, optional = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_endereco_to_cidade"))
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * @NotBlank
	@Size(min = 4, max = 150)
	**/
	@Column(nullable = false, length = 150)
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	
	@Column(nullable = false, length = 150)
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public Endereco(String rua, String cidade, String bairro, String cep, String estado) {

        Excecao.Validar(rua == null || rua.equals(""), "Rua é obrigatório");
        Excecao.Validar(cidade == null || cidade.equals(""), "Cidade é obrigatório");
        //Excecao.Validar(bairro == null || bairro.equals(""), "Bairro é obrigatório");
        Excecao.Validar(cep == null || cep.equals(""), "Cep é obrigatório");
        Excecao.Validar(estado == null || estado.equals(""), "Estado é obrigatório");

        this.logradouro = rua;
        this.cidade.setNome(cidade);
        this.cep = cep;
        this.cidade.getEstado().setNome(estado);;
    }
	
	
	/**@Cep
	 * *
	 * @return
	 */
	@Column(nullable = false, length = 9)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	@NotNull
	@Min(0)
	**/
	@Column(nullable = false, length = 20)
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@ManyToOne(optional = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_endereco_to_cliente"))
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Transient
	public boolean isNovo() {
		return this.getCodigo() == null;
	}

	@Transient
	public boolean isExistente() {
		return !this.isNovo();
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
		if (!(obj instanceof Endereco))
			return false;
		Endereco other = (Endereco) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
