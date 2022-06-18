package uz.gita.a2048_game.mvp.view

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import uz.gita.a2048_game.R

import uz.gita.a2048_game.data.SideEnum
import uz.gita.a2048_game.data.local.MySharedPreferenced
import uz.gita.a2048_game.mvp.contracts.GameContract
import uz.gita.a2048_game.mvp.presenter.GamePresenter
import uz.gita.a2048_game.utils.MyTouchListener
import uz.gita.a2048_game.utils.getBackroundByAmount

class GameScreen : Fragment(R.layout.activity_game), GameContract.View {
    private val preferenseces by lazy { MySharedPreferenced.getInstance() }
    private val buttons: MutableList<TextView> = ArrayList()
    private val presenter: GameContract.Presenter = GamePresenter(this)
    private var onBackHomeListener: (() -> Unit)? = null
    private var onSetToSharedPreferencedListener: (() -> Unit)? = null
    private var onPlayAgainListener: (() -> Unit)? = null
    private var scoreText: TextView? = null
    private var sound: AppCompatImageView? = null
    private var noSound: AppCompatImageView? = null
    private var recordText: TextView? = null
    private var liner2: ConstraintLayout? = null
    var mediaPlayerMove: MediaPlayer? = null
    var mediaPlayerAdd: MediaPlayer? = null
    var mediaPlayerWin: MediaPlayer? = null
    var mediaPlayerFail: MediaPlayer? = null
    private var isSoundOff = preferenseces.bool
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData(view)
        presenter.startPlay()
        recordText?.text = preferenseces.record.toString()
        val homeButton: AppCompatImageView = view.findViewById(R.id.button_home)
        val replayButton: AppCompatImageView = view.findViewById(R.id.replay)
        val back_1StepButton: AppCompatImageView = view.findViewById(R.id.back_1_step)
        scoreText = view.findViewById(R.id.text_score)
        recordText = view.findViewById(R.id.text_record)
        noSound = view.findViewById(R.id.nosound)
        sound = view.findViewById(R.id.sound)
        mediaPlayerMove = MediaPlayer.create(context, R.raw.close_layer)
        mediaPlayerAdd = MediaPlayer.create(context, R.raw.success_daily_reward)
        mediaPlayerWin = MediaPlayer.create(context, R.raw.success_challenge)
        mediaPlayerFail = MediaPlayer.create(context, R.raw.wrong_word)
        liner2 = view.findViewById(R.id.liner2)


        if (preferenseces.bool) {
            noSound?.isVisible = true
            sound?.isVisible = false

        } else {
            noSound?.isVisible = false
            sound?.isVisible = true
        }

