package ke.co.proaktiv.restfulapis.service;

import java.util.List;

import ke.co.proaktiv.restfulapis.domain.Customer;

public interface CustomerService {
	Customer create(Customer customer);
	List<Customer> findAll();
	Customer findById(String customerId);
}
