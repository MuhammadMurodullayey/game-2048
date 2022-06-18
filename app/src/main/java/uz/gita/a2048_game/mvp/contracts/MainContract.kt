package uz.gita.a2048_game.mvp.contracts

interface MainContract {
    interface Madel

    interface View{
        fun openPlayScreen()
        fun openExitScreen()
        fun openAboutScreen()
    }


    interface Presenter{
        fun clickPlayButton()
        fun clickExitButton()
        fun clickAboutButton()

    }
}