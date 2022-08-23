package co.grandcircus.AnimalAPI.exceptions;

public class AnimalNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AnimalNotFoundException(String id) {
		super("Pet Not Found");
	}

}
