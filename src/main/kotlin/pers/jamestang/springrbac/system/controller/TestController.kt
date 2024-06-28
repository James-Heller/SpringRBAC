package pers.jamestang.springrbac.system.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pers.jamestang.springrbac.system.util.Resp

@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping("/test1")
    @PreAuthorize("hasRole('ROLE_CREATOR')")
    fun test1(): Resp {
        return Resp.success()
    }
}