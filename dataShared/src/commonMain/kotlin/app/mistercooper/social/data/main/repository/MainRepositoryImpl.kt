package app.mistercooper.data.home.repository

import app.mistercooper.social.domain.main.repository.MainRepository
import com.jetbrains.kmpapp.local.KeyValueStorageInterface


class MainRepositoryImpl (val localUserDataSource: KeyValueStorageInterface):
    MainRepository {
    override fun isUserRegistered(): Boolean =
        localUserDataSource.token?.isNotEmpty() == true

    override fun isFullRegistered(): Boolean =
        localUserDataSource.profileCompleted == true

}