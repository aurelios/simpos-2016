package br.com.javamysql.model;

/**
 * Created by tqi_agimenes on 21/06/2017.
 */
public class Person {

	public Long id;
	public String nome;
	public Integer cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCidade() {
		return cidade;
	}

	public void setCidade(Integer cidade) {
		this.cidade = cidade;
	}
}
