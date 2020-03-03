package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.assistant_fragment_content.*

class AssistantFragmentContent : Fragment(R.layout.assistant_fragment_content) {
    var page:Int=0

    private val viewModel: MainActivityViewModel by activityViewModels()


    companion object {
        fun newInstance() = AssistantFragmentContent()

    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.assistant_title)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setHasOptionsMenu(true)
        selectView()
        setListeners()
    }

    private fun setListeners() {
        btnFinish.setOnClickListener {
            viewModel.setTutorial(true)
            viewModel.setFragment(1)

        }
    }

    private fun selectView() {
        when(page){
            0->{cLAssistant.setBackgroundColor(resources.getColor(R.color.stroopOption));lblAssistant.setText(R.string.assistant_stroopDescription);imgAssistant.setImageResource(R.drawable.logo)}
            1->{cLAssistant.setBackgroundColor(resources.getColor(R.color.playOption));lblAssistant.setText(R.string.assistant_playDescription);imgAssistant.setImageResource(R.drawable.ic_play_black_24dp)}
            2->{cLAssistant.setBackgroundColor(resources.getColor(R.color.settingsOption));lblAssistant.setText(R.string.assistant_settingsDescription);imgAssistant.setImageResource(R.drawable.ic_settings_black_24dp)}
            3->{cLAssistant.setBackgroundColor(resources.getColor(R.color.rankingOption));lblAssistant.setText(R.string.assistant_rankingDescription);imgAssistant.setImageResource(R.drawable.ic_ranking_black_24dp)}
            4->{cLAssistant.setBackgroundColor(resources.getColor(R.color.assistantOption));lblAssistant.setText(R.string.assistant_assistantDescription);imgAssistant.setImageResource(R.drawable.ic_assistant_black_24dp)}
            5->{cLAssistant.setBackgroundColor(resources.getColor(R.color.playerOption));lblAssistant.setText(R.string.assistant_playerDescription);imgAssistant.setImageResource(R.drawable.ic_player_add_black_24dp)}
            6->{cLAssistant.setBackgroundColor(resources.getColor(R.color.aboutOption));lblAssistant.setText(R.string.assistant_aboutDescription);imgAssistant.setImageResource(R.drawable.ic_about_black_24dp)}
            7->{cLAssistant.setBackgroundColor(resources.getColor(R.color.finishOption));lblAssistant.setText(R.string.assistant_finishDescription);imgAssistant.setImageResource(R.drawable.ic_finish_black_24dp);btnFinish.isVisible=true}

        }
    }

//
}