package abika.sinau.metukkuyapi.model.driver

import abika.sinau.metukkuyapi.utils.Constant.DRIVER_ROLE_ID
import java.util.*

data class Driver(
        var id: String = "",
        var username: String = "",
        var password: String = "",
        var email: String = "",
        var address: String = "",
        var numberPlate: String = "",
        var vehicleYear: Int = 0,
        var phoneNumber: String = "",
        var role: Int = DRIVER_ROLE_ID
) {
    companion object {
        fun createNewCustomer(
                username: String,
                password: String,
                email: String,
                address: String,
                numberPlate: String,
                vehicleYear: Int,
                phoneNumber: String
        ): Driver {
            return Driver(
                    id = UUID.randomUUID().toString(),
                    username = username,
                    password = password,
                    email = email,
                    address = address,
                    numberPlate = numberPlate,
                    vehicleYear = vehicleYear,
                    phoneNumber = phoneNumber,
                    role = DRIVER_ROLE_ID
            )
        }
    }
}
