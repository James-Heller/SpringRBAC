package pers.jamestang.springrbac.system.entity

import org.ktorm.entity.Entity

interface RoleMenu: Entity<RoleMenu> {
    companion object: Entity.Factory<RoleMenu>()
    val roleId: Int
    val menuId: Int
}