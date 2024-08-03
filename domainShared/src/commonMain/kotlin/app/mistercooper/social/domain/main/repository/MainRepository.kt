package app.mistercooper.social.domain.main.repository

interface MainRepository {
    fun isUserRegistered(): Boolean
    fun isFullRegistered(): Boolean
}