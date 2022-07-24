package abika.sinau.metukkuyapi.model.customer

import abika.sinau.metukkuyapi.utils.Constant.CUSTOMER_ROLE_ID
import java.util.*

data class Customer(
        var id: String = "",
        var username: String = "",
        var password: String = "",
        var email: String = "",
        var address: String = "",
        var phoneNumber: String = "",
        var role: Int = CUSTOMER_ROLE_ID
) {
    companion object {
        fun createNewCustomer(
                username: String,
                password: String,
                email: String,
                address: String,
                phoneNumber: String,
        ): Customer {
            return Customer(
                    id = UUID.randomUUID().toString(),
                    username = username,
                    password = password,
                    email = email,
                    address = address,
                    phoneNumber = phoneNumber,
                    role = CUSTOMER_ROLE_ID
            )
        }
    }
}
