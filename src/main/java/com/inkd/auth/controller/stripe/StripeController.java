package com.inkd.auth.controller.stripe;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.common.ResponseDTO;
import com.inkd.auth.model.dto.customer.CustomerDTO;
import com.inkd.auth.model.dto.stripe.*;
import com.inkd.auth.service.stripe.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/stripe")
public class StripeController {

    @Autowired
    private StripeService stripeService;

    @GetMapping(value = "/getCustomers", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CustomerCollectionDTO>> getCustomers() {
        ResponseDTO<CustomerCollectionDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CustomerCollectionDTO customerCollection = this.stripeService.getCustomers();

            response.setResult(customerCollection);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());

        } catch (StripeException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping(value = "/createCustomer", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CustomerDTO>> createCustomer(@RequestBody StripeCustomerCreateRQ createRQ) {
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CustomerDTO customerDTO = this.stripeService.createCustomer(createRQ);

            response.setResult(customerDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());

        } catch (StripeException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping(value = "/updateCustomer", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CustomerDTO>> updateCustomer(@RequestBody StripeCustomerUpdateRQ updateRQ) {
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CustomerDTO customerDTO = this.stripeService.updateCustomer(updateRQ);

            response.setResult(customerDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());

        } catch (StripeException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/retrieveCustomer/{stripeID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<CustomerDTO>> retrieveCustomer(@PathVariable String stripeID) {
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            CustomerDTO customerDTO = this.stripeService.retrieveCustomer(stripeID);

            response.setResult(customerDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());

        } catch (StripeException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping(value = "/createSubscription", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<SubscriptionDTO>> createSubscription(@RequestBody SubscriptionCreateRQ createRQ) {
        ResponseDTO<SubscriptionDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            SubscriptionDTO subscriptionDTO = this.stripeService.createSubscription(createRQ);

            response.setResult(subscriptionDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());

        } catch (StripeException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
