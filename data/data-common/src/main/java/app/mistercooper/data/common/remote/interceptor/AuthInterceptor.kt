package app.mistercooper.data.common.remote.interceptor

import app.mistercooper.data.common.local.LocalUserDataSource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val localUserDataSource: LocalUserDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("auth-token", localUserDataSource.getApiKey().orEmpty())
                .build()
        )
    }
}