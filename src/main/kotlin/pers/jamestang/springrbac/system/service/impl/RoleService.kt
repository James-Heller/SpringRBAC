package pers.jamestang.springrbac.system.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.count
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pers.jamestang.springrbac.system.entity.Role
import pers.jamestang.springrbac.system.repository.Roles
import pers.jamestang.springrbac.system.repository.UserRoles
import pers.jamestang.springrbac.system.service.IRoleService
import pers.jamestang.springrbac.system.util.Page

@Service
class RoleService(
    private val database: Database
) : IRoleService {

    override fun getRoleById(id: Int): Role {
        return database.sequenceOf(Roles).find { it.id eq id } ?: throw Exception("Role not found")
    }

    override fun pageRole(currentPage: Int, size: Int): Page<Role> {
        val total = database.sequenceOf(Roles).count()
        val roles = database.from(Roles).select().limit((currentPage - 1) * size, size).map { Roles.createEntity(it) }
        return Page(roles, total, total / size + 1, size)
    }

    override fun list(): List<Role> {

        return database.sequenceOf(Roles).toList()
    }

    override fun createRole(role: Role): Boolean {

        val result = database.insert(Roles) {
            set(it.roleCode, role.roleCode)
            set(it.roleName, role.roleName)
        }
        return result == 1
    }

    override fun updateRole(role: Role): Boolean {

        val result = database.update(Roles) {
            set(it.roleCode, role.roleCode)
            set(it.roleName, role.roleName)
            where {
                it.id eq role.id
            }
        }
        return result == 1
    }

    @Transactional
    override fun deleteRole(id: Int): Boolean {
        val deleteRoleFlag = database.delete(Roles) {
            it.id eq id
        } == 1

        if (deleteRoleFlag){
            database.delete(UserRoles){
                it.roleId eq id
            }
        }
        return deleteRoleFlag
    }


}