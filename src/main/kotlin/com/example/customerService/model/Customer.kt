package com.example.customerService.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
data class Customer constructor(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var customerId: Long? = null,
                                @field:NotEmpty(message = "Name is mandatory") var customerName: String?,
                                var customerProfile: String? = null, var customerAge: Int? = null)
