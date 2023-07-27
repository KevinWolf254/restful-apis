package ke.co.proaktiv.restfulapis.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import ke.co.proaktiv.restfulapis.domain.Customer;
import ke.co.proaktiv.restfulapis.model.NotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {
	private final HashMap<String, Customer> repository = new HashMap<>();
	
	@Override
	public Customer create(Customer customer) {
		repository.putIfAbsent(customer.getId(), customer);
		return repository.get(customer.getId());
	}

	@Override
	public List<Customer> findAll() {
		return repository.values().stream().toList();
	}

	@Override
	public Customer findById(String customerId) {
		if(!repository.containsKey(customerId)) {
			throw new NotFoundException("Customer with id " + customerId+ " was not found!");
		}
		
		return repository.get(customerId);
	}
}
