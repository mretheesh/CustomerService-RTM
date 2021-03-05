package com.example.customerService.service

import com.example.customerService.client.Profile
import com.example.customerService.client.ProfileClient
import com.example.customerService.dao.CustomerRepository
import com.example.customerService.model.Customer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class CustomerServiceTest {
    private lateinit var customer: Customer

    @MockBean
    private lateinit var service: CustomerService

    @MockBean
    private lateinit var repo: CustomerRepository

    @MockBean
    private lateinit var client: ProfileClient

    @BeforeEach
    fun setup() {
        customer = Customer(123, "testName", "prof", 32)
        BDDMockito.given(repo.save(customer)).willReturn(customer)
        BDDMockito.given(client.getCustomerProfile()).willReturn(Profile("name"))
        service = CustomerService(repo, client)
    }

    @Test
    fun testCreateCustomer() {
        assertNotNull(service.createCustomer(customer))
    }
    @Test
    fun testCreateCustomerException() {
        customer.customerAge = 12
        val exception = assertThrows(CustomerService.CustomerNotEligibleException::class.java) {
            service.createCustomer(customer)
        }
        assertEquals("Customer with name testName is not eligible to create",exception.message)
    }
}