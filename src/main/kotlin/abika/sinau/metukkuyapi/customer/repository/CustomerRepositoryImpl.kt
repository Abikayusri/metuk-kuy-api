package abika.sinau.metukkuyapi.customer.repository

import abika.sinau.metukkuyapi.database.DatabaseComponent
import abika.sinau.metukkuyapi.exception.MetukException
import abika.sinau.metukkuyapi.model.customer.Customer
import abika.sinau.metukkuyapi.utils.toResult
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CustomerRepositoryImpl: CustomerRepository {

    @Autowired
    private lateinit var databaseComponent: DatabaseComponent

    private fun customerCollection(): MongoCollection<Customer> = databaseComponent.database.getDatabase("metuk").getCollection()
    override fun insertCustomer(customer: Customer): Result<Boolean> {
        val existingUser = getCustomerByUsername(customer.username)

        return if (existingUser.isSuccess) {
            throw MetukException("User ${customer.username} already exist")
        } else {
            customerCollection().insertOne(customer).wasAcknowledged().toResult()
        }
    }

    override fun getCustomerById(id: String): Result<Customer> {
        return customerCollection().findOne(Customer::id eq id).toResult()
    }

    override fun getCustomerByUsername(username: String): Result<Customer> {
        return customerCollection().findOne(Customer::username eq username).toResult("User $username not found")
    }

}