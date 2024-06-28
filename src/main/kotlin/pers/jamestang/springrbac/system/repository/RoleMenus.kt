package pers.jamestang.springrbac.system.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import pers.jamestang.springrbac.system.entity.RoleMenu

class RoleMenus: Table<RoleMenu>("role_menus") {
    val roleId = int("role_id").primaryKey().bindTo { it.roleId }
    val menuId = int("menu_id").primaryKey().bindTo { it.menuId }
}