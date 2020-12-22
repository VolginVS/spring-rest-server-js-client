package ru.volginvs.rest.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.volginvs.rest.model.Customer;
import ru.volginvs.rest.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers/")
@CrossOrigin(origins = "http://localhost:8080")
public class CustomerRestController {

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping(value ="{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerService.getById(id);

        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("Content-Type",
//                "application/json");

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Validated Customer customer){
        HttpHeaders headers = new HttpHeaders();

        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.customerService.save(customer);
        return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Validated Customer customer, UriComponentsBuilder builder){
        HttpHeaders headers = new HttpHeaders();
        if(customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        customerService.save(customer);
        return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        Customer customer = this.customerService.getById(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.customerService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = this.customerService.getAll();

        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}

