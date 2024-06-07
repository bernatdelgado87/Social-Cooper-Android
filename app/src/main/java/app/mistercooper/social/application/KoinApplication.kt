package app.mistercooper.social.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import app.mistercooper.data.comment.koin.commentDataModule
import app.mistercooper.data.comment.koin.commonDataModule
import app.mistercooper.data.common.di.mediaDataModule
import app.mistercooper.data.home.koin.homeDataModule
import app.mistercooper.data.main.koin.mainDataModule
import app.mistercooper.data.publish.koin.publishDataModule
import app.mistercooper.data.register_login.koin.registerLoginDataModule
import app.mistercooper.domain.comment.koin.commentDomainModule
import app.mistercooper.domain.comment.koin.publishDomainModule
import app.mistercooper.domain.comment.koin.registerLoginDomainModule
import app.mistercooper.domain.common.feature.media.koin.mediaDomainModule
import app.mistercooper.domain.home.koin.homeDomainModule
import app.mistercooper.domain_shared_common.user.koin.commonDomainModule
import app.mistercooper.social.di.appAndroidModule
import app.mistercooper.ui.common.viewModel.MediaViewModel
import app.mistercooper.ui_comment_shared.viewmodel.CommentViewModel
import app.mistercooper.ui_home_shared.viewmodel.HomeViewModel
import app.mistercooper.ui_main_shared.MainViewModel
import app.mistercooper.ui_registerlogin_shared.viewmodel.RegisterLoginViewModel
import app.mistercooper.ui_sharedui_publish_shared.viewmodel.PublishViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        startKoin {
            androidContext(this@KoinApplication)

            modules(
                appAndroidModule,
                mediaDomainModule,
                mediaDataModule,
                mainDataModule,
                commentDataModule,
                commentDomainModule,
                commonDomainModule,
                commonDataModule,
                homeDomainModule,
                homeDataModule,
                publishDomainModule,
                publishDataModule,
                registerLoginDomainModule,
                registerLoginDataModule,
                *listOf(
                    module {
                        factory { CommentViewModel(get(), get()) }
                        factory { MediaViewModel(get(), get()) }
                        factory { HomeViewModel(get(), get()) }
                        factory { MainViewModel(get()) }
                        factory { PublishViewModel(get()) }
                        factory { RegisterLoginViewModel(get(), get()) }
                    }
                ).toTypedArray()
            )
        }
    }
}

