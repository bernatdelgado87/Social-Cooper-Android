package app.mistercooper.social

import android.app.Application
import app.di.initKoin
import app.mistercooper.social.di.appModule

class SocialCooperApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            appModule
        }
    }
}