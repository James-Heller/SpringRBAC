package pers.jamestang.springrbac.system.service

import pers.jamestang.springrbac.system.entity.Menu

interface IMenuService {

    fun listMenu(): List<Menu>

    fun addMenu(menu: Menu): Boolean

    fun updateMenu(menu: Menu): Boolean

    fun deleteMenu(menuId: Int): Boolean
}