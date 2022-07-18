package abika.sinau.metukkuyapi.user.service

import abika.sinau.metukkuyapi.user.entity.LoginResponse
import abika.sinau.metukkuyapi.user.entity.User
import abika.sinau.metukkuyapi.user.entity.UserLogin

interface UserService {

    fun login(userLogin: UserLogin): Result<LoginResponse>

    fun register(user: User): Result<Boolean>

    fun getUsers(): Result<List<User>>

    fun getUserByUsername(username: String): Result<User>

    fun getUserById(id: String): Result<User>
}