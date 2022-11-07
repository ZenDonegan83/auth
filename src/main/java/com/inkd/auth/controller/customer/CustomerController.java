package com.inkd.auth.controller.customer;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.common.ResponseDTO;
import com.inkd.auth.model.dto.customer.CustomerDTO;
import com.inkd.auth.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("saveCustomer")
    public ResponseEntity<ResponseDTO<CustomerDTO>> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CustomerDTO savedCustomerDTO = customerService.saveCustomer(customerDTO);

            response.setResult(savedCustomerDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/allCustomers", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<CustomerDTO>>> allCustomers() {
        ResponseDTO<List<CustomerDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            List<CustomerDTO> allCustomers = this.customerService.findAllCustomers();

            response.setResult(allCustomers);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/updateCustomer/{customerID}")
    public ResponseEntity<ResponseDTO<CustomerDTO>> updateCustomer(@PathVariable Long customerID, @RequestBody CustomerDTO updateCustomerDTO) {
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CustomerDTO customerDTO = this.customerService.updateCustomer(customerID, updateCustomerDTO);

            response.setResult(customerDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/findByID/{customerID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CustomerDTO>> findCustomerByID(@PathVariable Long customerID) {
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CustomerDTO customer = this.customerService.findByID(customerID);

            response.setResult(customer);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
