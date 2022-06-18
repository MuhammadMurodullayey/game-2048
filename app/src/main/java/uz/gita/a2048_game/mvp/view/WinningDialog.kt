package uz.gita.a2048_game.mvp.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import uz.gita.a2048_game.R
import uz.gita.a2048_game.data.repastory.AppRepastory
import uz.gita.a2048_game.data.repastory.AppRepositoryImpl

class WinningDialog : DialogFragment() {
    private var onRePlayListener: (() -> Unit)? = null
    private var onBackToHomeListener: (() -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialog = inflater.inflate(R.layout.activity_dialog_win, container, false)
        return dialog

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val cancelButton: AppCompatButton = view.findViewById(R.id.cancel_dialog)
        val yesButton: AppCompatButton = view.findViewById(R.id.yes_dialog)
        val text : TextView = view.findViewById(R.id.text_dialog)
       val repastory : AppRepastory = AppRepositoryImpl.getAppRepasitory()
        if (repastory.checkToFinish()){
            text.text = "Game Over"
        }else if (repastory.isWin()){
            text.text = "Congratulations \n You  Win"
        }
        yesButton.setOnClickListener {
            onRePlayListener?.invoke()
            dismiss()
        }
        cancelButton.setOnClickListener {
            dismiss()
            onBackToHomeListener?.invoke()
        }

    }

    fun setOnReplayListener(block: () -> Unit) {
        onRePlayListener = block
    }
    fun setOnBackToHomeListener(block: () -> Unit) {
        onBackToHomeListener = block
    }
}