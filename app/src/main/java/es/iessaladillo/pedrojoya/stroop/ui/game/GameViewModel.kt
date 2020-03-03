package es.iessaladillo.pedrojoya.stroop.ui.game

import android.content.Context
import android.graphics.Color
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.ui.room.Match
import es.iessaladillo.pedrojoya.stroop.ui.room.Player
import java.util.*
import kotlin.concurrent.thread


class GameViewModel() : ViewModel() {

    @Volatile
    private var isGameFinished: Boolean = false
    @Volatile
    private var currentWordMillis: Int = 0
    @Volatile
    private var millisUntilFinished: Int = 0
    private var minutes:Long=0
    private val handler: Handler = Handler()
    private val listColors: List<String> = listOf("BLUE","RED","YELLOW","GREEN")
    private val listColors2:List<Int> = listOf(Color.BLUE,Color.RED,Color.YELLOW,Color.GREEN)

    private var rColor:Int=-1
    private var wColor:Int=-1

    lateinit var actualPlayer:Player

    private var gameModeA:Boolean=true
    private var gameModeB:String=""

    private val randomColor:Random=Random()

    private val _attempts: MutableLiveData<Int> = MutableLiveData(1)
    val attempts: LiveData<Int>
        get() = _attempts

    private val _color: MutableLiveData<Int> = MutableLiveData(Color.BLUE)
    val color: LiveData<Int>
        get() = _color

    private val _correct: MutableLiveData<Int> = MutableLiveData(0)
    val correct: LiveData<Int>
        get() = _correct

    private val _word: MutableLiveData<String> = MutableLiveData("BLUE")
    val word: LiveData<String>
        get() = _word

    private val _totalWord: MutableLiveData<Int> = MutableLiveData(0)
    val totalWord: LiveData<Int>
        get() = _totalWord

    private val _time: MutableLiveData<Int> = MutableLiveData(0)
    val time: LiveData<Int>
        get() = _time

    private val _end: MutableLiveData<Match> = MutableLiveData()
    val end: LiveData<Match>
        get() = _end




    private fun onGameTimeTick(millisUntilFinished: Int) {
        _time.value=millisUntilFinished
    }

    fun setAttempts(attempts:Int){
        this._attempts.value=attempts
    }

    fun setGameMode(gameMode:String) {
        this.gameModeB=gameMode
    }



     fun onGameTimeFinish() {
        isGameFinished = true
         _end.value= Match(0,actualPlayer.id,correct.value!!.toLong()*10,minutes,totalWord.value!!.toLong(),gameModeB,Date().toString())
    }

    fun onGameTimeFinish2() {
        isGameFinished = true

    }

    fun nextWord() {
        rColor=randomColor.nextInt(4)
        wColor=randomColor.nextInt(4)
        _color.value=listColors2[rColor]
        _word.value=listColors[wColor]
        _totalWord.value=_totalWord.value!!+1

    }

    fun checkRight() {
        currentWordMillis = 0
        nextWord()
        if(wColor==rColor){
            _correct.value=correct.value!!+1
        }
        else{
            if(gameModeA){
                _attempts.value=_attempts.value!!-1
            }
        }

    }

    fun checkWrong() {
        currentWordMillis = 0
        nextWord()
        if(wColor!=rColor){
            _correct.value=correct.value!!+1
        }
        else{
            if(gameModeA){
                _attempts.value=_attempts.value!!-1
            }

        }

    }

    fun startGameThread(gameTime: Int, wordTime: Int) {
        millisUntilFinished = gameTime
        minutes = gameTime.toLong()
        currentWordMillis = 0
        isGameFinished = false
        _time.value=gameTime
        if(_attempts.value!!<=0){
            gameModeA=false
        }
        val checkTimeMillis: Int = wordTime / 2
        nextWord()
        thread {
            try {
                while (!isGameFinished) {
                    Thread.sleep(checkTimeMillis.toLong())
                    // Check and publish on main thread.
                    handler.post {
                        if (!isGameFinished) {
                            if (currentWordMillis >= wordTime) {
                                currentWordMillis = 0
                                nextWord()
                            }
                            currentWordMillis += checkTimeMillis
                            millisUntilFinished -= checkTimeMillis
                            onGameTimeTick(millisUntilFinished)
                            if (millisUntilFinished <= 0) {
                                onGameTimeFinish2()
                            }
                        }
                    }
                }
            } catch (ignored: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        isGameFinished = true
    }

}