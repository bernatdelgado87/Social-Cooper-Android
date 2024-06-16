package app.mistercooper.domain_shared_common.user.repository

interface MainRepository {
    fun isUserRegistered(): Boolean
}