package abika.sinau.metukkuyapi.driver.repository

import abika.sinau.metukkuyapi.database.DatabaseComponent
import abika.sinau.metukkuyapi.exception.MetukException
import abika.sinau.metukkuyapi.model.driver.Driver
import abika.sinau.metukkuyapi.utils.toResult
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class DriverRepositoryImpl: DriverRepository {

    @Autowired
    private lateinit var databaseComponent: DatabaseComponent

    private fun driverCollection(): MongoCollection<Driver> = databaseComponent.database.getDatabase("metuk").getCollection()

    override fun insertDriver(driver: Driver): Result<Boolean> {
        val existingDriver = getDriverByNoPlate(driver.numberPlate)

        return if (existingDriver.isSuccess) {
            throw MetukException("Driver with number plate ${driver.numberPlate} has already exist")
        } else {
            driverCollection().insertOne(driver).wasAcknowledged().toResult()
        }
    }

    override fun getDriverById(id: String): Result<Driver> {
        return driverCollection().findOne(Driver::id eq id).toResult()
    }

    override fun getDriverByUsername(username: String): Result<Driver> {
        return driverCollection().findOne(Driver::username eq username).toResult()
    }

    override fun getDriverByNoPlate(noPlate: String): Result<Driver> {
        return driverCollection().findOne(Driver::numberPlate eq noPlate).toResult("User with $noPlate not found")
    }

}