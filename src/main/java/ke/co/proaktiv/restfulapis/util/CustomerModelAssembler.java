package ke.co.proaktiv.restfulapis.util;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ke.co.proaktiv.restfulapis.controller.CustomerController;
import ke.co.proaktiv.restfulapis.domain.Customer;
import ke.co.proaktiv.restfulapis.model.CustomerStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

	  @Override
	  public EntityModel<Customer> toModel(Customer customer) {
	    EntityModel<Customer> customerModel = EntityModel.of(customer,
	        linkTo(methodOn(CustomerController.class).findById(customer.getId())).withSelfRel(),
	        linkTo(methodOn(CustomerController.class).findAll()).withRel("customers"));
	    
	    if(customer.getStatus().equals(CustomerStatus.NEW)) {
	    	customerModel.add(linkTo(methodOn(CustomerController.class).kyc(customer.getId())).withRel("kyc"));
	    } else if(customer.getStatus().equals(CustomerStatus.KYC)) {
	    	customerModel.add(linkTo(methodOn(CustomerController.class).complete(customer.getId())).withRel("complete"));
	    	customerModel.add(linkTo(methodOn(CustomerController.class).cancel(customer.getId())).withRel("cancel"));
	    }
	    
	    return customerModel;
	  }
}
