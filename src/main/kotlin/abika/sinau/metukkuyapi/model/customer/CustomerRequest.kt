package abika.sinau.metukkuyapi.model.customer

data class CustomerRequest(
        var id: String = "",
        var username: String = "",
        var password: String = "",
        var email: String = "",
        var address: String = "",
        var phoneNumber: String = ""
) {
    fun mapToNewCustomer(): Customer {
        return Customer.createNewCustomer(
                username, password, email, address, phoneNumber
        )
    }
}
