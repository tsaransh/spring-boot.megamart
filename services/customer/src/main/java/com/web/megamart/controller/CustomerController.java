package com.web.megamart.controller;

import com.web.megamart.dto.CustomerRequest;
import com.web.megamart.dto.CustomerResponse;
import com.web.megamart.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid CustomerRequest request) {
        return new ResponseEntity<>(customerService.registerCustomer(request), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody @Valid CustomerRequest request) {
          return ResponseEntity.ok(customerService.updateCustomer(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> getAllCustomer() {
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("exists/{customerId}")
    public ResponseEntity<Boolean> existsById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.existsById(customerId));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
