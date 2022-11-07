package com.inkd.auth.service.customer;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.domain.customer.Customer;
import com.inkd.auth.model.dto.customer.CustomerDTO;
import com.inkd.auth.repository.customer.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws AppsException {
        Customer customer = new Customer();

        //Validate Customer DTO
        this.validateCustomerDTO(customerDTO, true);

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setTelNumber(customerDTO.getTelNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setProfilePic(customerDTO.getProfilePic());
        customer.setCreateDate(new Date());

        customer = customerRepository.saveAndFlush(customer);

        return new CustomerDTO(customer);
    }

    private void validateCustomerDTO(CustomerDTO customerDTO, boolean isNew) throws AppsException {
        if (StringUtils.isEmpty(customerDTO.getTelNumber())) {
            throw new AppsException("Customer Tel number is not valid");
        }

        if (StringUtils.isEmpty(customerDTO.getEmail())) {
            throw new AppsException("Customer Email is not valid");
        }

        if (isNew) {
            if (this.customerRepository.findByTelNumberIgnoreCase(customerDTO.getTelNumber()) != null) {
                throw new AppsException("Tel number already exist");
            }

            if (this.customerRepository.findByEmailIgnoreCase(customerDTO.getEmail()) != null) {
                throw new AppsException("Email already exist");
            }
        }

        if (StringUtils.isEmpty(customerDTO.getFirstName())) {
            throw new AppsException("First name can't be empty or null");
        }

        if (StringUtils.isEmpty(customerDTO.getLastName())) {
            throw new AppsException("Last name can't be empty or null");
        }
    }

    @Override
    public List<CustomerDTO> findAllCustomers() throws AppsException {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customerList) {
            customerDTOList.add(new CustomerDTO(customer));
        }

        return customerDTOList;
    }

    @Override
    public CustomerDTO updateCustomer(Long customerID, CustomerDTO updateCustomerDTO) throws AppsException {
        if (customerID == null) {
            throw new AppsException("Customer ID is not valid");
        }

        if (!customerRepository.existsById(customerID)) {
            throw new AppsException("Customer is not found");
        } else {
            Customer customer = customerRepository.getById(customerID);

            //Validate Customer DTO
            this.validateCustomerDTO(updateCustomerDTO, false);

            if (!customer.getTelNumber().equalsIgnoreCase(updateCustomerDTO.getTelNumber())
                    && this.customerRepository.findByTelNumberIgnoreCase(updateCustomerDTO.getTelNumber()) != null) {
                throw new AppsException("Tel number is already exist");
            }
            if (!customer.getEmail().equalsIgnoreCase(updateCustomerDTO.getEmail())
                    && this.customerRepository.findByEmailIgnoreCase(updateCustomerDTO.getEmail()) != null) {
                throw new AppsException("Email is already exist");
            }

            customer.setFirstName(updateCustomerDTO.getFirstName());
            customer.setLastName(updateCustomerDTO.getLastName());
            customer.setTelNumber(updateCustomerDTO.getTelNumber());
            customer.setEmail(updateCustomerDTO.getEmail());
            customer.setProfilePic(updateCustomerDTO.getProfilePic());

            customer = this.customerRepository.saveAndFlush(customer);

            return new CustomerDTO(customer);
        }
    }

    @Override
    public CustomerDTO findByID(Long customerID) throws AppsException {
        if (customerID == null) {
            throw new AppsException("Customer ID is not valid");
        }

        if (!customerRepository.existsById(customerID)) {
            throw new AppsException("Customer is not found");
        } else {
            Customer customer = customerRepository.getById(customerID);

            return new CustomerDTO(customer);
        }
    }
}
