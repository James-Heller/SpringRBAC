package pers.jamestang.springrbac.system.service

import pers.jamestang.springrbac.system.entity.Department
import pers.jamestang.springrbac.system.util.Page

interface IDeptService {

    fun getList(): List<Department>

    fun getPageList(page: Int, size: Int): Page<Department>

    fun createDept(department: Department): Boolean

    fun updateDept(department: Department): Boolean

    fun deleteDept(id: Int): Boolean

    fun setPrimaryHead(deptId: Int, userId: Int?): Boolean

    fun setSecondaryHead(deptId: Int, userId: Int?): Boolean

    fun changeDeptMembers(deptId: Int, userIds: List<Int>): Boolean
}