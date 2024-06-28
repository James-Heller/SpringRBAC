package pers.jamestang.springrbac.system.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.count
import org.ktorm.entity.first
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.springframework.stereotype.Service
import pers.jamestang.springrbac.system.entity.Admin
import pers.jamestang.springrbac.system.entity.Role
import pers.jamestang.springrbac.system.repository.Admins
import pers.jamestang.springrbac.system.repository.Roles
import pers.jamestang.springrbac.system.repository.UserRoles
import pers.jamestang.springrbac.system.service.IAdminService
import pers.jamestang.springrbac.system.util.Page

@Service
class AdminService(
    private val database: Database
) : IAdminService {
    override fun insertUser(username: String, password: String, email: String): Boolean {
        val result = database.insert(Admins) {
            set(it.username, username)
            set(it.password, password)
            set(it.email, email)
        }

        return result == 1
    }

    override fun deleteUser(id: Long): Boolean {

        return database.delete(Admins) {
            it.id eq id
        } == 1
    }

    override fun updateUser(id: Long, username: String, password: String, email: String): Boolean {

        return database.update(Admins) {
            set(it.username, username)
            set(it.password, password)
            set(it.email, email)
            where {
                it.id eq id
            }
        } == 1
    }

    override fun selectUserById(id: Long): Admin {

        return database.sequenceOf(Admins).first {
            it.id eq id
        }
    }

    override fun getAllUsers(): List<Admin> {

            return database.sequenceOf(Admins).toList()
    }

    override fun pageUsers(page: Int, size: Int): Page<Admin> {

        val totalElements = database.sequenceOf(Admins).count()
        val totalPages = (totalElements + size - 1) / size
        val content = database.from(Admins)
            .select()
            .limit((page - 1) * size, size)
            .map { row ->
                Admins.createEntity(row)
            }
            .toList()

        return Page(content, totalElements, totalPages, size)
    }


    override fun getUserRoles(id: Int): List<Role> {

        val data = database .from(Roles)
            .leftJoin(UserRoles, on = Roles.id eq UserRoles.roleId)
            .select(Roles.columns)
            .where(UserRoles.userId eq id)
            .map { row ->
                Roles.createEntity(row)
            }.toList()

        return data
    }


}