package pers.jamestang.springrbac.system.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import pers.jamestang.springrbac.system.entity.RolePermission

object RolePermissions: Table<RolePermission>("role_permissions") {
    val roleId = int("role_id").primaryKey().bindTo { it.roleId }
    val permissionId = int("permission_id").primaryKey().bindTo { it.permissionId }
}