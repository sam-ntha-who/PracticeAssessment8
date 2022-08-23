package co.grandcircus.AnimalAPI.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.grandcircus.AnimalAPI.models.Animal;

public interface AnimalRepository extends MongoRepository<Animal, String> {

	List<Animal> findAll();
	Optional<Animal> findById(String id);
	
	
}
