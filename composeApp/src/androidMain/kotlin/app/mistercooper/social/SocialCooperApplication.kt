package app.mistercooper.social

import android.app.Application
import app.di.initSocialKoin
import app.mistercooper.social.di.appModule

class SocialCooperApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initSocialKoin {
            appModule
        }
    }
}