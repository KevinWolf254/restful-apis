package ke.co.proaktiv.restfulapis.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import ke.co.proaktiv.restfulapis.domain.Customer;
import ke.co.proaktiv.restfulapis.model.BadRequestException;
import ke.co.proaktiv.restfulapis.model.CustomerStatus;
import ke.co.proaktiv.restfulapis.model.NotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {
	private final HashMap<String, Customer> repository = new HashMap<>();
	
	@Override
	public Customer create(Customer customer) {
		customer.setStatus(CustomerStatus.NEW);
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
	
	@Override
	public Customer update(String customerId, Customer customer) {
		if(!repository.containsKey(customerId)) {
			throw new NotFoundException("Customer with id " + customerId + " was not found!");
		}
		var previousData = repository.get(customerId);
		customer.setId(customerId);
		
		// ensure status does not change
		customer.setStatus(previousData.getStatus());
		
		repository.put(customerId, customer);
		return repository.get(customerId);
	}

	@Override
	public void delete(String customerId) {
		if(!repository.containsKey(customerId)) {
			throw new NotFoundException("Customer with id " + customerId + " was not found!");
		}
		repository.remove(customerId);		
	}
	@Override
	public Customer kyc(String customerId) {
		if(!repository.containsKey(customerId)) {
			throw new NotFoundException("Customer with id " + customerId + " was not found!");
		}
		
		var customer = repository.get(customerId);
		
		if(!customer.getStatus().equals(CustomerStatus.NEW)) {
			throw new BadRequestException("Can not change customer status to kyc from " + customer.getStatus().toString());
		}
		
		customer.setStatus(CustomerStatus.KYC);
		return repository.put(customerId, customer);
	}

	@Override
	public Customer complete(String customerId) {
		if(!repository.containsKey(customerId)) {
			throw new NotFoundException("Customer with id " + customerId + " was not found!");
		}
		
		var customer = repository.get(customerId);
		
		if(!customer.getStatus().equals(CustomerStatus.KYC)) {
			throw new BadRequestException("Can not change customer status to complete from " + customer.getStatus().toString());
		}
		
		customer.setStatus(CustomerStatus.COMPLETED);
		return repository.put(customerId, customer);
	}

	@Override
	public Customer cancel(String customerId) {
		if(!repository.containsKey(customerId)) {
			throw new NotFoundException("Customer with id " + customerId + " was not found!");
		}
		
		var customer = repository.get(customerId);
		
		if(!customer.getStatus().equals(CustomerStatus.KYC)) {
			throw new BadRequestException("Can not change customer status to cancel from " + customer.getStatus().toString());
		}
		
		customer.setStatus(CustomerStatus.CANCELLED);
		return repository.put(customerId, customer);
	}
}
