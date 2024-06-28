package pers.jamestang.springrbac.system.security

import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class CustomPermissionEvaluator : PermissionEvaluator{
    override fun hasPermission(authentication: Authentication, targetDomainObject: Any, permission: Any): Boolean {
        authentication.authorities.forEach {
            if (it.authority == permission) {
                return true
//                TODO real access control
            }
        }
        return false
    }

    override fun hasPermission(
        authentication: Authentication?,
        targetId: Serializable?,
        targetType: String?,
        permission: Any?
    ): Boolean {
        return false
    }
}