package ke.co.proaktiv.restfulapis.model;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 3998580853026924035L;

	public BadRequestException(String message) {
		super(message);
	}
}
