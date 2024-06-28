package pers.jamestang.springrbac.system.entity

import org.ktorm.entity.Entity


interface Admin : Entity<Admin>{
    companion object : Entity.Factory<Admin>()
    val id: Long
    var username: String
    var password: String
    var email: String


}