package com.example.customerService.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name="profile", url="https://my-json-server.typicode.com")
interface ProfileClient {
    @GetMapping("/typicode/demo/profile", consumes= [MediaType.APPLICATION_JSON_VALUE])
    fun getCustomerProfile(): Profile
}
data class Profile(val name: String)