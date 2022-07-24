package abika.sinau.metukkuyapi.driver.services

import abika.sinau.metukkuyapi.model.driver.Driver
import abika.sinau.metukkuyapi.model.driver.DriverLogin
import abika.sinau.metukkuyapi.model.driver.DriverLoginResponse

interface DriverServices {

    fun loginDriver(driverLogin: DriverLogin): Result<DriverLoginResponse>

    fun registerDriver(driver: Driver): Result<Boolean>

    fun getDriverByUsername(username: String): Result<Driver>

    fun getDriverById(id: String): Result<Driver>

    fun getDriverByNoPlate(noPLate: String): Result<Driver>
}