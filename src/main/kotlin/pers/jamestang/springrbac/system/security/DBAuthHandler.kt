package pers.jamestang.springrbac.system.security

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import pers.jamestang.springrbac.system.entity.LoginUser
import pers.jamestang.springrbac.system.repository.Users

@Component
class DBAuthHandler(
    private val database: Database
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {

        val user = database.from(Users)
            .select()
            .where { Users.username eq username }
            .map { Users.createEntity(it) }
            .firstOrNull() ?: throw Exception("User not found")

        return LoginUser(user)
    }
}