package uz.gita.a2048_game.mvp.presenter

import uz.gita.a2048_game.mvp.contracts.MainContract
import uz.gita.a2048_game.mvp.madels.MainModel

class MainPresenter(private val view : MainContract.View) : MainContract.Presenter {
    private val model : MainContract.Madel = MainModel()
    override fun clickPlayButton() {
        view.openPlayScreen()
    }

    override fun clickExitButton() {
       view.openExitScreen()
    }

    override fun clickAboutButton() {
       view.openAboutScreen()
    }
}