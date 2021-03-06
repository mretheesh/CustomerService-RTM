package com.example.customerService.service

import com.example.customerService.client.ProfileClient
import com.example.customerService.dao.CustomerRepository
import com.example.customerService.model.Customer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@Service
class CustomerService(private val repository: CustomerRepository, private val client: ProfileClient) {
    fun getCustomerById(customerId: Long): Optional<Customer> {
        return repository.findById(customerId);
    }
    fun createCustomer(customer: Customer): Customer {
        if ((customer.customerAge?:0) < 18) {
            throw CustomerNotEligibleException("Customer with name ${customer.customerName} is not eligible to create");
        }
        customer.customerProfile = client.getCustomerProfile().name;
        return repository.save(customer);
    }
    fun deleteCustomer(customerId: Long) : Boolean{
        return if(repository.existsById(customerId)){
            repository.deleteById(customerId);
            true
        } else {
            false;
        }
    }
    fun updateCustomer(customer: Customer): Customer?{
        var optional: Optional<Customer> = repository.findById(customer.customerId?:0);
        if (optional.isPresent) {
            var existingCustomer : Customer = optional.get()
            existingCustomer.customerName = customer.customerName
            existingCustomer.customerAge = customer.customerAge
            existingCustomer.customerProfile = customer.customerProfile
            return repository.save(existingCustomer);
        }
        return null;
    }

    fun searchCustomer(customerName: String): Collection<Customer?>? {
        return repository.searchCustomerByCustomerName(customerName)
    }

    @ControllerAdvice
    class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {
        @ExceptionHandler(value = [(CustomerNotEligibleException::class)])
        fun handleCustomerNotEligible(ex: CustomerNotEligibleException, request: WebRequest): ResponseEntity<ErrorsDetails> {
            val errorDetails = ErrorsDetails(
                    "Validation Failed",
                    ex.message!!
            )
            return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
        }
    }
    class CustomerNotEligibleException(override val message: String?) : Exception(message)
    data class ErrorsDetails(val message: String, val details: String)
}