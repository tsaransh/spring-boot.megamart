package com.web.megamart.services;

import com.web.megamart.dto.CustomerRequest;
import com.web.megamart.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    public String registerCustomer(CustomerRequest customerRequest);

    public String updateCustomer(CustomerRequest request);

    List<CustomerResponse> findAllCustomer();

    Boolean existsById(String customerId);

    CustomerResponse findById(String customerId);

    void deleteCustomer(String customerId);
}
