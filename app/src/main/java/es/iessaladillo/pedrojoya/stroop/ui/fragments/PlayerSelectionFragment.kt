package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.*
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.playercreation_fragment.*
import kotlinx.android.synthetic.main.playerselection_fragment.*

class PlayerSelectionFragment : Fragment(R.layout.playerselection_fragment) {

    private lateinit var  sharedPreferences: SharedPreferences

    private val listAdapterSelect:RecyclerViewAdapterSelect=RecyclerViewAdapterSelect()

    private val viewModel: MainActivityViewModel by activityViewModels()

    companion object {
        fun newInstance() = PlayerSelectionFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedPreferences=activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        setHasOptionsMenu(true)
        setupRecyclerView()
        setObservers()
        listAdapterSelect.apply {
            setPreferences(sharedPreferences,viewModel)

        }
        setupViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu,inflater)

    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.player_selection_title)
        }
    }

    private fun setupRecyclerView(){
        lstAvatars.run{
            setHasFixedSize(true)
            layoutManager= GridLayoutManager(context,2)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapterSelect
        }
    }

    private fun setObservers() {
        viewModel.getPlayers().observe(this){
            listAdapterSelect.submitList(it)
        }
        viewModel.onChangePlayer.observe(this){
            imgPlayerSelect.setImageResource(it.avatar)
            txtPlayerSelect.text=it.name
            if (it.avatar!=R.drawable.logo){
                btnEdit.visibility=View.VISIBLE
            }
            else{
                btnEdit.visibility=View.INVISIBLE
            }

        }
        btnCreate.setOnClickListener {
            viewModel.setFragment(6)
        }
        btnEdit.setOnClickListener {
            viewModel.setFragment(7)
        }

    }




    private fun showMessage(message: String) {
        Snackbar.make(lstAvatars, message, Snackbar.LENGTH_SHORT).show()
    }





}