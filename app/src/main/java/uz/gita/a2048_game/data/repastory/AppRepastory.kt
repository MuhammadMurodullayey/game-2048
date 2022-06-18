package uz.gita.a2048_game.data.repastory

interface AppRepastory {
    fun getMatrixData() : Array<Array<Int>>

    fun getOldMatrixData(): Array<Array<Int>>
    fun fillMatrix()
    fun getScore() : Int
    fun checkToFinish() : Boolean
    fun isWin() : Boolean
    fun getBoolAdd() : Boolean
    fun soundBegin() : Boolean
    fun setsoundBegin(sound:Boolean)
    fun putMatrixToSharedPreference()
    fun fillMatrixForReplay()
    fun moveLeft()
    fun moveRight()
    fun moveUp()
    fun moveDown()
}