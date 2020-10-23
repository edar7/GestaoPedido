package br.com.segurossura.api.domains.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.segurossura.api.domains.endereco.Endereco;
import br.com.segurossura.api.util.Excecao;


@Entity
@Table(name = "cliente", uniqueConstraints = {
		
		@UniqueConstraint(name = "uk_email", columnNames = { "email" }) })
public class Cliente implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private List<Endereco> endereco = new ArrayList<>();
	private String nome;
	private String email;
	private String senha;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	
	@Column(nullable = false, length = 255)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Column(nullable = false, length = 255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(nullable = false, length = 150)
	public String getSenha() {
		return email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	 public Cliente(String nome, String email, String senha, Endereco endereco) {

	        Excecao.Validar(nome == null || nome.equals(""), "Nome é obrigatório");
	        Excecao.Validar(email == null || email.equals(""), "Email é obrigatório");
	        Excecao.Validar(senha == null || senha.equals(""), "Senha é obrigatório");
	        Excecao.Validar(endereco == null, "Endereco é obrigatório");

	        Excecao.Validar(senha.length() < 6, "Senha deve ter no mínio 6 caracteres");

	        List<Endereco> ListEnd = new ArrayList<>(); 
	        ListEnd.add(endereco);
	        this.nome = nome;
	        this.email = email;
	        this.senha = senha;
	        this.endereco = ListEnd;
	    }

}
