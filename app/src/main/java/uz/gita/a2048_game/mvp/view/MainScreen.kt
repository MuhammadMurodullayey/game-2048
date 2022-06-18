package uz.gita.a2048_game.mvp.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import uz.gita.a2048_game.R
import uz.gita.a2048_game.data.local.MySharedPreferenced
import uz.gita.a2048_game.mvp.contracts.MainContract
import uz.gita.a2048_game.mvp.presenter.MainPresenter


class MainScreen : Fragment(R.layout.activity_play) , MainContract.View{
    private var onPlayScreenListener : (()-> Unit)? = null
    private var onExitListener : (()-> Unit)? = null
    private var onOpenAboutScreen : (()-> Unit)? = null
    private val preferenseces by lazy { MySharedPreferenced.getInstance() }
    private val presenter : MainContract.Presenter = MainPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       val playButton : TextView = view.findViewById(R.id.play_game)
       val exitButton : TextView = view.findViewById(R.id.exit)
       val aboutButton : TextView = view.findViewById(R.id.about)
        playButton.setOnClickListener { presenter.clickPlayButton() }
        exitButton.setOnClickListener { presenter.clickExitButton() }
        aboutButton.setOnClickListener { presenter.clickAboutButton() }
    }

    fun setOnPlayScreenListener(block : ()-> Unit){
        onPlayScreenListener = block
    }
    fun setOnOpenAboutScreenListener(block : ()-> Unit){
        onOpenAboutScreen = block
    }
    fun setOnExitListener(block : ()-> Unit){
        onExitListener = block
    }

    override fun openPlayScreen() {
        onPlayScreenListener?.invoke()
    }

    override fun openExitScreen() {
    onExitListener?.invoke()
    }

    override fun openAboutScreen() {
      onOpenAboutScreen?.invoke()

    }

    override fun onResume() {
        super.onResume()


    }

    override fun onPause() {
        super.onPause()


    }
}