package abika.sinau.metukkuyapi.customer.services

import abika.sinau.metukkuyapi.model.customer.Customer
import abika.sinau.metukkuyapi.model.customer.CustomerLogin
import abika.sinau.metukkuyapi.model.customer.CustomerLoginResponse

interface CustomerServices {

    fun loginCustomer(customerLogin: CustomerLogin) : Result<CustomerLoginResponse>

    fun registerCustomer(customer: Customer): Result<Boolean>

    fun getCustomerByUsername(username: String): Result<Customer>

    fun getCustomerById(id: String): Result<Customer>
}