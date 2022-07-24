package abika.sinau.metukkuyapi.customer.services

import abika.sinau.metukkuyapi.authentication.JWTConfig
import abika.sinau.metukkuyapi.customer.repository.CustomerRepository
import abika.sinau.metukkuyapi.exception.MetukException
import abika.sinau.metukkuyapi.model.customer.Customer
import abika.sinau.metukkuyapi.model.customer.CustomerLogin
import abika.sinau.metukkuyapi.model.customer.CustomerLoginResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerServicesImpl : CustomerServices {

    @Autowired
    private lateinit var customerRepository: CustomerRepository
    override fun loginCustomer(customerLogin: CustomerLogin): Result<CustomerLoginResponse> {
        val resultCustomer = customerRepository.getCustomerByUsername(customerLogin.username)

        return resultCustomer.map {
            val token = JWTConfig.generateToken(it)
            val passwordInDb = it.password
            val passwordRequest = customerLogin.password
            if (passwordInDb == passwordRequest) {
                CustomerLoginResponse(token)
            } else {
                throw MetukException("Password Invalid!")
            }
        }
    }

    override fun registerCustomer(customer: Customer): Result<Boolean> {
        return customerRepository.insertCustomer(customer)
    }

    override fun getCustomerByUsername(username: String): Result<Customer> {
        return customerRepository.getCustomerByUsername(username)
    }

    override fun getCustomerById(id: String): Result<Customer> {
        return customerRepository.getCustomerById(id)
    }

}