        sound?.setOnClickListener {
            preferenseces.bool = true
            sound?.isVisible = false
            noSound?.isVisible = true
        }
        noSound?.setOnClickListener {
            preferenseces.bool = false
            sound?.isVisible = true
            noSound?.isVisible = false
        }
        homeButton.setOnClickListener {
            presenter.clickHomeButton()
        }
        replayButton.setOnClickListener {
            presenter.clickPlayAgain()
        }
        back_1StepButton.setOnClickListener {
            presenter.clickReturnBack()
        }


    }

    fun playSound(mediaPlayer: MediaPlayer) {
        isSoundOff = preferenseces.bool
        if (!isSoundOff) {
            mediaPlayer.start()
        }
    }

    fun pauseSound(mediaPlayer: MediaPlayer) {
        if (mediaPlayer.isPlaying) mediaPlayer.pause()
    }

    fun stopSound(mediaPlayer: MediaPlayer) {
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    override fun onResume() {
        super.onResume()
        scoreText?.text = presenter.setScore().toString()
        recordText?.text = preferenseces.record.toString()
        if (preferenseces.bool) {
            noSound?.isVisible = true
            sound?.isVisible = false

        } else {
            noSound?.isVisible = false
            sound?.isVisible = true

        }


    }

    override fun onPause() {
        super.onPause()
        recordText?.text = preferenseces.record.toString()
        presenter.callPutSharedPreference()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun loadData(view: View) {
        val mainView: LinearLayoutCompat = view.findViewById(R.id.main_parent_buttons)
        for (i in 0 until mainView.childCount) {
            val line: LinearLayoutCompat = mainView.getChildAt(i) as LinearLayoutCompat
            for (j in 0 until line.childCount) {
                buttons.add(line.getChildAt(j) as TextView)
            }
        }
        val myTouchListener = MyTouchListener(requireContext())
        myTouchListener.setResultListener {
            when (it) {
                SideEnum.DOWN -> presenter.swipeDown()
                SideEnum.UP -> presenter.swipeUp()
                SideEnum.LEFT -> presenter.swipeLeft()
                SideEnum.RIGHT -> presenter.swipeRight()
            }
        }
        mainView.setOnTouchListener(myTouchListener)
    }

    override fun illustrateMatrix(matrix: Array<Array<Int>>) {
        for (i in 0 until matrix.size) {
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] == 0) buttons[4 * i + j].text = ""
                else buttons[4 * i + j].text = matrix[i][j].toString()
                buttons[4 * i + j].setBackgroundResource(matrix[i][j].getBackroundByAmount())
            }
        }
        if (presenter.soundBegin()) {
            if (presenter.isAddTrue()) {
                mediaPlayerAdd?.let { playSound(it) }
            } else {
                mediaPlayerMove?.let { playSound(it) }
            }
            presenter.soundBegin(false)
        }

        scoreText?.text = presenter.setScore().toString()
        if (presenter.checkToFinish()) {
            mediaPlayerFail?.let { playSound(it) }
            val dialog = WinningDialog()
            dialog.isCancelable = false
            dialog.setOnReplayListener {
                presenter.fillMatrixForReplay()
                val dialog = WinningDialog()
                scoreText?.text = presenter.setScore().toString()
                presenter.startPlay()
            }
            dialog.setOnBackToHomeListener {
                presenter.clickHomeButton()
                scoreText?.text = presenter.setScore().toString()
            }
            recordText?.text = preferenseces.record.toString()
            dialog.show(childFragmentManager, "play")
        }
        if (presenter.isWin()) {

            val dialog = WinningDialog()
            dialog.isCancelable = false
            mediaPlayerWin?.let { playSound(it) }
            dialog.setOnReplayListener {
                presenter.fillMatrixForReplay()
                scoreText?.text = presenter.setScore().toString()
                presenter.startPlay()
            }
            dialog.setOnBackToHomeListener {
                presenter.fillMatrixForReplay()
                presenter.startPlay()
                presenter.clickHomeButton()
                scoreText?.text = presenter.setScore().toString()
            }
            recordText?.text = preferenseces.record.toString()
            dialog.show(childFragmentManager, "game")
        }
    }

    fun setOnBackHomeListener(block: () -> Unit) {
        onBackHomeListener = block
    }

    fun setOnSetToSharedPreferencedListener(block: () -> Unit) {
        onSetToSharedPreferencedListener = block
    }

    fun setPlayAgainListener(block: () -> Unit) {
        onPlayAgainListener = block
    }

    override fun backToHome() {
        onBackHomeListener?.invoke()
    }

    override fun playAgain() {
        val dialog = MyDialog()
        dialog.setOnReplayListener {
            presenter.fillMatrixForReplay()
            scoreText?.text = presenter.setScore().toString()
            presenter.startPlay()
        }
        dialog.show(childFragmentManager, "replay")
    }

    override fun swipeUpAnimation(position: Int) {
        YoYo.with(Techniques.FadeInUp).duration(200).playOn(buttons[position])
    }

    override fun swipeDownAnimation(position: Int) {
        YoYo.with(Techniques.FadeInDown).duration(200).playOn(buttons[position])
    }

    override fun swipeLeftAnimation(position: Int) {
        YoYo.with(Techniques.FadeInLeft).duration(200).playOn(buttons[position])
    }

    override fun swipeRightAnimation(position: Int) {
        YoYo.with(Techniques.FadeInRight).duration(200).playOn(buttons[position])
    }


    override fun returnBack() {

    }


}