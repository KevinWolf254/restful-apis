package ke.co.proaktiv.restfulapis.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import ke.co.proaktiv.restfulapis.domain.Customer;
import ke.co.proaktiv.restfulapis.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
	private final CustomerService service;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public EntityModel<Customer> create(@RequestBody Customer customer) {
		 var savedCustomer = service.create(customer);
		  return EntityModel.of(savedCustomer,
		      linkTo(methodOn(CustomerController.class).findById(savedCustomer.getId())).withSelfRel(),
		      linkTo(methodOn(CustomerController.class).findAll()).withRel("customers"));
	}

	@GetMapping("/{customerId}")
	@ResponseStatus(value = HttpStatus.OK)	
	public EntityModel<Customer> findById(@PathVariable String customerId) {
		Customer customer = service.findById(customerId);
		  return EntityModel.of(customer,
		      linkTo(methodOn(CustomerController.class).findById(customer.getId())).withSelfRel(),
		      linkTo(methodOn(CustomerController.class).findAll()).withRel("customers"));
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)	
	public CollectionModel<EntityModel<Customer>> findAll() {
		  List<EntityModel<Customer>> customers = service.findAll().stream()
		      .map(customer -> EntityModel.of(customer,
		          linkTo(methodOn(CustomerController.class).findById(customer.getId())).withSelfRel(),
		          linkTo(methodOn(CustomerController.class).findAll()).withRel("customers")))
		      .collect(Collectors.toList());

		  return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).findAll()).withSelfRel());
	}
}
