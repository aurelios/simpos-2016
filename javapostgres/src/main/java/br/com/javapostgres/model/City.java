package br.com.javapostgres.model;

/**
 * Created by tqi_agimenes on 23/06/2017.
 */
public class City {

	public Long id;
	public String nome;

	public City() {
	}

	public City(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

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
}
