package pers.jamestang.springrbac.system.service.impl

import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.*
import org.springframework.stereotype.Service
import pers.jamestang.springrbac.system.entity.Menu
import pers.jamestang.springrbac.system.repository.Menus
import pers.jamestang.springrbac.system.service.IMenuService

@Service
class MenuService(
    private val database: Database
): IMenuService {
    override fun listMenu(): List<Menu> {
        return database.sequenceOf(Menus).toList()
    }

    override fun addMenu(menu: Menu): Boolean {
        return database.sequenceOf(Menus).add(menu) == 1
    }

    override fun updateMenu(menu: Menu): Boolean {

        return database.sequenceOf(Menus).update(menu) == 1
    }

    override fun deleteMenu(menuId: Int): Boolean {
        return database.sequenceOf(Menus).removeIf { it.id eq menuId } == 1
    }
}