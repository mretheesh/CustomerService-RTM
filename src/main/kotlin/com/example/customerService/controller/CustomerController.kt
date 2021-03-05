package com.example.customerService.controller

import com.example.customerService.client.ProfileClient
import com.example.customerService.model.Customer
import com.example.customerService.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import javax.validation.Valid

@RestController
@Validated
class CustomerController(private val service:CustomerService) {
    @GetMapping("/customers/{customerId}")
    fun getCustomerByCustomerId(@PathVariable customerId: Long): Customer {
        return service.getCustomerById(customerId);
    }
    @PostMapping("/customers/create")
    fun createCustomer(@RequestBody @Valid customer: Customer): Customer {
        return service.createCustomer(customer)
    }
    @DeleteMapping("/customers/{customerId}")
    fun deleteCustomer(@PathVariable customerId: Long): Customer {
        return service.getCustomerById(customerId);
    }
    @PutMapping("/customers/update")
    fun updateCustomer(@PathVariable customerId: Long): Customer {
        return service.getCustomerById(customerId);
    }
}