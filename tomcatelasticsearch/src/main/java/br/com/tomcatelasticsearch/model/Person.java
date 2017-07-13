package br.com.tomcatelasticsearch.model;

/**
 * Created by tqi_agimenes on 21/06/2017.
 */
public class Person {

	public Long id;
	public String nome;
	public City city;

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
