package com.inkd.auth.service.customer;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.customer.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    CustomerDTO saveCustomer(CustomerDTO customerDTO) throws AppsException;

    List<CustomerDTO> findAllCustomers() throws AppsException;

    CustomerDTO updateCustomer(Long customerID, CustomerDTO updateCustomerDTO) throws AppsException;

    CustomerDTO findByID(Long customerID) throws AppsException;
}
