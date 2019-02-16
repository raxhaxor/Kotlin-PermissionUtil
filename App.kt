package app

import android.app.Application
import student.bac.edu.my.di.presenterModule
import student.bac.edu.my.util.preference.SharedPreferenceUtils


/**
 * Created by Akash Saggu(R4X)
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        lateinit var application: App
        @JvmStatic
        fun getApp() = application
    }

}
