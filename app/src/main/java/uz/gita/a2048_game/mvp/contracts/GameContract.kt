package uz.gita.a2048_game.mvp.contracts

interface GameContract {
    interface Madel {
        fun getMatrixData(): Array<Array<Int>>
        fun getOldMatrixData(): Array<Array<Int>>
        fun getScore(): Int
        fun fillMatrix()
        fun fillMatrixForReplay()
        fun callPutSharedPreference()
        fun checkToFinish(): Boolean
        fun isWin(): Boolean
        fun soundBegin(): Boolean
        fun soundBegin(sound: Boolean)
        fun isAddTrue(): Boolean
        fun moveLeft()
        fun moveRight()
        fun moveUp()
        fun moveUDown()

    }

    interface View {
        fun illustrateMatrix(matrix: Array<Array<Int>>)
        fun backToHome()
        fun playAgain()
        fun returnBack()

        fun swipeUpAnimation(position: Int)
        fun swipeDownAnimation(position: Int)
        fun swipeLeftAnimation(position: Int)
        fun swipeRightAnimation(position: Int)


    }

    interface Presenter {
        fun soundBegin(): Boolean
        fun soundBegin(sound: Boolean)
        fun isAddTrue(): Boolean
        fun callPutSharedPreference()
        fun checkToFinish(): Boolean
        fun isWin(): Boolean
        fun fillMatrixForReplay()
        fun openDialogFinish()
        fun openDialogWin()
        fun startPlay()
        fun swipeLeft()
        fun swipeRight()
        fun setScore(): Int
        fun swipeUp()
        fun swipeDown()
        fun clickHomeButton()
        fun clickPlayAgain()
        fun clickReturnBack()

    }
}