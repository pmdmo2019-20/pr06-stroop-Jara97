package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import  es.iessaladillo.pedrojoya.stroop.avatars
import androidx.preference.PreferenceManager

import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.room.Database1
import es.iessaladillo.pedrojoya.stroop.ui.room.Player
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var  sharedPreferences: SharedPreferences


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setListeners()
        setHasOptionsMenu(true)
        sharedPreferences=activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        setTittle()

    }

    private fun setTittle() {
        imgMainPlayer.setImageResource( viewModel.onChangePlayer.value!!.avatar)
        lblTittleMain.text=viewModel.onChangePlayer.value!!.name

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu,inflater)
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            setTitle(R.string.app_name)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


    private fun setListeners(){
        cardAbout.setOnClickListener {
            viewModel.setFragment(2)
        }
        cardSettings.setOnClickListener {
            viewModel.setFragment(3)
        }
        cardAssistant.setOnClickListener {
            viewModel.setFragment(4)
        }
        cardPlayer.setOnClickListener {
            viewModel.setFragment(5)

        }
        cardPlay.setOnClickListener {
            if(!lblTittleMain.text.toString().equals("No player selected")){
                viewModel.setFragment(8)
            }

        }
        cardRanking.setOnClickListener {
            viewModel.setFragment(10)

        }

    }



}
