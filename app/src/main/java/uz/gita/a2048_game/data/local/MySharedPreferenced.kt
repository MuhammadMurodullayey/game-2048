package uz.gita.a2048_game.data.local

import android.content.Context
import android.content.SharedPreferences


class MySharedPreferenced {
    companion  object{
        private var preferenced : SharedPreferences? = null
        private var mySharedPreferences : MySharedPreferenced? = null

        fun init(context: Context){
            when{
                preferenced != null -> return
                else -> {
                    mySharedPreferences = MySharedPreferenced()
                    preferenced = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
                }
            }
        }
        fun getInstance() : MySharedPreferenced = mySharedPreferences!!



    }
    var record:Int
        get() = preferenced!!.getInt("RECORD",0)
        set(value) = preferenced!!.edit().putInt("RECORD",value).apply()

    var bool:Boolean
        get() = preferenced!!.getBoolean("BOOL",false)
        set(value) = preferenced!!.edit().putBoolean("BOOL",value).apply()
    var init:Boolean
        get() = preferenced!!.getBoolean("INIT",true)
        set(value) = preferenced!!.edit().putBoolean("INIT",value).apply()
    var matrix : String?
        get() = preferenced!!.getString("MATRIX",null)
        set(value) = preferenced!!.edit().putString("MATRIX",value).apply()

}