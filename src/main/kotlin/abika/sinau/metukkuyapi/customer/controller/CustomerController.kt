package abika.sinau.metukkuyapi.customer.controller

import abika.sinau.metukkuyapi.customer.services.CustomerServices
import abika.sinau.metukkuyapi.model.customer.Customer
import abika.sinau.metukkuyapi.model.customer.CustomerLogin
import abika.sinau.metukkuyapi.model.customer.CustomerLoginResponse
import abika.sinau.metukkuyapi.model.customer.CustomerRequest
import abika.sinau.metukkuyapi.utils.BaseResponse
import abika.sinau.metukkuyapi.utils.toResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/customer")
class CustomerController {
    
    @Autowired
    private lateinit var customerServices: CustomerServices
    
    @GetMapping
    fun getUser(): BaseResponse<Customer> {
        val userId = SecurityContextHolder.getContext().authentication.principal as? String
        return customerServices.getCustomerById(userId.orEmpty()).toResponses()
    }

    @PostMapping("/login")
    fun loginCustomer(@RequestBody customerLogin: CustomerLogin): BaseResponse<CustomerLoginResponse> {
        return customerServices.loginCustomer(customerLogin).toResponses()
    }

    @PostMapping("/register")
    fun registerCustomer(@RequestBody customerRequest: CustomerRequest): BaseResponse<Boolean> {
        return customerServices.registerCustomer(customerRequest.mapToNewCustomer()).toResponses()
    }
}