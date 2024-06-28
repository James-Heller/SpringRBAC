package pers.jamestang.springrbac.system.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(

) {

    @GetMapping("/test1")
    fun test1(code: String): String{
        return "dddd"
    }
}