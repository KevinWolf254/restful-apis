package ke.co.proaktiv.restfulapis.domain;

import ke.co.proaktiv.restfulapis.model.CustomerStatus;
import lombok.Data;

@Data
public class Customer {
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;	
	private CustomerStatus status;
}
