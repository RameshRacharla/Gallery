package com.application.di.component

import com.application.ui.splash.SplashActivity
import com.application.di.ActivityScope
import com.application.di.module.ActivityModule
import com.application.ui.gallery.GalleryViewActivity
import com.application.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: MainActivity)
    fun inject(activity: GalleryViewActivity)


}