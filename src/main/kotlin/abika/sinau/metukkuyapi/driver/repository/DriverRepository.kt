package abika.sinau.metukkuyapi.driver.repository

import abika.sinau.metukkuyapi.model.driver.Driver

interface DriverRepository {

    fun insertDriver(driver: Driver): Result<Boolean>

    fun getDriverById(id: String): Result<Driver>

    fun getDriverByUsername(username: String): Result<Driver>

    fun getDriverByNoPlate(noPlate: String):Result<Driver>
}