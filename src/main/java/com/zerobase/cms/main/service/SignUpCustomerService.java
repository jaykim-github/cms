package com.zerobase.cms.main.service;

import com.zerobase.cms.main.domain.SignUpForm;
import com.zerobase.cms.main.domain.model.Customer;
import com.zerobase.cms.main.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form){

        return customerRepository.save(Customer.from(form));
    }
}

