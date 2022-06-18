package uz.gita.a2048_game.mvp.view

import android.app.DialogFragment
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import uz.gita.a2048_game.R


class MyDialog : DialogFragment() {
    private var onRePlayListener: (() -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialog = inflater.inflate(R.layout.activity_dialog, container, false)
        return dialog

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val cancelButton: AppCompatButton = view.findViewById(R.id.cancel_dialog)
        val yesButton: AppCompatButton = view.findViewById(R.id.yes_dialog)
        yesButton.setOnClickListener {
            onRePlayListener?.invoke()
            dismiss()
        }
        cancelButton.setOnClickListener {
            dismiss()
        }

    }

    fun setOnReplayListener(block: () -> Unit) {
        onRePlayListener = block
    }


}
