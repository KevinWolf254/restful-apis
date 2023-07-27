package ke.co.proaktiv.restfulapis.service;

import java.util.List;

import ke.co.proaktiv.restfulapis.domain.Customer;

public interface CustomerService {
	Customer create(Customer customer);
	List<Customer> findAll();
	Customer findById(String customerId);
	Customer update(String customerId, Customer customer);
	void delete(String customerId);
	Customer kyc(String customerId);
	Customer complete(String customerId);
	Customer cancel(String customerId);
}
