package co.grandcircus.AnimalAPI.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.AnimalAPI.exceptions.AnimalNotFoundException;
import co.grandcircus.AnimalAPI.models.Animal;
import co.grandcircus.AnimalAPI.repositories.AnimalRepository;



@RestController
public class AnimalController {

	@Autowired
	private AnimalRepository animals;

	@ResponseBody
	@ExceptionHandler(AnimalNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)

	String animalNotFoundHandler(AnimalNotFoundException ex) {
		return ex.getMessage();
	}

	@GetMapping("/reset")
	public String reset() {

		// delete all
		animals.deleteAll();

		// add default animals
		String[] traits = new String [] {"Loves pizza", "Hungry", "Bowling Ball Shaped"};
		Animal animal = new Animal("Belle", "cat", 16, traits);
		animals.insert(animal);
		
		traits = new String [] {"Shy boy", "Loves treats", "Will run when vibes are off"};
		animal = new Animal("Boots", "cat", 10, traits);
		animals.insert(animal);
		
		traits = new String [] {"Loves to play ball", "Independent", "Chocolate Milk Cow"};
		animal = new Animal("Magnolia", "dog", 100, traits);
		animals.insert(animal);

		return "Data reset.";

	}
//	Create a GET endpoint that will allow users to retrieve all animals
//	a. endpoint: /animals
//	b. response code: 200 (OK)
//	c. response body: a JSON array of all pets

		@GetMapping("/animals")
		public List<Animal> getAnimals() {
			return animals.findAll();
		}
//		
//		Create a GET endpoint that will allow users to retrieve an animal by ID
//		a. endpoint: /animals/{id}
//	   	b. if found:
//			i. response code: 200 (OK)
//			ii. response body: a JSON object of pet
//	   	c. if not found:
//			i. response code: 404 (Not Found)
//			ii. response body: "Pet Not Found"
		
		@GetMapping("/animals/{id}")
		public Animal getAnimalById(@PathVariable("id") String id) {
			return animals.findById(id).orElseThrow(() -> new AnimalNotFoundException(id));
		}
//		
//		Create a POST endpoint that will allow users to create a new animal
//		a. endpoint: /animals/{id}
//		b. response code: 201 (Created)
//		c. response body: JSON object of created pet (with ID)

		@PostMapping("/animals/{id}")
		@ResponseStatus(HttpStatus.CREATED)
		public Animal create(@RequestBody Animal animal) {
			return animals.insert(animal);
		}
//		
//		Create a PUT endpoint that will allow users to update an existing animal's description by ID
//		a. /animals/{id}
//		b. response code: 200 (OK)
//		c. newly update JSON object with ID
		
		
//		If you just want to update part of your resource, you still need to send in data for the entire resource 
//		when you make a PUT request. I will remember this eventually.
		
		@PutMapping("/animals/{id}")
		public Animal updateAnimal(@PathVariable("id") String id, @RequestBody Animal animal) {

			return animals.save(animal);
		}
		
//		 Create a DELETE endpoint that will allow users to delete an existing animal's description by ID
//			a. /animals/{id}
//			b. response code: 204 (No Content)
//			c. response body: empty
		
		@DeleteMapping("/animals/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void delete(@PathVariable("id") String id) {
			animals.deleteById(id);
		}


}
