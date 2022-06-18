package uz.gita.a2048_game.mvp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import uz.gita.a2048_game.R

class AboutScreen : Fragment(R.layout.activity_about) {
    private var onClickBackListener : (()-> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val backButton : AppCompatImageView= view.findViewById(R.id.back)
        backButton.setOnClickListener {
            run {
                onClickBackListener?.invoke()
            }
        }

    }
    fun setOnClickBackListener(block : (()-> Unit)){
        onClickBackListener = block
    }
}