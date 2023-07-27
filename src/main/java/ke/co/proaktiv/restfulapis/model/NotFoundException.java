package ke.co.proaktiv.restfulapis.model;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7136292985746110981L;

	public NotFoundException(String message) {
		super(message);
	}	
}
