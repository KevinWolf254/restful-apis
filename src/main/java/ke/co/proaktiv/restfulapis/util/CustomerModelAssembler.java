package ke.co.proaktiv.restfulapis.util;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ke.co.proaktiv.restfulapis.controller.CustomerController;
import ke.co.proaktiv.restfulapis.domain.Customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

	  @Override
	  public EntityModel<Customer> toModel(Customer customer) {
	    return EntityModel.of(customer,
	        linkTo(methodOn(CustomerController.class).findById(customer.getId())).withSelfRel(),
	        linkTo(methodOn(CustomerController.class).findAll()).withRel("customers"));
	  }
}
