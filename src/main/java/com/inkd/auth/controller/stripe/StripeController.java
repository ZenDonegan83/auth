package com.inkd.auth.controller.stripe;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.common.ResponseDTO;
import com.inkd.auth.model.dto.stripe.CustomerCollectionDTO;
import com.inkd.auth.service.stripe.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
