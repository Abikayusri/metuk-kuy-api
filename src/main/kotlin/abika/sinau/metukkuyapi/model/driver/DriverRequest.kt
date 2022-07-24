package abika.sinau.metukkuyapi.model.driver

data class DriverRequest(
        var username: String,
        var password: String,
        var email: String,
        var address: String,
        var numberPlate: String,
        var vehicleYear: Int,
        var phoneNumber: String
) {
    fun mapToNewDriver(): Driver {
        return Driver.createNewCustomer(
                username, password, email, address, numberPlate, vehicleYear, phoneNumber
        )
    }
}