package com.web.megamart.services.impl;

import com.web.megamart.dto.CustomerRequest;
import com.web.megamart.dto.CustomerResponse;
import com.web.megamart.entity.Customer;
import com.web.megamart.exception.CustomerNotFoundException;
import com.web.megamart.repository.CustomerRepository;
import com.web.megamart.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public String registerCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    @Override
    public String updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot find customer with id %d", request.id())));

        mergeCustomer(customer, request);
        customerRepository.save(customer);
        return "Customer has been updated";
    }

    @Override
    public List<CustomerResponse> findAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map((customerMapper::formCustomer))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    @Override
    public CustomerResponse findById(String customerId) {
        var customerResponse = customerRepository.findById(customerId)
                .map(customerMapper::formCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + customerId + " not found"));
        return customerResponse;
    }

    @Override
    public void deleteCustomer(String customerId) {
        var customerIsPresent = customerRepository.findById(customerId).isPresent();
        if(customerIsPresent) {
            customerRepository.deleteById(customerId);
        }
        else {
            throw new CustomerNotFoundException("Customer with id " + customerId + " not found");
        }
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setFirstName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setFirstName(request.email());
        }
        if(request.address() != null) {
            customer.setAddress(request.address());
        }
    }
}
