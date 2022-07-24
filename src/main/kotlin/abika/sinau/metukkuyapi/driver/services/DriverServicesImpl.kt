package abika.sinau.metukkuyapi.driver.services

import abika.sinau.metukkuyapi.authentication.JWTConfig
import abika.sinau.metukkuyapi.driver.repository.DriverRepository
import abika.sinau.metukkuyapi.exception.MetukException
import abika.sinau.metukkuyapi.model.driver.Driver
import abika.sinau.metukkuyapi.model.driver.DriverLogin
import abika.sinau.metukkuyapi.model.driver.DriverLoginResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DriverServicesImpl : DriverServices {

    @Autowired
    private lateinit var driverRepository: DriverRepository
    override fun loginDriver(driverLogin: DriverLogin): Result<DriverLoginResponse> {
        val resultDriver = driverRepository.getDriverByNoPlate(driverLogin.noPlate)

        return resultDriver.map {
            val token = JWTConfig.generateToken(it)
            val passwordInDb = it.password
            val passwordRequest = driverLogin.password
            if (passwordInDb == passwordRequest) {
                DriverLoginResponse(token)
            } else {
                throw MetukException("Password Invalid")
            }
        }
    }

    override fun registerDriver(driver: Driver): Result<Boolean> {
        return driverRepository.insertDriver(driver)
    }

    override fun getDriverByUsername(username: String): Result<Driver> {
        return driverRepository.getDriverByUsername(username)
    }

    override fun getDriverById(id: String): Result<Driver> {
        return driverRepository.getDriverById(id)
    }

    override fun getDriverByNoPlate(noPLate: String): Result<Driver> {
        return driverRepository.getDriverByNoPlate(noPLate)
    }
}