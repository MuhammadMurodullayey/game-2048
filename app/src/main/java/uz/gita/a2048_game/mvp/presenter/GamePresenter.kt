package uz.gita.a2048_game.mvp.presenter

import uz.gita.a2048_game.mvp.contracts.GameContract
import uz.gita.a2048_game.mvp.madels.GameModel

class GamePresenter(private val view: GameContract.View) : GameContract.Presenter {
    private val model: GameContract.Madel = GameModel()
    val matrix = model.getMatrixData()
    val oldMatrix = model.getOldMatrixData()
    override fun soundBegin(): Boolean {
        return model.soundBegin()
    }

    override fun soundBegin(sound: Boolean) {
        model.soundBegin(sound)
    }

    override fun isAddTrue(): Boolean {
        return model.isAddTrue()
    }

    override fun callPutSharedPreference() {
        model.callPutSharedPreference()
    }

    override fun checkToFinish(): Boolean {
        return model.checkToFinish()
    }

    override fun isWin(): Boolean {
        return model.isWin()
    }

    override fun fillMatrixForReplay() {
        model.fillMatrixForReplay()
    }

    override fun openDialogFinish() {

    }

    override fun openDialogWin() {

    }

    override fun startPlay() {
        view.illustrateMatrix(model.getMatrixData())
    }

    override fun swipeLeft() {
        model.moveLeft()
        view.illustrateMatrix(model.getMatrixData())
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                if (matrix[i][j] != 0 && oldMatrix[i][j] != matrix[i][j])
                    view.swipeLeftAnimation(i * 4 + j)

    }

    override fun swipeRight() {

        model.moveRight()
        view.illustrateMatrix(matrix)
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                if (matrix[i][j] != 0 && oldMatrix[i][j] != matrix[i][j])
                    view.swipeRightAnimation(i * 4 + j)

    }

    override fun setScore(): Int {
        return model.getScore()
    }

    override fun swipeUp() {
        model.moveUp()
        view.illustrateMatrix(model.getMatrixData())
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                if (matrix[i][j] != 0 && oldMatrix[i][j] != matrix[i][j])
                    view.swipeUpAnimation(i * 4 + j)
    }

    override fun swipeDown() {
        model.moveUDown()
        view.illustrateMatrix(model.getMatrixData())
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                if (matrix[i][j] != 0 && oldMatrix[i][j] != matrix[i][j])
                    view.swipeDownAnimation(i * 4 + j)
    }

    override fun clickHomeButton() {
        view.backToHome()
    }

    override fun clickPlayAgain() {
        view.playAgain()

    }

    override fun clickReturnBack() {
        model.fillMatrix()
        view.illustrateMatrix(model.getMatrixData())

    }
}