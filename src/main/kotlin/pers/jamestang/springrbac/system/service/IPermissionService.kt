package pers.jamestang.springrbac.system.service

import pers.jamestang.springrbac.system.entity.Permission
import pers.jamestang.springrbac.system.util.Page

interface IPermissionService {

    fun listAll(): List<Permission>

    fun page(currentPage: Int, size: Int): Page<Permission>

    fun create(permission: Permission): Boolean

    fun update(permission: Permission): Boolean

    fun delete(id: Int): Boolean
}