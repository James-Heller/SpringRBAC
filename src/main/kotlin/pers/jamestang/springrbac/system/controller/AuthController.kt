package pers.jamestang.springrbac.system.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pers.jamestang.springrbac.system.service.IAuthService
import pers.jamestang.springrbac.system.util.Resp

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: IAuthService
) {

    @PostMapping("/login")
    fun loginByPassword(username: String, password: String) = Resp.data(authService.loginByPassword(username, password))

    @PostMapping("/registry")
    fun registry(username: String, password: String, email: String) = Resp.data(authService.registry(username, password, email))

    @GetMapping("/getCurrentUserMenus")
    fun getCurrentUserMenus() = Resp.data(authService.getCurrentUserMenus())

    @GetMapping("/getCurrentUserPermissions")
    fun getCurrentUserPermissions() = Resp.data(authService.getCurrentUserPermissions())
}