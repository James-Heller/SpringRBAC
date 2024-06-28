package pers.jamestang.springrbac.system.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import pers.jamestang.springrbac.system.security.DBAuthHandler
import pers.jamestang.springrbac.system.security.filter.JwtAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val dbAuthHandler: DBAuthHandler,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http{

            authorizeRequests {
                authorize("/auth/login", permitAll)
                authorize("/auth/registry", permitAll)
                authorize(anyRequest, authenticated)
            }

            formLogin { disable() }
            csrf { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtAuthenticationFilter)

        }


        return http.build()
    }

    @Bean
    fun injectAuthenticationManager(authenticationConfiguration: AuthenticationConfiguration): ProviderManager {

        val authenticationFunctionList = listOf(
            daoAuthenticationProvider()
        )

        return ProviderManager(authenticationFunctionList)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(dbAuthHandler)
        return daoAuthenticationProvider
    }
}