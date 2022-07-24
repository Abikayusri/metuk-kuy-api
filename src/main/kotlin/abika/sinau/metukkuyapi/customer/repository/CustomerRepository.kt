package abika.sinau.metukkuyapi.customer.repository

import abika.sinau.metukkuyapi.model.customer.Customer

interface CustomerRepository {
    fun insertCustomer(customer: Customer): Result<Boolean>

    fun getCustomerById(id: String): Result<Customer>

    fun getCustomerByUsername(username: String): Result<Customer>
}