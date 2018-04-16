package br.com.javaelasticsearch.model;

/**
 * Created by tqi_agimenes on 21/06/2017.
 */
public class Person {

	public Long id;
	public String nome;
	public Long city;
	//public City city;

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

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	/*public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}*/
}
