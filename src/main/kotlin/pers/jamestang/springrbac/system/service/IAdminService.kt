package pers.jamestang.springrbac.system.service

import pers.jamestang.springrbac.system.entity.Admin
import pers.jamestang.springrbac.system.entity.Role
import pers.jamestang.springrbac.system.util.Page


interface IAdminService {

    fun insertUser(username: String, password: String, email: String): Boolean

    fun deleteUser(id: Long): Boolean

    fun updateUser(id: Long, username: String, password: String, email: String): Boolean

    fun selectUserById(id: Long): Admin

    fun getAllUsers(): List<Admin>

    fun pageUsers(page: Int, size: Int): Page<Admin>

    fun getUserRoles(id: Long): List<Role>
}