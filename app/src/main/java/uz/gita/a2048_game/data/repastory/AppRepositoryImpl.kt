package uz.gita.a2048_game.data.repastory

import uz.gita.a2048_game.data.local.MySharedPreferenced
import java.lang.StringBuilder
import kotlin.random.Random

class AppRepositoryImpl() : AppRepastory {
    private val preference by lazy { MySharedPreferenced.getInstance() }
    private var isAdd : Boolean = false
    private var soundBigin : Boolean = false

    companion object {
        private var obj: AppRepastory? = null
        fun init() {
            obj = AppRepositoryImpl()
        }

        fun getAppRepasitory(): AppRepastory {
            return obj!!
        }
    }

    private var score = 0
    private var matrix: Array<Array<Int>> =
    arrayOf(
    arrayOf(0, 0, 0, 0),
    arrayOf(0, 0, 0, 0),
    arrayOf(0, 0, 0, 0),
    arrayOf(0, 0, 0, 0)
    )
    private val ADD_ELEMENT = 2

    init {
        initMatrix()
        if (preference.init) {
            addNewElementToMatrix()
            addNewElementToMatrix()
            preference.init = false
        }
    }





    private fun initMatrix(){
        val stringMatrix = preference.matrix?.split("#")
        var index = 0
        for (i in 0 until 4){
            for (j in 0 until 4){
                matrix[i][j] = Integer.parseInt(stringMatrix?.get(index++) ?: "0")
            }
        }
        index = 0

    }

    private val oldMatrix: Array<Array<Int>> =
        arrayOf(
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0),
            arrayOf(0, 0, 0, 0)
        )



    private var isWin = false
    override fun isWin(): Boolean {
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[i].size) {
                if ( matrix[i][j] == 2048){
                    if (preference.record < score){
                        preference.record = score
                    }
                    score = 0
                    return true
                }

            }
        }
        return false
    }

    override fun getBoolAdd(): Boolean {
        return isAdd
    }

    override fun soundBegin() : Boolean {
        return soundBigin
    }

    override fun setsoundBegin(sound: Boolean) {
        soundBigin = sound
    }

    override fun putMatrixToSharedPreference() {
        val stringBuilder : StringBuilder = StringBuilder()
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[i].size) {
            stringBuilder.append(matrix[i][j]).append("#")
            }
        }
        stringBuilder.deleteAt(stringBuilder.lastIndex)
        preference.matrix = stringBuilder.toString()
    }


    private fun fillOldMatrix() {
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[i].size) {
                oldMatrix[i][j] = matrix[i][j]
            }
        }
    }

    override fun fillMatrix() {
        if (chackOldMatrix())
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[i].size) {
                matrix[i][j] = oldMatrix[i][j]
            }
        }
    }
   fun chackOldMatrix() : Boolean{
       for (i in 0 until matrix.size) {
           for (j in 0 until matrix[i].size) {
             if (oldMatrix[i][j] != 0){
                 return true
             }
           }
       }
       return false
   }

    override fun getScore(): Int {
        return score
    }

    override fun checkToFinish(): Boolean {
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[i].size) {
                if (j - 1 != -1 && matrix[i][j] == matrix[i][j - 1] && matrix[i][j] != 0) {
                    return false
                }
                if (j + 1 != 4 && matrix[i][j] == matrix[i][j + 1]&& matrix[i][j] != 0) {
                    return false
                }
                if (i - 1 != -1 && matrix[i][j] == matrix[i - 1][j]&& matrix[i][j] != 0) {
                    return false
                }
                if (i + 1 != 4 && matrix[i][j] == matrix[i + 1][j]&& matrix[i][j] != 0) {
                    return false
                }
                if (matrix[i][j] == 0)return false
            }
        }
        if (preference.record < score){
            preference.record = score
        }
        score = 0
        return true
    }


    override fun fillMatrixForReplay() {
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[i].size) {
                matrix[i][j] = 0
            }
        }
        score = 0
        addNewElementToMatrix()
        addNewElementToMatrix()
    }


    private fun addNewElementToMatrix() {
        val coordinates = ArrayList<Pair<Int, Int>>()
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] == 0) {
                    coordinates.add(Pair(i, j))
                }
            }
        }
        if (coordinates.size != 0) {
            val randomIndex = Random.nextInt(0, coordinates.size)
            matrix[coordinates[randomIndex].first][coordinates[randomIndex].second] = ADD_ELEMENT
        }
    }

    override fun getMatrixData(): Array<Array<Int>> = matrix
    override fun getOldMatrixData(): Array<Array<Int>>  = oldMatrix


    override fun moveLeft() {
        isAdd = false
        fillOldMatrix()
        soundBigin = true
        for (i in 0 until matrix.size) {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] == 0) continue
                if (amount.isEmpty()) amount.add(matrix[i][j])
                else {
                    if (amount.last() == matrix[i][j] && bool) {
                        isAdd = true
                        score += amount.last() * 2
                        amount[amount.size - 1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix[i][j])
                    }
                }

                matrix[i][j] = 0
            }
            for (j in 0 until amount.size) {
                matrix[i][j] = amount[j]
            }
        }
        if (!checkToFinish() && !isWin) {
            addNewElementToMatrix()
        }
    }

    override fun moveRight() {
        isAdd = false
        fillOldMatrix()
        soundBigin = true

        for (i in 0 until matrix.size) {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].size - 1 downTo 0) {
                if (matrix[i][j] == 0) continue
                if (amount.isEmpty()) amount.add(matrix[i][j])
                else {
                    if (amount.last() == matrix[i][j] && bool) {
                        isAdd = true
                        score += amount.last() * 2
                        amount[amount.size - 1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix[i][j])
                    }
                }

                matrix[i][j] = 0
            }
            for (k in 0 until amount.size) {
                matrix[i][3 - k] = amount[k]
            }
        }
        if (!checkToFinish() && !isWin) {
            addNewElementToMatrix()
        }

    }

    override fun moveUp() {
        isAdd = false
        fillOldMatrix()
        soundBigin = true

        for (i in 0 until matrix.size) {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in 0 until matrix[i].size) {
                if (matrix[j][i] == 0) continue
                if (amount.isEmpty()) amount.add(matrix[j][i])
                else {
                    if (amount.last() == matrix[j][i] && bool) {
                        isAdd = true
                        score += amount.last() * 2
                        amount[amount.size - 1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix[j][i])
                    }
                }

                matrix[j][i] = 0
            }
            for (j in 0 until amount.size) {
                matrix[j][i] = amount[j]
            }
        }
        if (!checkToFinish() && !isWin) {
            addNewElementToMatrix()
        }
    }

    override fun moveDown() {
        isAdd = false
        fillOldMatrix()
        soundBigin = true

        for (i in 0 until matrix.size) {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].size - 1 downTo 0) {
                if (matrix[j][i] == 0) continue
                if (amount.isEmpty()) amount.add(matrix[j][i])
                else {
                    if (amount.last() == matrix[j][i] && bool) {
                        isAdd = true
                        score += amount.last() * 2
                        amount[amount.size - 1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix[j][i])
                    }
                }

                matrix[j][i] = 0
            }
            for (k in 0 until amount.size) {
                matrix[3 - k][i] = amount[k]
            }
        }
        if (!checkToFinish() && !isWin) {
            addNewElementToMatrix()
        }
    }


}