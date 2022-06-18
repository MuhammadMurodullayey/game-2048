package uz.gita.a2048_game.app

import android.app.Application
import uz.gita.a2048_game.data.local.MySharedPreferenced
import uz.gita.a2048_game.data.repastory.AppRepositoryImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferenced.init(this)
        AppRepositoryImpl.init()
    }
}
