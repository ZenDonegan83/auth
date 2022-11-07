package com.inkd.auth.repository.customer;

import com.inkd.auth.model.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmailIgnoreCase(String email);

    Customer findByTelNumberIgnoreCase(String telNumber);
}
