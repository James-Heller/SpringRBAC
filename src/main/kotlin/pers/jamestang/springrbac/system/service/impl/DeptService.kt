package pers.jamestang.springrbac.system.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pers.jamestang.springrbac.system.entity.Department
import pers.jamestang.springrbac.system.repository.DepartmentMembers
import pers.jamestang.springrbac.system.repository.Departments
import pers.jamestang.springrbac.system.service.IDeptService
import pers.jamestang.springrbac.system.util.Page

@Service
class DeptService(
    private val database: Database
): IDeptService {

    override fun getList(): List<Department> {
        return database.sequenceOf(Departments).toList()
    }

    override fun getPageList(page: Int, size: Int): Page<Department> {

        val total = database.sequenceOf(Departments).count()
        val list = database.from(Departments).select(Departments.columns).limit((page - 1) * size, size).map(Departments::createEntity)
        return Page(
            content = list,
            totalElements = total,
            totalPages = (total + size - 1) / size,
            size = size
        )

    }

    override fun createDept(department: Department): Boolean {

        return database.sequenceOf(Departments).add(department) > 0
    }

    override fun updateDept(department: Department): Boolean {
        return database.sequenceOf(Departments).update(department) > 0
    }

    @Transactional
    override fun deleteDept(id: Int): Boolean {

        database.sequenceOf(Departments).removeIf { it.id eq id } > 0

        database.sequenceOf(DepartmentMembers).removeIf { it.deptId eq id}

        return true
    }
}