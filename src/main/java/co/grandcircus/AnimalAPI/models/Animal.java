package co.grandcircus.AnimalAPI.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("animals")
public class Animal {

	@Id
	private String id;
	private String name;
	private String species;
	private Integer weight;
	private String[] traits;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String[] getTraits() {
		return traits;
	}

	public void setTraits(String[] traits) {
		this.traits = traits;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Animal() {

	}

	public Animal(String name, String species, Integer weight, String[] traits) {
		super();
		
		this.name = name;
		this.species = species;
		this.weight = weight;
		this.traits = traits;
	}

}
