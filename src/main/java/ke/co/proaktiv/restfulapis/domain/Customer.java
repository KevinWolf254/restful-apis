package ke.co.proaktiv.restfulapis.domain;

import lombok.Data;

@Data
public class Customer {
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
}
