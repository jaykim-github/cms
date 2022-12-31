package com.zerobase.cms.main.domain.repository;

import com.zerobase.cms.main.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
