package pers.jamestang.springrbac.system.service

import pers.jamestang.springrbac.system.entity.Admin
import pers.jamestang.springrbac.system.entity.Menu
import pers.jamestang.springrbac.system.entity.Role
import pers.jamestang.springrbac.system.util.Page


interface IAdminService {

    fun insertUser(username: String, password: String, email: String): Boolean

    fun deleteUser(id: Int): Boolean

    fun updateUser(id: Int, username: String, password: String, email: String): Boolean

    fun selectUserById(id: Int): Admin

    fun getAllUsers(): List<Admin>

    fun pageUsers(page: Int, size: Int): Page<Admin>

    fun getUserRoles(id: Int): List<Role>

    fun setUserRoles(id: Int, roleIds: List<Int>): Boolean

    fun getUserMenus(id: Int): List<Menu>

}