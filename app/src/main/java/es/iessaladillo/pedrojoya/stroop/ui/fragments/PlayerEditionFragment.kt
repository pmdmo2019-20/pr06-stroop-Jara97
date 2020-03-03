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

class PlayerEditionFragment : Fragment(R.layout.playercreation_fragment) {

    private lateinit var sharedPreferences: SharedPreferences

    private val listAdapterSelect: RecyclerViewAdapterCreation = RecyclerViewAdapterCreation()

    private val viewModel: MainActivityViewModel by activityViewModels()

    companion object {
        fun newInstance() = PlayerEditionFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedPreferences = activity!!.getSharedPreferences("pref", Context.MODE_PRIVATE)
        setupViews()
        setHasOptionsMenu(true)
        setupRecyclerView()
        setObservers()
        listAdapterSelect.apply {
            setPreferences(sharedPreferences, viewModel)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.player_manu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.player_edition_title)
        }

    }

    private fun setupRecyclerView() {
        lstAvatarsCreate.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapterSelect
        }
    }

    private fun setObservers() {
        viewModel.imageCreation.observe(this){
            imgPlayerCreate.setImageResource(it)
        }
        viewModel.onChangePlayer.observe(this){
            imgPlayerCreate.setImageResource(it.avatar)
            txtPlayerCreate.setText(it.name)
        }

        btnSave.setOnClickListener {
            if(viewModel.imageCreation.value!=R.drawable.logo && txtPlayerCreate.text!=null && !txtPlayerCreate.text!!.isBlank()){
               viewModel.updatePlayer(Player(viewModel.onChangePlayer.value!!.id,txtPlayerCreate.text.toString(),viewModel.imageCreation.value!!))
                viewModel.setFragment(5)
            }
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean =

        when (item.itemId) {
            R.id.mnuDelete -> {
                AlertDialog.Builder(context!!)
                    .setTitle(R.string.player_deletion_title)
                    .setPositiveButton(R.string.player_deletion_yes){_,_->viewModel.deletePlayer(viewModel.onChangePlayer.value!!)}
                    .setNegativeButton(R.string.player_deletion_no){_,_->}
                    .setMessage(R.string.player_deletion_message)
                    .create().show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }




}