package uz.gita.a2048_game.mvp.madels

import android.os.Parcel
import android.os.Parcelable
import uz.gita.a2048_game.data.repastory.AppRepastory
import uz.gita.a2048_game.data.repastory.AppRepositoryImpl
import uz.gita.a2048_game.mvp.contracts.GameContract


class GameModel() : GameContract.Madel, Parcelable {
    private val repastory : AppRepastory = AppRepositoryImpl.getAppRepasitory()

    constructor(parcel: Parcel) : this() {
    }

    override fun getMatrixData(): Array<Array<Int>> {
        return repastory.getMatrixData()
    }

    override fun getOldMatrixData(): Array<Array<Int>> = repastory.getOldMatrixData()

    override fun getScore(): Int {
        return repastory.getScore()
    }

    override fun fillMatrix() {
        return repastory.fillMatrix()
    }

    override fun fillMatrixForReplay() {
        repastory.fillMatrixForReplay()
    }

    override fun moveLeft() {
       repastory.moveLeft()
    }

    override fun callPutSharedPreference() {
        repastory.putMatrixToSharedPreference()
    }

    override fun checkToFinish(): Boolean {
        return repastory.checkToFinish()

    }

    override fun isWin(): Boolean {
        return repastory.isWin()
    }

    override fun soundBegin(): Boolean {
        return repastory.soundBegin()
    }

    override fun soundBegin(sound: Boolean) {
        repastory.setsoundBegin(sound)
    }

    override fun isAddTrue(): Boolean {
        return repastory.getBoolAdd()
    }

    override fun moveRight() {
        repastory.moveRight()
    }

    override fun moveUp() {
        repastory.moveUp()
    }

    override fun moveUDown() {
        repastory.moveDown()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameModel> {
        override fun createFromParcel(parcel: Parcel): GameModel {
            return GameModel(parcel)
        }

        override fun newArray(size: Int): Array<GameModel?> {
            return arrayOfNulls(size)
        }
    }
}