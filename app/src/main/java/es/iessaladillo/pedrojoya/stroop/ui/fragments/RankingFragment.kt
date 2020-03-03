package es.iessaladillo.pedrojoya.stroop.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.*
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.playercreation_fragment.*
import kotlinx.android.synthetic.main.ranking_fragment.*


class RankingFragment : Fragment(R.layout.ranking_fragment) {


    private val viewModel: MainActivityViewModel by activityViewModels()

    private val listAdapterRanking:RecyclerViewAdapterRanking=RecyclerViewAdapterRanking().apply {
        this.setOnItemClick { match,player ->
            viewModel.setMatch(match)
            viewModel.changePlayer(player)
            viewModel.setFragment(9)
        }
    }

    companion object {
        fun newInstance() = RankingFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setHasOptionsMenu(true)
        setupRecyclerView()
        setObserveres()
    }

    private fun setObserveres() {
        viewModel.getPlayersWithGames().observe(this){
            listAdapterRanking.submitList(it)
        }
    }

    private fun setupViews() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.ranking_title)
        }
        listAdapterRanking.apply {
            setPreferences(viewModel)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu,inflater)
    }

    private fun setupRecyclerView(){
        lstRanking.run{
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapterRanking
        }
    }


}