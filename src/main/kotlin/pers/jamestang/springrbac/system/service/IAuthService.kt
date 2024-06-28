package pers.jamestang.springrbac.system.service

interface IAuthService {

    /**
     * Login by password.
     * @param username the username
     * @param password the password
     * @return the token
     */
    fun loginByPassword(username: String, password: String): String


    fun registry(username: String, password: String, email: String): Boolean
}