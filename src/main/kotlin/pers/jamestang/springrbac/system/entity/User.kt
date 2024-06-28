package pers.jamestang.springrbac.system.entity

import org.ktorm.entity.Entity


interface User : Entity<User>{
    companion object : Entity.Factory<User>()
    val id: Long
    var username: String
    var password: String
    var email: String


}