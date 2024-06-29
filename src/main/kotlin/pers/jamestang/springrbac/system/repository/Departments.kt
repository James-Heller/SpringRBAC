package pers.jamestang.springrbac.system.repository

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import pers.jamestang.springrbac.system.entity.Department

object Departments: Table<Department>("system_dept") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val parentId = int("parent_id").bindTo { it.parentId }
    val primaryHead = int("primary_head").bindTo { it.primaryHead }
    val secondaryHead = int("secondary_head").bindTo { it.secondaryHead }
}