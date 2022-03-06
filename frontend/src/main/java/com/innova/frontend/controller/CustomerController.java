package com.innova.frontend.controller;

import com.innova.frontend.business.dto.CustomerDto;
import com.innova.frontend.business.services.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

/**
 * Base controller class for all customer operations.
 *
 * @author Ahmet AKAN
 */


@Controller
@Log4j2
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // FIND CUSTOMER INFORMATIONS BY ID AND ACCESS MY ACCOUNT PAGE
    @GetMapping("/customer")
    public String consumerList(Model model, @RequestParam Long identificationNumber) {
        try {
            CustomerDto customerDto = customerService.findCustomer(identificationNumber);
            model.addAttribute("customerDto", customerDto);
        } catch (HttpClientErrorException e) {
            return "error/no-customer-found";
        }

        return "my-account";
    }

    // SAVE NEW CUSTOMER
    @PostMapping("/customer")
    public String saveConsumer(@Valid CustomerDto customerDto,Model model) {
        ResponseEntity<CustomerDto> responseEntity;

        try {
            responseEntity = customerService.save(customerDto);
            log.info("Response entity is: " + responseEntity);
        } catch (HttpClientErrorException httpResponse) {
            log.error(httpResponse.getMessage());

            if(httpResponse.getStatusCode().series().CLIENT_ERROR == HttpStatus.Series.CLIENT_ERROR)
                        return "error/customer-already-exists";
            else if(httpResponse.getStatusCode().series().SERVER_ERROR == HttpStatus.Series.SERVER_ERROR)
                        return "error/something-went-wrong";

        }
        model.addAttribute("createDone", true);
        return "identification-number-inquiry";
    }

    // UPDATE CUSTOMER
    @PostMapping("/customer/update")
    public String updateCustomer(@Valid CustomerDto customerDto,
                                 @RequestParam long identificationNumber,
                                 Model model
    ) {

        customerDto.setIdentificationNumber(identificationNumber);
        log.info("Updating customerDto with the id of " + identificationNumber + ". New info : " + customerDto);
        try {
            customerService.update(customerDto);
        }
        catch(HttpClientErrorException httpResponse) {

            if(httpResponse.getStatusCode().series().CLIENT_ERROR == HttpStatus.Series.CLIENT_ERROR)
                return "error/no-customer-found";
            else if(httpResponse.getStatusCode().series().SERVER_ERROR == HttpStatus.Series.SERVER_ERROR)
                return "error/something-went-wrong";
        }
        model.addAttribute("updateDone", true);
        return "identification-number-inquiry";
    }

    // DELETE CUSTOMER
    @GetMapping("/customer/delete")
    public String deleteConsumer(@RequestParam long identificationNumber,Model model) {

        try {
            customerService.delete(identificationNumber);
        }
        catch(HttpClientErrorException httpResponse) {

            if(httpResponse.getStatusCode().series().CLIENT_ERROR == HttpStatus.Series.CLIENT_ERROR)
                return "error/no-customer-found";
            else if(httpResponse.getStatusCode().series().SERVER_ERROR == HttpStatus.Series.SERVER_ERROR)
                return "error/something-went-wrong";

        }
        model.addAttribute("deleteDone", true);
        return "identification-number-inquiry";
    }

    // TAKE PARAMS FOR UPDATE
    @GetMapping("/customer/update-form")
    public String updateForm(@RequestParam @ModelAttribute(name = "identificationNumber") long identificationNumber,
                             @RequestParam @ModelAttribute(name = "firstName") String firstName,
                             @RequestParam @ModelAttribute(name = "lastName") String lastName,
                             @RequestParam @ModelAttribute(name = "salary") Integer salary,
                             @RequestParam @ModelAttribute(name = "phoneNumber") String phoneNumber,
                             @ModelAttribute(name = "customerDto") CustomerDto customerDto) {
        return "customer-update";
    }

    @GetMapping("/new-customer")
    public String customerForm(@ModelAttribute(name = "customerDto") CustomerDto customerDto) {
        return "new-customer";
    }

    @GetMapping("/identification-number-inquiry")
    public String identificationNumberInquiry() {
        return "identification-number-inquiry";
    }


}
