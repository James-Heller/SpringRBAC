package pers.jamestang.springrbac.system.security

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import pers.jamestang.springrbac.system.entity.LoginAdmin
import pers.jamestang.springrbac.system.repository.Admins

@Component
class DBAuthHandler(
    private val database: Database
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {

        val admin = database.from(Admins)
            .select()
            .where { Admins.username eq username }
            .map { Admins.createEntity(it) }
            .firstOrNull() ?: throw Exception("Admin not found")

        return LoginAdmin(admin)
    }
}