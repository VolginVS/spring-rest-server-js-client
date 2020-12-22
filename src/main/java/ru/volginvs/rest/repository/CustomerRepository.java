package ru.volginvs.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volginvs.rest.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
