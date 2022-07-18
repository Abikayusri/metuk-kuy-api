package abika.sinau.metukkuyapi.user.repository

import abika.sinau.metukkuyapi.database.DatabaseComponent
import abika.sinau.metukkuyapi.exception.MetukException
import abika.sinau.metukkuyapi.user.entity.User
import abika.sinau.metukkuyapi.utils.toResult
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {

    @Autowired
    private lateinit var databaseComponent: DatabaseComponent

    private fun userCollection(): MongoCollection<User> = databaseComponent.database.getDatabase("metuk").getCollection()

    override fun insertUser(user: User): Result<Boolean> {
        val existingUser = getUserByUsername(user.username)

        return if (existingUser.isSuccess) {
            throw MetukException ("User ${user.username} already exist")
        } else {
            userCollection().insertOne(user).wasAcknowledged().toResult()
        }
    }

    override fun getUserById(id: String): Result<User> {
        return userCollection().findOne(User::id eq id).toResult()
    }

    override fun getUserByUsername(username: String): Result<User> {
        return userCollection().findOne(User::username eq username).toResult("User $username not found")
    }

    override fun getUsers(): Result<List<User>> {
        return userCollection().find().toList().toResult()
    }
}