package es.iessaladillo.pedrojoya.stroop.ui

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.findNavController
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.fragments.*
import es.iessaladillo.pedrojoya.stroop.ui.room.Database1
import es.iessaladillo.pedrojoya.stroop.ui.room.Player
import kotlinx.android.synthetic.main.assistant_fragment.*


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var  sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        sharedPreferences=getSharedPreferences("pref",Context.MODE_PRIVATE)
        if(!sharedPreferences.getBoolean("tutorial",false)){
            viewModel.setFragment(4)
        }
        if (savedInstanceState == null) {
            showInitialDestination()
        }
        setupAppBar()
        setObservers()
        viewModel.setBD(this,sharedPreferences)
    }



    private fun changeFragment(fragment:Int) {
        when (fragment) {
            1 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, MainFragment.newInstance())
            }
            2 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, AboutFragment.newInstance())
            }
            3 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, SettingsFragment.newInstance())
            }
            4 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, AssistantFragment.newInstance())
            }
            5 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, PlayerSelectionFragment.newInstance())
            }
            6 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, PlayerCreationFragment.newInstance())
            }
            7 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, PlayerEditionFragment.newInstance())
            }
            8 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, GameFragment.newInstance())
            }
            9 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, SumaryFragment.newInstance())
            }
            10 -> supportFragmentManager.commit {
                replace(R.id.navHostFragment, RankingFragment.newInstance())
            }

        }
    }

    private fun setObservers() {
        viewModel.fragment.observe(this){
            changeFragment(it)
        }
        viewModel.tutorial.observe(this){
            if(!sharedPreferences.getBoolean("tutorial",false)){
                sharedPreferences.edit{
                    putBoolean("tutorial",true)
                }
            }
        }
    }

    private fun showInitialDestination() {
        supportFragmentManager.commit {
            replace(R.id.navHostFragment, MainFragment.newInstance())
        }
    }

    private fun setupAppBar() {
        setSupportActionBar(toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        if(viewModel.fragment.value==1){
            super.onBackPressed()
        }
        else{
            viewModel.setFragment(1)
        }
        return true
    }

    override fun onBackPressed() {
        if(viewModel.fragment.value==1){
            super.onBackPressed()
        }
        else{
            viewModel.setFragment(1)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =

        when (item.itemId) {
            R.id.mnuHelp -> {
                if(viewModel.fragment.value==1){
                    AlertDialog.Builder(this)
                        .setTitle(R.string.help_title)
                        .setPositiveButton(R.string.help_accept){_,_->}
                        .setMessage(R.string.dashboard_help_description)
                        .create().show()
                }
                else if(viewModel.fragment.value==3){
                    AlertDialog.Builder(this)
                        .setTitle(R.string.help_title)
                        .setPositiveButton(R.string.help_accept){_,_->}
                        .setMessage(R.string.settings_help_description)
                        .create().show()
                }
                else if(viewModel.fragment.value==5){
                    AlertDialog.Builder(this)
                        .setTitle(R.string.player_selection_title)
                        .setPositiveButton(R.string.help_accept){_,_->}
                        .setMessage(R.string.player_selection_help_description)
                        .create().show()
                }
                else if(viewModel.fragment.value==6){
                    AlertDialog.Builder(this)
                        .setTitle(R.string.player_creation_title)
                        .setPositiveButton(R.string.help_accept){_,_->}
                        .setMessage(R.string.player_creation_help_description)
                        .create().show()
                }
                else if(viewModel.fragment.value==7){
                    AlertDialog.Builder(this)
                        .setTitle(R.string.player_edition_title)
                        .setPositiveButton(R.string.help_accept){_,_->}
                        .setMessage(R.string.player_edition_help_description)
                        .create().show()
                }
                else if(viewModel.fragment.value==10){
                    AlertDialog.Builder(this)
                        .setTitle(R.string.ranking_title)
                        .setPositiveButton(R.string.help_accept){_,_->}
                        .setMessage(R.string.ranking_help_description)
                        .create().show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


}
