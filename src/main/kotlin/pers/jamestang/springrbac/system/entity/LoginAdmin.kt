package pers.jamestang.springrbac.system.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class LoginAdmin(private val admin: Admin): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf<SimpleGrantedAuthority>()
    }

    override fun getPassword(): String {
        return admin.password
    }

    override fun getUsername(): String {
        return admin.username
    }
}