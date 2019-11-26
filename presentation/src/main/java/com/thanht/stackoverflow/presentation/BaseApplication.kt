package com.thanht.stackoverflow.presentation

import android.app.Application

import com.thanht.stackoverflow.presentation.di.component.ApplicationComponent
import com.thanht.stackoverflow.presentation.di.component.DaggerApplicationComponent
import com.thanht.stackoverflow.presentation.di.component.DaggerUserComponent
import com.thanht.stackoverflow.presentation.di.component.UserComponent

class BaseApplication : Application() {
    private lateinit var mApplicationComponent: ApplicationComponent
    lateinit var userComponent: UserComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        mApplicationComponent = DaggerApplicationComponent.builder()
                .context(this)
                .build()
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(mApplicationComponent)
                .build()
    }

    companion object {
        lateinit var instance: BaseApplication
    }
}
