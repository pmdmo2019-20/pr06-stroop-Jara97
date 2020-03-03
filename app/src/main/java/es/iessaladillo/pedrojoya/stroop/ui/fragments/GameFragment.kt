package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.game.GameViewModel
import es.iessaladillo.pedrojoya.stroop.ui.room.Player
import kotlinx.android.synthetic.main.game_fragment.*
import kotlinx.android.synthetic.main.playercreation_fragment.*

class GameFragment : Fragment(R.layout.game_fragment) {

    private lateinit var  sharedPreferences: SharedPreferences

    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }


    private val viewModel: MainActivityViewModel by activityViewModels()

    private val gameViewModel:GameViewModel= GameViewModel().apply {
        //setContext(context!!)
    }

    companion object {
        fun newInstance() = GameFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedPreferences=activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        setupViews()
        setHasOptionsMenu(false)
        setObservers()

    }


    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle("")
        }
        gameViewModel.actualPlayer=viewModel.onChangePlayer.value!!
        var attempts:Int=settings.getString(getText(R.string.prefAttempts_key).toString(),"-1")!!.toInt()
        if(settings.getString(getText(R.string.prefGameMode_key).toString(),"Time").equals("Time")){
            println(settings.getString(getText(R.string.prefGameMode_key).toString(),"Time"))
            attempts=-1
        }


        progressBar.max=settings.getString(getText(R.string.prefGameTime_key).toString(),"10000")!!.toInt()
        if(attempts>0){
            gameViewModel.setAttempts(attempts)
            lblAttempsNum.text=attempts.toString()
        }
        else{
            gameViewModel.setAttempts(-1)
            lblAttempsNum.text="0"
        }

        gameViewModel.startGameThread(settings.getString(getText(R.string.prefGameTime_key).toString(),"10000")!!.toInt(),settings.getString(getText(R.string.prefWordTime_key).toString(),"1000")!!.toInt())
    }

    private fun setObservers() {
        gameViewModel.color.observe(this){
            lblWord.setTextColor(it)
        }
        gameViewModel.word.observe(this){
            lblWord.text=it
        }
        gameViewModel.totalWord.observe(this){
            lblWordsNum.text=it.toString()
        }

        gameViewModel.correct.observe(this){
            lblCorrectNum.text=it.toString()
        }
        imgOk.setOnClickListener{
            gameViewModel.checkRight()
        }
        imgNo.setOnClickListener{
            gameViewModel.checkWrong()
        }
        gameViewModel.time.observe(this){
            progressBar.progress=it
            if(it==0){
                gameViewModel.onGameTimeFinish()
                viewModel.setFragment(9)
            }
        }
        gameViewModel.attempts.observe(this){
            if(it==0){
                gameViewModel.onGameTimeFinish()
                viewModel.setFragment(9)
            }
            else if(it<0){
                lblAttempsNum.text="0"
            }
            else{
                lblAttempsNum.text=it.toString()
            }
        }
        gameViewModel.end.observe(this){
            viewModel.setMatch(it)
            viewModel.insertMatch(it)
        }
    }





}