package pers.jamestang.springrbac.system.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pers.jamestang.springrbac.system.service.IAdminService
import pers.jamestang.springrbac.system.util.Resp

@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminService: IAdminService
) {

    @GetMapping("/page")
    fun getPage(currentPage: Long, size: Long) = Resp.data(adminService.pageUsers(currentPage.toInt(), size.toInt()))

    @GetMapping("/list")
    fun list() = Resp.data(adminService.getAllUsers())

    @GetMapping("/get")
    fun get(id: Long) = Resp.data(adminService.selectUserById(id))

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) = Resp.data(adminService.deleteUser(id))


}