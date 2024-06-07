package app.mistercooper.data.home.repository

import app.mistercooper.domain_shared_common.user.repository.MainRepository
import com.jetbrains.kmpapp.local.KeyValueStorageInterface


class MainRepositoryImpl (val localUserDataSource: KeyValueStorageInterface):
    MainRepository {
    override fun isUserRegistered(): Boolean =
        localUserDataSource.token?.isNotEmpty() == true

}