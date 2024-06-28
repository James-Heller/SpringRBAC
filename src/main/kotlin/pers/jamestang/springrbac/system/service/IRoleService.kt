package pers.jamestang.springrbac.system.service


import pers.jamestang.springrbac.system.entity.Role
import pers.jamestang.springrbac.system.util.Page

interface IRoleService {

    fun getRoleById(id: Int): Role

    fun pageRole(currentPage: Int, size: Int): Page<Role>

    fun list(): List<Role>

    fun createRole(role: Role): Boolean

    fun updateRole(role: Role): Boolean

    fun deleteRole(id: Int): Boolean

}