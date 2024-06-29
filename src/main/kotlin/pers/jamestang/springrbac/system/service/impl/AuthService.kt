package pers.jamestang.springrbac.system.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import pers.jamestang.springrbac.system.entity.LoginAdmin
import pers.jamestang.springrbac.system.entity.Menu
import pers.jamestang.springrbac.system.repository.Admins
import pers.jamestang.springrbac.system.repository.Menus
import pers.jamestang.springrbac.system.repository.RoleMenus
import pers.jamestang.springrbac.system.repository.UserRoles
import pers.jamestang.springrbac.system.service.IAuthService
import pers.jamestang.springrbac.system.util.JWTUtil

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val database: Database,
    private val passwordEncoder: PasswordEncoder
) : IAuthService {


    override fun loginByPassword(username: String, password: String): String {

        val authenticateToken = UsernamePasswordAuthenticationToken(username, password)
        val authenticated = authenticationManager.authenticate(authenticateToken)

        SecurityContextHolder.getContext().authentication = authenticated

        val admin = database.sequenceOf(Admins).find { it.username eq username } ?: throw RuntimeException("用户不存在")

        return JWTUtil.generateToken(
            username, mapOf(
                "email" to admin.email
            ),
            true
        )

    }

    override fun registry(username: String, password: String, email: String): Boolean {

        val encryptedPassword = passwordEncoder.encode(password)

        val result = database.insert(Admins) {
            set(it.username, username)
            set(it.password, encryptedPassword)
            set(it.email, email)
        }

        return result > 0
    }

    override fun getCurrentUserMenus(): List<Menu> {

        val id = (SecurityContextHolder.getContext().authentication.principal as LoginAdmin).getUserId()
        return database.from(Menus)
            .leftJoin(RoleMenus, on = RoleMenus.menuId eq Menus.id)
            .leftJoin(UserRoles, on = UserRoles.roleId eq RoleMenus.roleId)
            .select(Menus.columns)
            .where(UserRoles.userId eq id).map(Menus::createEntity)
    }

}