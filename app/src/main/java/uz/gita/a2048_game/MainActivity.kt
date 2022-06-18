package uz.gita.a2048_game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uz.gita.a2048_game.mvp.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = MainScreen()

        mainFragment.setOnOpenAboutScreenListener {
            val aboutScreen = AboutScreen()
            openScreenAddStack(aboutScreen)
            aboutScreen.setOnClickBackListener {
                aboutScreen.activity?.onBackPressed()
            }
        }
        mainFragment.setOnPlayScreenListener {
            val playFragment = GameScreen()
            openScreenAddStack(playFragment)
            playFragment.setOnBackHomeListener {
                playFragment.activity?.onBackPressed()
            }

        }
        mainFragment.setOnExitListener {
            finish()
        }
        openScreenAddStack(mainFragment)


    }

    private fun openScreenAddStack(fm: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, fm)
            .addToBackStack(fm.javaClass.name)
            .commit()
    }

    private fun openScreen(fm: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, fm)
            .commit()
    }

}