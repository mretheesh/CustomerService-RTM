package com.example.customerService.controller

import com.example.customerService.model.Customer
import com.example.customerService.service.CustomerService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(CustomerController::class)
internal class CustomerControllerTest()  {
    @Autowired
    lateinit private var mockMvc: MockMvc
    private lateinit var customer: Customer
    @MockBean
    private lateinit var service: CustomerService
    @BeforeEach
    fun setup() {
        customer = Customer(123,"testName", "prof", 32)
        given(this.service.getCustomerById(customer.customerId!!)).willReturn(Optional.of(customer))
        given(this.service.createCustomer(customer)).willReturn(customer)
        given(this.service.deleteCustomer(customer.customerId!!)).willReturn(true)
        given(this.service.updateCustomer(customer)).willReturn(customer)
    }
    @Test
    fun getCustomerByCustomerId() {
        mockMvc.perform(get("/customers/123"))
                .andExpect(status().isOk)
    }



    @Test
    fun deleteCustomer() {
        mockMvc.perform(delete("/customers/123"))
                .andExpect(status().isOk)
    }

}