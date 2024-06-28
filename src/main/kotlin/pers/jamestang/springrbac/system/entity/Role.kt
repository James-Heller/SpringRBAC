package pers.jamestang.springrbac.system.entity

import org.ktorm.entity.Entity

interface Role: Entity<Role> {
    companion object : Entity.Factory<Role>()
    val id: Long
    var roleCode: String
    var roleName: String
}