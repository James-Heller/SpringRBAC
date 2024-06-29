package pers.jamestang.springrbac.system.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import pers.jamestang.springrbac.system.entity.Role

object Roles: Table<Role>("system_role") {
    val id = int("id").primaryKey().bindTo { it.id }
    val roleCode = varchar("role_code").bindTo { it.roleCode }
    val roleName = varchar("role_name").bindTo { it.roleName }
}