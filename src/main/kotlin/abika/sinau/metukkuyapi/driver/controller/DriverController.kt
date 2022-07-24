package abika.sinau.metukkuyapi.driver.controller

import abika.sinau.metukkuyapi.driver.services.DriverServices
import abika.sinau.metukkuyapi.model.driver.Driver
import abika.sinau.metukkuyapi.model.driver.DriverLogin
import abika.sinau.metukkuyapi.model.driver.DriverLoginResponse
import abika.sinau.metukkuyapi.model.driver.DriverRequest
import abika.sinau.metukkuyapi.utils.BaseResponse
import abika.sinau.metukkuyapi.utils.toResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/driver")
class DriverController {

    @Autowired
    private lateinit var driverServices: DriverServices

    @GetMapping
    fun getUser(): BaseResponse<Driver> {
        val driverId = SecurityContextHolder.getContext().authentication.principal as? String
        return driverServices.getDriverById(driverId.orEmpty()).toResponses()
    }

    @PostMapping("/login")
    fun loginDriver(@RequestBody driverLogin: DriverLogin): BaseResponse<DriverLoginResponse> {
        return driverServices.loginDriver(driverLogin).toResponses()
    }

    @PostMapping("/register")
    fun registerDriver(@RequestBody driverRequest: DriverRequest): BaseResponse<Boolean> {
        return driverServices.registerDriver(driverRequest.mapToNewDriver()).toResponses()
    }
}