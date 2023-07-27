package ke.co.proaktiv.restfulapis.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import ke.co.proaktiv.restfulapis.domain.Customer;
import ke.co.proaktiv.restfulapis.service.CustomerService;
import ke.co.proaktiv.restfulapis.util.CustomerModelAssembler;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
	private final CustomerService service;
	private final CustomerModelAssembler assembler;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Customer customer) {
		var customerModel = assembler.toModel(service.create(customer));
		  return ResponseEntity
				  .created(customerModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				  .body(customerModel);
	}

	@GetMapping("/{customerId}")
	@ResponseStatus(value = HttpStatus.OK)	
	public EntityModel<Customer> findById(@PathVariable String customerId) {
		  return assembler.toModel(service.findById(customerId));
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)	
	public CollectionModel<EntityModel<Customer>> findAll() {
		  List<EntityModel<Customer>> customers = service.findAll().stream()
		      .map(assembler::toModel)
		      .collect(Collectors.toList());

		  return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).findAll()).withSelfRel());
	}

	@PutMapping("/{customerId}")
	public EntityModel<Customer> update(@RequestBody Customer customer,@PathVariable String customerId) {
		return assembler.toModel(service.update(customerId, customer));
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> delete(@PathVariable String customerId) {
		service.delete(customerId);
	  return ResponseEntity.noContent().build();
	}
}
