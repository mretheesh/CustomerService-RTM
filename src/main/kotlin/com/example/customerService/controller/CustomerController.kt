package com.example.customerService.controller

import com.example.customerService.model.Customer
import com.example.customerService.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@Validated
class CustomerController(private val service: CustomerService) {
    @GetMapping("/customers/{customerId}")
    fun getCustomerByCustomerId(@PathVariable customerId: Long): ResponseEntity<Customer> {
        val optional: Optional<Customer> = service.getCustomerById(customerId);
        if (optional.isPresent) {
            return ResponseEntity(optional.get(), HttpStatus.OK);
        }
        return ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/customers/search/{customerName}")
    fun searchCustomer(@PathVariable customerName: String): ResponseEntity<Collection<Customer?>> {
        return ResponseEntity(service.searchCustomer(customerName), HttpStatus.OK);
    }

    @PostMapping("/customers/create")
    fun createCustomer(@RequestBody @Valid customer: Customer): ResponseEntity<Customer> {
        return ResponseEntity(service.createCustomer(customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{customerId}")
    fun deleteCustomer(@PathVariable customerId: Long): ResponseEntity<Customer> {
        return if (service.deleteCustomer(customerId)) {
            ResponseEntity(HttpStatus.OK);
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/customers/update")
    fun updateCustomer(@RequestBody @Valid customer: Customer): ResponseEntity<Customer> {
        val customer1:Customer? = service.updateCustomer(customer)
        return if (customer != null){
            return ResponseEntity(customer1, HttpStatus.OK);
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}