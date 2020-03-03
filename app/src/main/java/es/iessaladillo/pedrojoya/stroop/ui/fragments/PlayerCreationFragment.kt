package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import es.iessaladillo.pedrojoya.stroop.ui.room.Player
import kotlinx.android.synthetic.main.playercreation_fragment.*
import kotlinx.android.synthetic.main.playerselection_fragment.*

class PlayerCreationFragment : Fragment(R.layout.playercreation_fragment) {

    private lateinit var  sharedPreferences: SharedPreferences

    private val listAdapterSelect:RecyclerViewAdapterCreation=RecyclerViewAdapterCreation()

    private val viewModel: MainActivityViewModel by activityViewModels()

    companion object {
        fun newInstance() = PlayerCreationFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setHasOptionsMenu(true)
        setupRecyclerView()
        setObservers()
        sharedPreferences=activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        listAdapterSelect.apply {
            setPreferences(sharedPreferences,viewModel)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu,inflater)
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.player_creation_title)
        }
    }

    private fun setupRecyclerView(){
        lstAvatarsCreate.run{
            setHasFixedSize(true)
            layoutManager= GridLayoutManager(context,3)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = listAdapterSelect
        }
    }

    private fun setObservers() {
        viewModel.imageCreation.observe(this){
            imgPlayerCreate.setImageResource(it)
        }
        btnSave.setOnClickListener {
            if(viewModel.imageCreation.value!=R.drawable.logo && txtPlayerCreate.text!=null && !txtPlayerCreate.text!!.isBlank()){
                viewModel.insertPlayer(Player(0,txtPlayerCreate.text.toString(),viewModel.imageCreation.value?:R.drawable.avatar_06_chinese))
                viewModel.setFragment(5)
            }
        }

    }





}