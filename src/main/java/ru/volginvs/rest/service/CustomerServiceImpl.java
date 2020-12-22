package ru.volginvs.rest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volginvs.rest.model.Customer;
import ru.volginvs.rest.repository.CustomerRepository;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getById(Long id) {
        //log.info("IN CustomerServiceImpl getById {}", id);
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Customer customer) {
        //log.info("In CustomerServiceImpl save {}", customer);
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        //log.info("In CustomerServiceImpl delete {}", id);
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() {
        //log.info("In CustomerServiceImpl findAll customers");
        return customerRepository.findAll();
    }
}
